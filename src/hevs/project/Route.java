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

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.content.Context;
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
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route);
		
		// Init MapView and configure it
		mapView=(MapView)findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		// Init MapController
		mapController=mapView.getController();
		mapController.setZoom(14); // Zoom 1 is world view
		
		// Init GeoPoints
		currentLoc=new GeoPoint(0,0);
		mercier=new GeoPoint(46294116,7527538); // GeoPoint fix: Chateau Mercier at 46.294116,7.52753
		mapController.animateTo(mercier);
		
		// Init LocationManager
		locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,new GeoUpdateHandler());
	}

	protected boolean isRouteDisplayed()
	{
		return false;
	}
	
	public class GeoUpdateHandler implements LocationListener
	{
		public void onLocationChanged(Location location)
		{
			int lat=(int)(location.getLatitude()*1E6);
			int lng=(int)(location.getLongitude()*1E6);
			currentLoc=new GeoPoint(lat,lng);
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