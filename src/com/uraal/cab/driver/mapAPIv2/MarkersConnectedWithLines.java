package com.uraal.cab.driver.mapAPIv2;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.uraal.cab.driver.R;
import com.uraal.cab.driver.baseClasses.BaseGeoFenceActivity;
import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.beanClasses.EndRoute;
import com.uraal.cab.driver.geofence.GeofenceErrorMessages;
import com.uraal.cab.driver.geofence.GeofenceTransitionsIntentService;
import com.uraal.cab.driver.utilities.MapUtilities;


public class MarkersConnectedWithLines extends BaseGeoFenceActivity{

	private GoogleApiClient mGoogleApiClient;
	private GoogleMap map;
	ArrayList<Marker> markers = new ArrayList<Marker>();
	// Polyline polyline;
	private LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_markers_connected_with_lines);
		enableBackButton();
		setMapProperties();

		// polyline = map.addPolyline(new PolylineOptions().add(new
		// LatLng(26.93434, 73.2323)));
		// polyline = map.addPolyline(new PolylineOptions());
		Bundle bundle = getIntent().getBundleExtra(Constants.bundleArg);
		if (bundle != null) {
			ArrayList<EndRoute> list = (ArrayList<EndRoute>) bundle.getSerializable(Constants.list);
			addPinMarkers(list);
		}
	}

	@Override
	protected void onResume() {	
		super.onResume();
		findViewById(R.id.start_route).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 
				removeGeofencesButtonHandler();
				addGeofencesButtonHandler();
			}
		});
	}
	// onMapClickListener
	@Override
	public void onMapClick(LatLng point) {
		/*
		 * Marker marker = map.addMarker(new
		 * MarkerOptions().position(point).draggable(true));
		 * markers.add(marker); Toast.makeText(getApplicationContext(),
		 * "Number of markers: "+markers.size(), Toast.LENGTH_SHORT).show();
		 * 
		 * //draw poly line between markers ArrayList<LatLng> latlngs = new
		 * ArrayList<LatLng>(); for (Marker m: markers){
		 * latlngs.add(m.getPosition()); }
		 * 
		 * polyline.setPoints(latlngs);
		 */
	}

	/*
	 * private void addPinMarkers(ArrayList<EndRoute> list){
	 * 
	 * if(list != null && list.size() > 0){ for(EndRoute route : list){ LatLng
	 * latlng = new LatLng(route.getLat(), route.getLng()); Marker marker =
	 * map.addMarker(new MarkerOptions().position(latlng).draggable(false));
	 * markers.add(marker); Toast.makeText(getApplicationContext(),
	 * "Number of markers: "+markers.size(), Toast.LENGTH_SHORT).show(); }
	 * //draw poly line between markers
	 * 
	 * }
	 * 
	 * ArrayList<LatLng> latlngs = new ArrayList<LatLng>(); for (Marker m:
	 * markers){ latlngs.add(m.getPosition()); }
	 * 
	 * //polyline.setPoints(latlngs); DrawRoute drawRoute = new DrawRoute();
	 * drawRoute.drawRoute(this, map, latlngs);
	 * map.moveCamera(CameraUpdateFactory
	 * .newLatLng(markers.get(0).getPosition()));
	 * map.animateCamera(CameraUpdateFactory.zoomTo(12)); }
	 */

	private void addPinMarkers(ArrayList<EndRoute> list) {
		
		if (list != null && list.size() > 0) {
			int i = 1;
			for (EndRoute route : list) {
				LatLng latlng = new LatLng(route.getLat(), route.getLng());
				geoFenceMap.put(route.getTitle()+""+route.getAddress().substring(0, 20), latlng);
				Marker marker = MapUtilities.putCustomMarker(this, map, latlng,	i);
				i++;
				// Marker marker = map.addMarker(new
				// MarkerOptions().position(latlng).draggable(false));
				markers.add(marker);
				Toast.makeText(getApplicationContext(),
						"Number of markers: " + markers.size(),
						Toast.LENGTH_SHORT).show();
			}
			//draw poly line between markers
			
			initControls(null);				
		}

		ArrayList<LatLng> latlngs = new ArrayList<LatLng>();
		for (Marker m : markers) {
			latlngs.add(m.getPosition());
		}

		// polyline.setPoints(latlngs);
		DrawRoute drawRoute = new DrawRoute();
		drawRoute.drawRoute(this, map, latlngs);
		map.moveCamera(CameraUpdateFactory.newLatLng(markers.get(0)
				.getPosition()));
		map.animateCamera(CameraUpdateFactory.zoomTo(12));
	}



	public void nextExample(View view) {

	}

	private void setMapProperties() {
		// Getting Google Play availability status
		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getBaseContext());

		if (status != ConnectionResult.SUCCESS) { // Google Play Services are
			// not available
			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
					requestCode);
			dialog.show();
		} else { // Google Play Services are available
			// Getting reference to SupportMapFragment of the activity_main
			SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);

			// Getting Map for the SupportMapFragment
			map = fm.getMap();

			// Enable MyLocation Button in the Map
			map.setMyLocationEnabled(true);

			// Getting LocationManager object from System Service
			// LOCATION_SERVICE
			locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			// Creating a criteria object to retrieve provider
			Criteria criteria = new Criteria();

			// Getting the name of the best provider
			String provider = locationManager.getBestProvider(criteria, true);

			// Getting Current Location From GPS
			Location location = locationManager.getLastKnownLocation(provider);

			if (location != null) {
				onLocationChanged(location);
			}

			locationManager.requestLocationUpdates(provider, 20000, 0, this);
			map.animateCamera(CameraUpdateFactory.zoomTo(16));

			map.setMyLocationEnabled(true);
			map.getUiSettings().setMapToolbarEnabled(false);
			map.setOnMapClickListener(this);
			map.setOnMarkerDragListener(this);
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// Draw the marker, if destination location is not set
		double mLatitude = location.getLatitude();
		double mLongitude = location.getLongitude();
		LatLng point = new LatLng(mLatitude, mLongitude);
		// mMarkerPoints.add(point);
		locationManager.removeUpdates(this);

		map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mLatitude,
				mLongitude)));
		//map.animateCamera(CameraUpdateFactory.zoomTo(12));
	}




	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action buttons
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return true;
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
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

	@Override
	protected void setValueOnUI() {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		int resultCode = (Integer)intent.getIntExtra("resultCode",-1);
		switch (resultCode) {
		case GeofenceTransitionsIntentService.STATUS_RUNNING:
			Log.i("STATUS_RUNNING", "STATUS_RUNNING");
			Toast.makeText(this, "View changed STATUS_RUNNING", Toast.LENGTH_LONG).show();
			break;
		case GeofenceTransitionsIntentService.STATUS_FINISHED:
			//String results = intent.getString("result");
			Toast.makeText(this, "View changed STATUS_FINISHED", Toast.LENGTH_LONG).show();
			//imageView1.setVisibility(View.VISIBLE);
			Log.i("STATUS_FINISHED", "Result");
			break;
		case GeofenceTransitionsIntentService.STATUS_ERROR:
			Log.i("STATUS_ERROR", "STATUS_ERROR");

			break;
		}
	}
	
	 /**
     * Runs when the result of calling addGeofences() and removeGeofences() becomes available.
     * Either method can complete successfully or with an error.
     *
     * Since this activity implements the {@link ResultCallback} interface, we are required to
     * define this method.
     *
     * @param status The Status returned through a PendingIntent when addGeofences() or
     *               removeGeofences() get called.
     */
    public void onResult(Status status) {
        if (status.isSuccess()) {
            // Update state and save in shared preferences.
            mGeofencesAdded = !mGeofencesAdded;
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(com.uraal.cab.driver.geofence.Constants.GEOFENCES_ADDED_KEY, mGeofencesAdded);
            editor.commit();

            // Update the UI. Adding geofences enables the Remove Geofences button, and removing
            // geofences enables the Add Geofences button.
            //setButtonsEnabledState();

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
}
