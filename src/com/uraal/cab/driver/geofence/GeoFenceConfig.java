package com.uraal.cab.driver.geofence;
/*package com.noto.mapitrealtor.geofence;

import java.util.ArrayList;
import java.util.Map;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.noto.mapitrealtor.R;

import dk.lowtide.MapAPIv2.MarkersConnectedWithLines;

public class GeoFenceConfig implements ConnectionCallbacks, OnConnectionFailedListener{

	protected static final String TAG = "GeoFenceConfig";
	Context context;
	
	 DownloadResultReceiver mReceiver;

	protected GoogleApiClient mGoogleApiClient;

	*//**
	 * The list of geofences used in this sample.
	 *//*
	protected ArrayList<Geofence> mGeofenceList;

	*//**
	 * Used to keep track of whether geofences were added.
	 *//*
	private boolean mGeofencesAdded;

	*//**
	 * Used when requesting to add or remove geofences.
	 *//*
	private PendingIntent mGeofencePendingIntent;

	*//**
	 * Used to persist application state about whether geofences were added.
	 *//*
	private SharedPreferences mSharedPreferences;

	// Buttons for kicking off the process of adding or removing geofences.
	public GeoFenceConfig(Context context , DownloadResultReceiver mReceiver) {

		this.context = context;
		this.mReceiver = mReceiver;

	}

	public  GoogleApiClient getGeoFenceConfig() {
		

        // Empty list for storing geofences.
        mGeofenceList = new ArrayList<Geofence>();

        // Initially set the PendingIntent used in addGeofences() and removeGeofences() to null.
        mGeofencePendingIntent = null;

        // Retrieve an instance of the SharedPreferences object.
        mSharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME,context.MODE_PRIVATE);

        // Get the value of mGeofencesAdded from SharedPreferences. Set to false as a default.
        mGeofencesAdded = mSharedPreferences.getBoolean(Constants.GEOFENCES_ADDED_KEY, false);

        // Get the geofences used. Geofence data is hard coded in this sample.
        populateGeofenceList();

        // Kick off the request to build GoogleApiClient.
        buildGoogleApiClient();
		return mGoogleApiClient;

	}
	
	*//**
     * This sample hard codes geofence data. A real app might dynamically create geofences based on
     * the user's location.
     *//*
    public void populateGeofenceList() {
        for (Map.Entry<String, LatLng> entry : Constants.BAY_AREA_LANDMARKS.entrySet()) {

            mGeofenceList.add(new Geofence.Builder()
                    // Set the request ID of the geofence. This is a string to identify this
                    // geofence.
                    .setRequestId(entry.getKey())

                    // Set the circular region of this geofence.
                    .setCircularRegion(
                            entry.getValue().latitude,
                            entry.getValue().longitude,
                            Constants.GEOFENCE_RADIUS_IN_METERS
                    )

                    // Set the expiration duration of the geofence. This geofence gets automatically
                    // removed after this period of time.
                    .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)

                    // Set the transition types of interest. Alerts are only generated for these
                    // transition. We track entry and exit transitions in this sample.
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                            Geofence.GEOFENCE_TRANSITION_EXIT)

                    // Create the geofence.
                    .build());
        }
    }
    *//**
     * Builds a GoogleApiClient. Uses the {@code #addApi} method to request the LocationServices API.
     *//*
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks((ConnectionCallbacks) context)
                .addOnConnectionFailedListener((OnConnectionFailedListener) context)
                .addApi(LocationServices.API)
                .build();
    }
    
    
    *//**
     * Adds geofences, which sets alerts to be notified when the device enters or exits one of the
     * specified geofences. Handles the success or failure results returned by addGeofences().
     *//*
    public void addGeofencesButtonHandler(View view) {
        if (!mGoogleApiClient.isConnected()) {
            Toast.makeText(context, context.getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
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
            ).setResultCallback((ResultCallback<Status>) context); // Result processed in onResult().
        } catch (SecurityException securityException) {
            // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
            logSecurityException(securityException);
        }
    }
    
    private void logSecurityException(SecurityException securityException) {
        Log.e(TAG, "Invalid location permission. " +
                "You need to use ACCESS_FINE_LOCATION with geofences", securityException);
    }

    *//**
     * Removes geofences, which stops further notifications when the device enters or exits
     * previously registered geofences.
     *//*
    public void removeGeofencesButtonHandler(View view) {
        if (!mGoogleApiClient.isConnected()) {
            Toast.makeText(context, context.getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            // Remove geofences.
            LocationServices.GeofencingApi.removeGeofences(
                    mGoogleApiClient,
                    // This is the same pending intent that was used in addGeofences().
                    getGeofencePendingIntent()
            ).setResultCallback((MarkersConnectedWithLines)context); // Result processed in onResult().
        } catch (SecurityException securityException) {
            // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
            logSecurityException(securityException);
        }
    }
    

    *//**
     * Gets a PendingIntent to send with the request to add or remove Geofences. Location Services
     * issues the Intent inside this PendingIntent whenever a geofence transition occurs for the
     * current list of geofences.
     *
     * @return A PendingIntent for the IntentService that handles geofence transitions.
     *//*
    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        
        Intent intent = new Intent(context, GeofenceTransitionsIntentService.class);
        intent.putExtra("receiver", mReceiver);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling
        // addGeofences() and removeGeofences().
        return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    
    *//**
    * Builds and returns a GeofencingRequest. Specifies the list of geofences to be monitored.
    * Also specifies how the geofence notifications are initially triggered.
    *//*
   private GeofencingRequest getGeofencingRequest() {
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
          //  setButtonsEnabledState();

            Toast.makeText(context,context.getString(mGeofencesAdded ? R.string.geofences_added :R.string.geofences_removed),Toast.LENGTH_SHORT).show();
        } else {
            // Get the status code for the error and log it using a user-friendly message.
            String errorMessage = GeofenceErrorMessages.getErrorString(context,status.getStatusCode());
            Log.e(TAG, errorMessage);
        }
    }

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}
  


}
*/