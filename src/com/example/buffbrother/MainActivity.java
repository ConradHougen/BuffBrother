package com.example.buffbrother;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.os.Build;
import android.annotation.TargetApi;
import android.content.SharedPreferences;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;


//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity {
	
	public static AmazonDynamoDBClient client;
	// keep hashmap of markers sorted by bus id
	public static HashMap<Integer, Marker> busMarkerList = new HashMap<Integer, Marker>();
	
	private static final String AWS_ACCESS_KEY = "AKIAIAHBD5GTZA5EMGUA";
	private static final String AWS_SECRET_KEY = "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";
	
	public static GoogleMap mMap;
	private LatLng Boulder = new LatLng(40.002917, -105.259268);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d("MainActivity", "CONRAD: Creating main activity...");

		try {
			createClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		setUpMapIfNeeded();
		
		// loop forever
		do
		{
			Log.d("MainActivity", "CONRAD: About to retrieve item");
			new ReadDynamoDB().execute(client);
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while(false);

	}
	
	private void createClient() throws IOException {
		Log.d("MainActivity", "Creating client");
		//AWSCredentials credentials = new PropertiesCredentials(
		//		MainActivity.class.getResourceAsStream("AwsCredentials.properties"));
		AWSCredentials credentials = new BasicAWSCredentials( AWS_ACCESS_KEY, AWS_SECRET_KEY );
		client = new AmazonDynamoDBClient(credentials);
		Log.d("MainActivity", "Done creating client");
	}
	
	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the map.
		Log.d("MainActivity", "CONRAD: Setting up map");
		if(mMap == null) {
			Log.d("MainActivity", "CONRAD: mMap null");
			mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
			Log.d("MainActivity", "CONRAD: getting map didn't break anything");
			// Check if we were successful in obtaining the map
			if(mMap != null) {
				Log.d("MainActivity", "CONRAD: mMap not null anymore");
				// Safe to manipulate the map
				//mMap.getUiSettings().setScrollGesturesEnabled(false);
				mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				mMap.setBuildingsEnabled(false);
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Boulder, 14));
				
				Log.d("MainActivity", "CONRAD: mMap about to add markers");
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
			else
			{
				Log.d("MainActivity", "CONRAD: mMap still null!");
			}
		}
		
	}
		
}
