package com.uraal.cab.driver.baseClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.LatLng;
import com.uraal.cab.driver.geofence.GeofenceTransitionsIntentService;


public abstract class BaseGeoFenceActivity extends BaseFragmentActivity  implements ConnectionCallbacks, OnConnectionFailedListener, ResultCallback<Status>, LocationListener,
OnMapClickListener, OnMarkerDragListener{
	protected static final String TAG = "creating-and-monitoring-geofences";
	/**
	 * Provides the entry point to Google Play services.
	 */
	protected GoogleApiClient mGoogleApiClient;

	/**
	 * The list of geofences used in this sample.
	 */
	protected ArrayList<Geofence> mGeofenceList;

	/**
	 * Used to keep track of whether geofences were added.
	 */
	protected boolean mGeofencesAdded;

	/**
	 * Used when requesting to add or remove geofences.
	 */
	protected PendingIntent mGeofencePendingIntent;

	/**
	 * Used to persist application state about whether geofences were added.
	 */
	protected SharedPreferences mSharedPreferences;

	protected HashMap<String, LatLng> geoFenceMap = new HashMap<String, LatLng>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);	

	}

	@Override
	protected void initControls(Bundle savedInstanceState) {
		// Empty list for storing geofences.
		mGeofenceList = new ArrayList<Geofence>();

		// Initially set the PendingIntent used in addGeofences() and removeGeofences() to null.
		mGeofencePendingIntent = null;

		// Retrieve an instance of the SharedPreferences object.
		mSharedPreferences = getSharedPreferences(com.uraal.cab.driver.geofence.Constants.SHARED_PREFERENCES_NAME, MODE_PRIVATE);

		// Get the value of mGeofencesAdded from SharedPreferences. Set to false as a default.
		mGeofencesAdded = mSharedPreferences.getBoolean(com.uraal.cab.driver.geofence.Constants.GEOFENCES_ADDED_KEY, false);
		//setButtonsEnabledState();

		// Get the geofences used. Geofence data is hard coded in this sample.
		populateGeofenceList(geoFenceMap);

		// Kick off the request to build GoogleApiClient.
		buildGoogleApiClient();        
	}

	/**
	 * Builds a GoogleApiClient. Uses the {@code #addApi} method to request the LocationServices API.
	 */
	protected synchronized void buildGoogleApiClient() {
		mGoogleApiClient = new GoogleApiClient.Builder(this)
		.addConnectionCallbacks(this)
		.addOnConnectionFailedListener(this)
		.addApi(LocationServices.API)
		.build();
	}

	@Override
	protected void onResume() {		
		super.onResume();		
	}

	@Override
	protected void onStart() {
		super.onStart();
		if(mGoogleApiClient == null)
			buildGoogleApiClient();  
		mGoogleApiClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if(mGoogleApiClient == null)
			buildGoogleApiClient();  
		mGoogleApiClient.disconnect();
	}

	/**
	 * Builds and returns a GeofencingRequest. Specifies the list of geofences to be monitored.
	 * Also specifies how the geofence notifications are initially triggered.
	 */
	protected GeofencingRequest getGeofencingRequest() {
		GeofencingRequest.Builder builder = new GeofencingRequest.Builder();

		// The INITIAL_TRIGGER_ENTER flag indicates that geofencing service should trigger a
		// GEOFENCE_TRANSITION_ENTER notification when the geofence is added and if the device
		// is already inside that geofence.
		builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);

		// Add the geofences to be monitored by geofencing service.
		builder.addGeofences(mGeofenceList);

		// Return a GeofencingRequest.
		return builder.build();
	}


	/**
	 * Adds geofences, which sets alerts to be notified when the device enters or exits one of the
	 * specified geofences. Handles the success or failure results returned by addGeofences().
	 */
	public void addGeofencesButtonHandler() {
		if (!mGoogleApiClient.isConnected()) {
			Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
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

	/**
	 * Removes geofences, which stops further notifications when the device enters or exits
	 * previously registered geofences.
	 */
	public void removeGeofencesButtonHandler() {
		if (!mGoogleApiClient.isConnected()) {
			Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
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

	protected void logSecurityException(SecurityException securityException) {
		Log.e(TAG, "Invalid location permission. " +
				"You need to use ACCESS_FINE_LOCATION with geofences", securityException);
	}

	/**
	 * Gets a PendingIntent to send with the request to add or remove Geofences. Location Services
	 * issues the Intent inside this PendingIntent whenever a geofence transition occurs for the
	 * current list of geofences.
	 *
	 * @return A PendingIntent for the IntentService that handles geofence transitions.
	 */
	protected PendingIntent getGeofencePendingIntent() {
		// Reuse the PendingIntent if we already have it.
		if (mGeofencePendingIntent != null) {
			return mGeofencePendingIntent;
		}
		
		Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
		
		// We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling
		// addGeofences() and removeGeofences().
		return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	/**
	 * This sample hard codes geofence data. A real app might dynamically create geofences based on
	 * the user's location.
	 */
	public void populateGeofenceList(HashMap<String, LatLng> geoFenceMap) {
		for (Map.Entry<String, LatLng> entry : geoFenceMap.entrySet()) {

			mGeofenceList.add(new Geofence.Builder()
			// Set the request ID of the geofence. This is a string to identify this
			// geofence.
			.setRequestId(entry.getKey())

			// Set the circular region of this geofence.
			.setCircularRegion(
					entry.getValue().latitude,
					entry.getValue().longitude,
					com.uraal.cab.driver.geofence.Constants.GEOFENCE_RADIUS_IN_METERS
					)

					// Set the expiration duration of the geofence. This geofence gets automatically
					// removed after this period of time.
					.setExpirationDuration(com.uraal.cab.driver.geofence.Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)

					// Set the transition types of interest. Alerts are only generated for these
					// transition. We track entry and exit transitions in this sample.
					.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
							Geofence.GEOFENCE_TRANSITION_EXIT)

							// Create the geofence.
							.build());
		}
	}

	/**
	 * Ensures that only one button is enabled at any time. The Add Geofences button is enabled
	 * if the user hasn't yet added geofences. The Remove Geofences button is enabled if the
	 * user has added geofences.
	 */
	/*private void setButtonsEnabledState() {
		if (mGeofencesAdded) {
			mAddGeofencesButton.setEnabled(false);
			mRemoveGeofencesButton.setEnabled(true);
		} else {
			mAddGeofencesButton.setEnabled(true);
			mRemoveGeofencesButton.setEnabled(false);
		}
	}*/
}
