package com.uraal.cab.driver.geofence;
/**
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *//*

package com.noto.mapitrealtor.geofence;

import java.util.ArrayList;
import java.util.Map;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.noto.mapitrealtor.R;
import com.noto.mapitrealtor.baseClasses.BaseGeoFenceActivity;

*//**
 * Demonstrates how to create and remove geofences using the GeofencingApi. Uses an IntentService
 * to monitor geofence transitions and creates notifications whenever a device enters or exits
 * a geofence.
 *
 * This sample requires a device's Location settings to be turned on. It also requires
 * the ACCESS_FINE_LOCATION permission, as specified in AndroidManifest.xml.
 *
 * Note that this Activity implements ResultCallback<Status>, requiring that
 * {@code onResult} must be defined. The {@code onResult} runs when the result of calling
 * {@link GeofencingApi#addGeofences(GoogleApiClient, GeofencingRequest, PendingIntent)}  addGeofences()} or
 * {@link com.google.android.gms.location.GeofencingApi#removeGeofences(GoogleApiClient, java.util.List)}  removeGeofences()}
 * becomes available.
 *//*
public class MainActivity extends BaseGeoFenceActivity {

	//protected static final String TAG = "creating-and-monitoring-geofences";

	*//**
	 * Provides the entry point to Google Play services.
	 *//*
	// protected GoogleApiClient mGoogleApiClient;

	*//**
	 * The list of geofences used in this sample.
	 *//*
	// protected ArrayList<Geofence> mGeofenceList;

	*//**
	 * Used to keep track of whether geofences were added.
	 *//*
	//private boolean mGeofencesAdded;

	*//**
	 * Used when requesting to add or remove geofences.
	 *//*
	//  private PendingIntent mGeofencePendingIntent;

	*//**
	 * Used to persist application state about whether geofences were added.
	 *//*
	// private SharedPreferences mSharedPreferences;

	// Buttons for kicking off the process of adding or removing geofences.
	private Button mAddGeofencesButton;
	private Button mRemoveGeofencesButton;
	private ImageView imageView1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		//initControls(savedInstanceState);
		// Get the UI widgets.
		mAddGeofencesButton = (Button) findViewById(R.id.add_geofences_button);
		mRemoveGeofencesButton = (Button) findViewById(R.id.remove_geofences_button);
		imageView1 = (ImageView) findViewById(R.id.imageView1);

		setButtonsEnabledState();
	}  



	*//**
	 * Adds geofences, which sets alerts to be notified when the device enters or exits one of the
	 * specified geofences. Handles the success or failure results returned by addGeofences().
	 *//*
	public void addGeofencesButtonHandler(View view) {
		if (!mGoogleApiClient.isConnected()) {
			Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
			return;
		}

		try {
			LocationServices.GeofencingApi.addGeofences(
					mGoogleApiClient,
					// The GeofenceRequest object.
					getGeofencingRequest(),
					// A pending intent that that is reused when calling removeGeofences(). This
					// pending intent is used to generate an intent when a matched geofence
					// transition is observed.
					getGeofencePendingIntent()
					).setResultCallback(this); // Result processed in onResult().
		} catch (SecurityException securityException) {
			// Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
			logSecurityException(securityException);
		}
	}

	*//**
	 * Removes geofences, which stops further notifications when the device enters or exits
	 * previously registered geofences.
	 *//*
	public void removeGeofencesButtonHandler(View view) {
		if (!mGoogleApiClient.isConnected()) {
			Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			// Remove geofences.
			LocationServices.GeofencingApi.removeGeofences(
					mGoogleApiClient,
					// This is the same pending intent that was used in addGeofences().
					getGeofencePendingIntent()
					).setResultCallback(this); // Result processed in onResult().
		} catch (SecurityException securityException) {
			// Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
			logSecurityException(securityException);
		}
	} 

	*//**
	 * Ensures that only one button is enabled at any time. The Add Geofences button is enabled
	 * if the user hasn't yet added geofences. The Remove Geofences button is enabled if the
	 * user has added geofences.
	 *//*
	private void setButtonsEnabledState() {
		if (mGeofencesAdded) {
			mAddGeofencesButton.setEnabled(false);
			mRemoveGeofencesButton.setEnabled(true);
		} else {
			mAddGeofencesButton.setEnabled(true);
			mRemoveGeofencesButton.setEnabled(false);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		int resultCode = (Integer)intent.getIntExtra("resultCode",-1);
		switch (resultCode) {
		case GeofenceTransitionsIntentService.STATUS_RUNNING:
			Log.i("STATUS_RUNNING", "STATUS_RUNNING");

			break;
		case GeofenceTransitionsIntentService.STATUS_FINISHED:
			//String results = intent.getString("result");
			Toast.makeText(this, "View changed ", Toast.LENGTH_LONG).show();
			imageView1.setVisibility(View.VISIBLE);
			Log.i("STATUS_FINISHED", "Result");
			break;
		case GeofenceTransitionsIntentService.STATUS_ERROR:
			Log.i("STATUS_ERROR", "STATUS_ERROR");

			break;
		}
		
		setButtonsEnabledState();
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub

	}
	
	 *//**
     * Runs when the result of calling addGeofences() and removeGeofences() becomes available.
     * Either method can complete successfully or with an error.
     *
     * Since this activity implements the {@link ResultCallback} interface, we are required to
     * define this method.
     *
     * @param status The Status returned through a PendingIntent when addGeofences() or
     *               removeGeofences() get called.
     *//*
    public void onResult(Status status) {
        if (status.isSuccess()) {
            // Update state and save in shared preferences.
            mGeofencesAdded = !mGeofencesAdded;
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(Constants.GEOFENCES_ADDED_KEY, mGeofencesAdded);
            editor.commit();

            // Update the UI. Adding geofences enables the Remove Geofences button, and removing
            // geofences enables the Add Geofences button.
            setButtonsEnabledState();

            Toast.makeText(
                    this,
                    getString(mGeofencesAdded ? R.string.geofences_added :
                            R.string.geofences_removed),
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            // Get the status code for the error and log it using a user-friendly message.
            String errorMessage = GeofenceErrorMessages.getErrorString(this,
                    status.getStatusCode());
            Log.e(TAG, errorMessage);
        }
    }
    
    @Override
	public void onConnected(Bundle connectionHint) {
		Log.i(TAG, "Connected to GoogleApiClient=");
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// Refer to the javadoc for ConnectionResult to see what error codes might be returned in
		// onConnectionFailed.
		Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// The connection to Google Play services was lost for some reason. 
		Log.i(TAG, "Connection suspended="+cause);

		// onConnected() will be called again automatically when the service reconnects
	}



	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void setValueOnUI() {
	}
}
*/