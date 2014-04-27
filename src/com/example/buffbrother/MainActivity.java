package com.example.buffbrother;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.os.Build;
import android.annotation.TargetApi;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity {
	
	private GoogleMap mMap;
	private LatLng Boulder = new LatLng(40.002917, -105.259268);
	/*private LatLngBounds busRoute = new LatLngBounds(
			new LatLng(39.995488, -105.247616),
			new LatLng(40.010708, -105.272550)
			);*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("MainActivity", "CONRAD: In onCreate method");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		setUpMapIfNeeded();
	}
	
	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the map.
		Log.d("MainActivity", "CONRAD: Setting up map");
		if(mMap == null) {
			mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map
			if(mMap != null) {
				// Safe to manipulate the map
				mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				mMap.setBuildingsEnabled(false);
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Boulder, 14));
				
				//mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(busRoute, 0));				
				mMap.addMarker(new MarkerOptions()
					.position(new LatLng(39.996583, -105.250652))
					.title("Bear Creek"));
				mMap.addMarker(new MarkerOptions()
					.position(new LatLng(39.998703, -105.252765))
					.title("Will Vill"));
				mMap.addMarker(new MarkerOptions()
					.position(new LatLng(40.007786, -105.261405))
					.title("Engineering Center"));
				mMap.addMarker(new MarkerOptions()
					.position(new LatLng(40.003894, -105.265912))
					.title("C4C"));
				mMap.addMarker(new MarkerOptions()
					.position(new LatLng(40.005958, -105.270320))
					.title("UMC"));
				mMap.addMarker(new MarkerOptions()
					.position(new LatLng(40.008238, -105.268995))
					.title("Norlin"));
				mMap.addMarker(new MarkerOptions()
					.position(new LatLng(40.008233, -105.264619))
					.title("Math"));
			}
		}
		
	}
	
}
