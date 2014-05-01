package com.example.buffbrother;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.AsyncTask;
import android.util.Log;


public class ReadDynamoDB extends AsyncTask<AmazonDynamoDBClient, Integer, Map<String, AttributeValue>>{
	
	@Override
	protected Map<String, AttributeValue> doInBackground(AmazonDynamoDBClient... params) {
		Log.d("ReadDynamoDB", "CONRAD: doing in background...");
		try {
			HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
			key.put("BusID", new AttributeValue().withN("1"));
			key.put("latlong", new AttributeValue().withS("both"));
			GetItemRequest getItemRequest = new GetItemRequest()
			.withTableName("BuffBus")
			.withKey(key)
			.withAttributesToGet(Arrays.asList("lat", "long"));
			
			GetItemResult result = params[0].getItem(getItemRequest);
			Log.d("ReadDynamoDB", "CONRAD: about to return result from DB read");
			
			return result.getItem();
			
		} catch (AmazonServiceException ase) {
			System.err.println("Failed to retrieve item");
			// Get specific error information
	        System.err.println("Error Message:    " + ase.getMessage());
	        System.err.println("HTTP Status Code: " + ase.getStatusCode());
	        System.err.println("AWS Error Code:   " + ase.getErrorCode());
	        System.err.println("Error Type:       " + ase.getErrorType());
	        System.err.println("Request ID:       " + ase.getRequestId());
		}
		
		Log.d("ReadDynamoDB", "CONRAD: something went wrong, returning null");
		return null;
	}
	
	protected void onPostExecute(Map<String, AttributeValue> BusCoords)
	{
		if(BusCoords != null)
		{
			Log.d("onPostExecute in AsyncTask", "CONRAD: Item retrieved...placing marker on map");
			Marker busMarker = MainActivity.mMap.addMarker(new MarkerOptions()
			.position(new LatLng(Float.parseFloat(BusCoords.get("lat").toString().substring(4,15)), Float.parseFloat(BusCoords.get("long").toString().substring(4,15))))
			.title("Bus1")
			.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
			// remove the old marker and update
			Marker oldMarker = MainActivity.busMarkerList.get(1);
			if(oldMarker != null)
			{
				oldMarker.remove();
			}
			MainActivity.busMarkerList.put(1, busMarker);			
		}
	}

}
