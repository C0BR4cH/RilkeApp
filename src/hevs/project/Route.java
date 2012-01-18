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

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
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
		mapView.invalidate();
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