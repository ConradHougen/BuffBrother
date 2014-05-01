/*package com.example.buffbrother;

import android.content.SharedPreferences;
import android.util.Log;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

public class AmazonSharedPreferencesWrapper {
	private static final String AWS_ACCESS_KEY = "AKIAIAHBD5GTZA5EMGUA";
	private static final String AWS_SECRET_KEY = "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";

	public static AWSCredentials getCredentialsFromSharedPreferences( SharedPreferences sharedPreferences ) {
		String accessKey = AmazonSharedPreferencesWrapper.getValueFromSharedPreferences( sharedPreferences, AWS_ACCESS_KEY );
		String secretKey = AmazonSharedPreferencesWrapper.getValueFromSharedPreferences( sharedPreferences, AWS_SECRET_KEY );
		Log.d("CredentialsWrapper", "CONRAD: getting aws credentials");
		return new BasicAWSCredentials( accessKey, secretKey );
	}
	
	protected static String getValueFromSharedPreferences( SharedPreferences sharedPreferences, String key ) {
		return sharedPreferences.getString( key, null );
	}

}
*/
