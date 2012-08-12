package edu.montgomerycollege.mapdemo;

import java.io.IOException;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MapDemoActivity extends MapActivity implements LocationListener {
    /** Called when the activity is first created. */
    
	private MapView mapView;
	
	private LocationManager locMgr;
	
	private Geocoder geocoder;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        locMgr = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        geocoder = new Geocoder(this);
        mapView = (MapView)findViewById(R.id.map_view);
        
        try {
        	List<Address> addresses = geocoder.getFromLocationName("Nationals Park", 100);
        	if (addresses.size() > 0) {
        		Address codedAddress = addresses.get(0);
        		Double codedLat = codedAddress.getLatitude();
        		Double codedLon = codedAddress.getLongitude();
        		int latTimesMil = (int)(codedLat*1000000);
        		int lonTimesMil = (int)(codedLon*1000000);
                GeoPoint mapCenter = new GeoPoint(latTimesMil, lonTimesMil);
                mapView.getController().setCenter(mapCenter);
        	}
        } catch (IOException ioe) {
        	Log.d("Geocode", "Couldn't geocode");
        }        
        
        mapView.setBuiltInZoomControls(true);
        

        
        mapView.getController().setZoom(16);
        
        mapView.setSatellite(true);
        mapView.setTraffic(true);
    }
	
	@Override
	public void onResume() {
		super.onResume();
		locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		locMgr.removeUpdates(this);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void onLocationChanged(Location location) {
		int lat = (int)(location.getLatitude()*1000000);
		int lon = (int)(location.getLongitude()*1000000);
		GeoPoint currentLocationCoords = new GeoPoint(lat, lon);
		mapView.getController().animateTo(currentLocationCoords);
		
		List<Address> addresses;
		
		try {
			addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 
				1);			
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				String toastText = "";
				for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
					toastText += address.getAddressLine(i) + "\n";
				}
				Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
			}
		} catch(IOException ioe) {
			Log.d("Geocode", "Couldn't reverse geocode");
		}
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}