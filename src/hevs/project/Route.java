/* RilkeApp
 * 
 * 
 * Route.java
 * 
 * Matthias Hurni
 * Created: 18.01.2012 14:52:59
 * Edit: 
 */
package hevs.project;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Route extends MapActivity
{
	@SuppressWarnings("unused")
	private LinearLayout linearLayout;
	private MapView mapView;
	private MapController mapController;
	private LocationManager locationManager;
	private GeoPoint mercier;
	private GeoPoint currentLoc;
	private RilkeOverlay ovRilke;
	private CurrentLocOverlay ovYou;
	private List<Overlay> overlayList;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route);

		// Init MapView and configure it
		mapView=(MapView)findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);

		// Init MapController
		mapController=mapView.getController();
		mapController.setZoom(17); // Zoom 1 is world view

		// Init GeoPoints
		currentLoc=new GeoPoint(46949017,7439286); // GeoPoint fix: Bern Trainstation at 46.949017,7.439286
		mercier=new GeoPoint(46294116,7527538); // GeoPoint fix: Chateau Mercier at 46.294116,7.52753
		mapController.animateTo(mercier);

		// Init Icons
		ovRilke=new RilkeOverlay();
		ovYou=new CurrentLocOverlay();
		refreshOverlay();

		// Init LocationManager
		locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,new GeoUpdateHandler());
	}

	protected boolean isRouteDisplayed()
	{
		return false;
	}

	private void refreshOverlay()
	{
		overlayList=mapView.getOverlays();
		overlayList.clear();
		overlayList.add(ovRilke);
		overlayList.add(ovYou);
		drawPath(currentLoc,mercier,Color.RED,mapView);
		mapView.invalidate();
	}

	private void drawPath(GeoPoint src,GeoPoint dest, int color, MapView mapView)
	{
		// connect to map web service
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.google.com/maps?f=d&hl=en");
		urlString.append("&saddr=");//from
		urlString.append( Double.toString((double)src.getLatitudeE6()/1.0E6 ));
		urlString.append(",");
		urlString.append( Double.toString((double)src.getLongitudeE6()/1.0E6 ));
		urlString.append("&daddr=");//to
		urlString.append( Double.toString((double)dest.getLatitudeE6()/1.0E6 ));
		urlString.append(",");
		urlString.append( Double.toString((double)dest.getLongitudeE6()/1.0E6 ));
		urlString.append("&ie=UTF8&0&om=0&output=kml");

		// get the kml (XML) doc. And parse it to get the coordinates(direction route)
		Document doc = null;
		HttpURLConnection urlConnection= null;
		URL url = null;
		try
		{
			url = new URL(urlString.toString());
			urlConnection=(HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.connect();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(urlConnection.getInputStream());

			if(doc.getElementsByTagName("GeometryCollection").getLength()>0)
			{
				//String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getNodeName();
				String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue() ;
				String [] pairs = path.split(" ");
				String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude lngLat[1]=latitude lngLat[2]=height
				// src
				GeoPoint startGP = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
				mapView.getOverlays().add(new RoadOverlay(startGP,startGP,1));
				GeoPoint gp1;
				GeoPoint gp2 = startGP;
				for(int i=1;i<pairs.length;i++) // the last one would be crash
				{
					lngLat = pairs[i].split(",");
					gp1 = gp2;
					// watch out! For GeoPoint, first:latitude, second:longitude
					gp2 = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
					mapView.getOverlays().add(new RoadOverlay(gp1,gp2,2,color));
				}
				mapView.getOverlays().add(new RoadOverlay(dest,dest, 3)); // use the default color
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
	}

	public class RoadOverlay extends Overlay
	{
		private GeoPoint gp1;
		private GeoPoint gp2;
		private int mRadius=6;
		private int mode=0;
		private int defaultColor;

		public RoadOverlay(GeoPoint gp1,GeoPoint gp2,int mode) // GeoPoint is a int. (6E)
		{
			this.gp1 = gp1;
			this.gp2 = gp2;
			this.mode = mode;
			defaultColor = 999; // no defaultColor

		}

		public RoadOverlay(GeoPoint gp1,GeoPoint gp2,int mode, int defaultColor)
		{
			this.gp1 = gp1;
			this.gp2 = gp2;
			this.mode = mode;
			this.defaultColor = defaultColor;
		}
		
		public int getMode()
		{
			return mode;
		}

		public boolean draw
		(Canvas canvas, MapView mapView, boolean shadow, long when)
		{
			Projection projection = mapView.getProjection();
			if (shadow == false)
			{
				Paint paint = new Paint();
				paint.setAntiAlias(true);
				Point point = new Point();
				projection.toPixels(gp1, point);
				// mode=1&#65306;start
				if(mode==1)
				{
					if(defaultColor==999)
						paint.setColor(Color.BLUE);
					else
						paint.setColor(defaultColor);
					RectF oval=new RectF(point.x - mRadius, point.y - mRadius,
							point.x + mRadius, point.y + mRadius);
					// start point
					canvas.drawOval(oval, paint);
				}
				// mode=2&#65306;path
				else if(mode==2)
				{
					if(defaultColor==999)
						paint.setColor(Color.RED);
					else
						paint.setColor(defaultColor);
					Point point2 = new Point();
					projection.toPixels(gp2, point2);
					paint.setStrokeWidth(5);
					paint.setAlpha(120);
					canvas.drawLine(point.x, point.y, point2.x,point2.y, paint);
				}
				/* mode=3&#65306;end */
				else if(mode==3)
				{
					/* the last path */

					if(defaultColor==999)
						paint.setColor(Color.GREEN);
					else
						paint.setColor(defaultColor);
					Point point2 = new Point();
					projection.toPixels(gp2, point2);
					paint.setStrokeWidth(5);
					paint.setAlpha(120);
					canvas.drawLine(point.x, point.y, point2.x,point2.y, paint);
					RectF oval=new RectF(point2.x - mRadius,point2.y - mRadius,
							point2.x + mRadius,point2.y + mRadius);
					/* end point */
					paint.setAlpha(255);
					canvas.drawOval(oval, paint);
				}
			}
			return super.draw(canvas, mapView, shadow, when);
		}
	}

	public class RilkeOverlay extends Overlay
	{
		public boolean draw(Canvas canvas,MapView mapView,boolean shadow,long when)
		{
			super.draw(canvas, mapView, shadow);                   

			// translate the GeoPoint to screen pixels
			Point screenPts = new Point();
			mapView.getProjection().toPixels(mercier, screenPts);

			// add the marker
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ov_rilke);            
			canvas.drawBitmap(bmp, screenPts.x-25, screenPts.y-50, null);         
			return true;
		}
	}

	public class CurrentLocOverlay extends Overlay
	{
		public boolean draw(Canvas canvas,MapView mapView,boolean shadow,long when)
		{
			super.draw(canvas, mapView, shadow);                   

			// translate the GeoPoint to screen pixels
			Point screenPts = new Point();
			mapView.getProjection().toPixels(currentLoc, screenPts);

			// add the marker
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ov_you);            
			canvas.drawBitmap(bmp, screenPts.x-13, screenPts.y-44, null);         
			return true;
		}
	} 

	public class GeoUpdateHandler implements LocationListener
	{
		public void onLocationChanged(Location location)
		{
			int lat=(int)(location.getLatitude()*1E6);
			int lng=(int)(location.getLongitude()*1E6);
			currentLoc=new GeoPoint(lat,lng);
			refreshOverlay();
			//			mapController.animateTo(point);
		}

		public void onProviderDisabled(String provider)
		{

		}

		public void onProviderEnabled(String provider)
		{

		}

		public void onStatusChanged(String provider,int status,Bundle extras)
		{

		}
	}
}