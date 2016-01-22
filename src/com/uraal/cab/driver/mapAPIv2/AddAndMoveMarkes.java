package com.uraal.cab.driver.mapAPIv2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.uraal.cab.driver.R;
import com.uraal.cab.driver.baseClasses.BaseFragmentActivity;
import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.beanClasses.EndRoute;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class AddAndMoveMarkes extends BaseFragmentActivity implements LocationListener {
	private EndRoute EndRoute;
	private GoogleMap map;
	private double mLatitude;
	private double mLongitude;
	private MyCustomDialog dialog;
	protected Marker marker;
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_and_move_markes);
		
		enableBackButton();

		setTitleOnAction("Pick Location");

		Bundle bundle = getIntent().getBundleExtra(Constants.bundleArg);
		if(bundle != null){
			EndRoute = (EndRoute) bundle.getSerializable(Constants.object);
			position = bundle.getInt(Constants.position);
		}else{
			EndRoute = new EndRoute();
		}

		setMapProperties();

		setMapListeners();

		if(EndRoute.getLat() != 0.0){					
			drawMarker(false, new LatLng(EndRoute.getLat(), EndRoute.getLng()), EndRoute.getAddress());
		}
	}

	private void setMapProperties(){
		// Getting Google Play availability status
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

		if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
			dialog.show();
		}else { // Google Play Services are available
			// Getting reference to SupportMapFragment of the activity_main
			SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

			// Getting Map for the SupportMapFragment
			map = fm.getMap();

			// Enable MyLocation Button in the Map
			map.setMyLocationEnabled(true);
			map.getUiSettings().setMapToolbarEnabled(false);
			// Getting LocationManager object from System Service LOCATION_SERVICE
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			// Creating a criteria object to retrieve provider
			Criteria criteria = new Criteria();

			// Getting the name of the best provider
			String provider = locationManager.getBestProvider(criteria, true);

			// Getting Current Location From GPS
			Location location = locationManager.getLastKnownLocation(provider);

			if(location!=null){
				onLocationChanged(location);
			}

			locationManager.requestLocationUpdates(provider, 20000, 0, this);
		}		
	}

	private void drawMarker(Boolean isCurrentLocation, LatLng dest, String title){
		// Creating MarkerOptions
		MarkerOptions options = new MarkerOptions();

		// Setting the position of the marker
		options.position(dest);
		if(title != null)
			options.title(title);

		/*if(mMarkerPoints.size()==1){
			options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
		}else if(mMarkerPoints.size()==2){*/
		if(!isCurrentLocation)
			options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
		//}

		// Add new marker to the Google Map Android API V2
		map.addMarker(options);	

		LatLng origin = new LatLng(mLatitude, mLongitude);


		// Getting URL to the Google Directions API
		String url = getDirectionsUrl(origin, dest);				

		DownloadTask downloadTask = new DownloadTask();

		// Start downloading json data from Google Directions API
		downloadTask.execute(url);
	}

	private void setMapListeners(){
		//add maplisteners
		map.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(final Marker marker) {
				// Already map contain destination location	
				marker.showInfoWindow();
				return true;
			}
		});

		map.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng point) {
				FragmentManager fm = getSupportFragmentManager();	
				map.clear();
				EndRoute.setLat(point.latitude);
				EndRoute.setLng(point.longitude);			
				drawMarker(false, point, null);				
			}
		});

		// show a dialog to set title on the marker
		map.setOnMarkerClickListener(new OnMarkerClickListener() {



			@Override
			public boolean onMarkerClick(Marker marker) {
				if(marker == null || marker.getTitle() == null || marker.getTitle().isEmpty()){

					double latitude = EndRoute.getLat();
					double longitude = EndRoute.getLng();
					LocationAddress.getAddressFromLocation(latitude, longitude,	getApplicationContext(), new GeocoderHandler());
					AddAndMoveMarkes.this.marker = marker;				
				}				
				return false;
			}
		});

		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker marker) {
				MyCustomDialog dialog = new MyCustomDialog(AddAndMoveMarkes.this, marker, EndRoute, position);				
				dialog.show(getSupportFragmentManager(), "CustomDialog");
			}
		});	

		map.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {

			@Override
			public boolean onMyLocationButtonClick() {
				Location location = map.getMyLocation();
				if (location != null) {
					mLatitude = location.getLatitude();
					mLongitude = location.getLongitude();
					if(EndRoute.getLat() != 0.0){		
						drawMarker(false, new LatLng(EndRoute.getLat(), EndRoute.getLng()), EndRoute.getAddress());
					}
				}
				return false;
			}
		});
	}

	@Override
	public void onLocationChanged(Location location) {
		// Draw the marker, if destination location is not set
		mLatitude = location.getLatitude();
		mLongitude = location.getLongitude();
		LatLng point = new LatLng(mLatitude, mLongitude);
		//mMarkerPoints.add(point);

		map.moveCamera(CameraUpdateFactory.newLatLng(point));
		//map.animateCamera(CameraUpdateFactory.zoomTo(12));        
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	protected void initControls(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setValueOnUI() {
		// TODO Auto-generated method stub

	}	

	private void drawRoute(Boolean fromCurrentLocation, LatLng origin, LatLng dest){
		if(fromCurrentLocation || origin == null){
			origin = new LatLng(mLatitude, mLongitude);			
		}
		// Getting URL to the Google Directions API
		String url = getDirectionsUrl(origin, dest);				

		DownloadTask downloadTask = new DownloadTask();

		// Start downloading json data from Google Directions API
		downloadTask.execute(url);
	}



	/*	system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
	system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
	system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");*/

	private String getDirectionsUrl(LatLng origin,LatLng dest){

		// Origin of EndRoute
		String str_origin = "origin="+origin.latitude+","+origin.longitude;

		// Destination of EndRoute
		String str_dest = "destination="+dest.latitude+","+dest.longitude;			

		// Sensor enabled
		String sensor = "sensor=false";			

		// Building the parameters to the web service
		String parameters = str_origin+"&"+str_dest+"&"+sensor;

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;		

		return url;
	}

	/** A method to download json data from url */
	private String downloadUrl(String strUrl) throws IOException{
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try{
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url 
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url 
			urlConnection.connect();

			// Reading data from url 
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

			StringBuffer sb  = new StringBuffer();

			String line = "";
			while( ( line = br.readLine())  != null){
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		}catch(Exception e){
			Log.d("Exception while downloading url", e.toString());
		}finally{
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}



	/** A class to download data from Google Directions URL */
	private class DownloadTask extends AsyncTask<String, Void, String>{			

		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {

			// For storing data from web service
			String data = "";

			try{
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			}catch(Exception e){
				Log.d("Background Task",e.toString());
			}
			return data;		
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {			
			super.onPostExecute(result);			

			ParserTask parserTask = new ParserTask();

			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);

		}		
	}

	/** A class to parse the Google Directions in JSON format */
	private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

		private PolylineOptions lineOptions;
		private Polyline polyLine;

		// Parsing the data in non-ui thread    	
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

			JSONObject jObject;	
			List<List<HashMap<String, String>>> routes = null;			           

			try{
				jObject = new JSONObject(jsonData[0]);
				DirectionsJSONParser parser = new DirectionsJSONParser();

				// Starts parsing data
				routes = parser.parse(jObject);    
			}catch(Exception e){
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			if(polyLine != null){
				polyLine.remove();
			}
			ArrayList<LatLng> points = null;
			// Traversing through all the routes
			if(result != null){
				for(int i=0;i<result.size();i++){
					points = new ArrayList<LatLng>();
					lineOptions = new PolylineOptions();

					// Fetching i-th EndRoute
					List<HashMap<String, String>> path = result.get(i);

					// Fetching all the points in i-th EndRoute
					for(int j=0;j<path.size();j++){
						HashMap<String,String> point = path.get(j);					

						double lat = Double.parseDouble(point.get("lat"));
						double lng = Double.parseDouble(point.get("lng"));
						LatLng position = new LatLng(lat, lng);	

						points.add(position);						
					}

					// Adding all the points in the EndRoute to LineOptions
					lineOptions.addAll(points);
					lineOptions.width(2);
					lineOptions.color(Color.RED);	
				}

				// Drawing polyline in the Google Map for the i-th EndRoute
				try {
					if(lineOptions != null)
						polyLine = map.addPolyline(lineOptions);
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("E", "Map :"+map+"Lines :"+lineOptions);
				}
			}
		}			
	}   

	/*public void nextExample(View view){
		Intent intent = new Intent();
		intent.putExtra(Constants.object, EndRoute);		
		setResult(Activity.RESULT_OK, intent);
		finish();
		//startActivity(new Intent(this, MarkersConnectedWithLines.class));
	}*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action buttons
		switch(item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return true;
	}

	private class GeocoderHandler extends Handler {
		@Override
		public void handleMessage(Message message) {
			String locationAddress;
			switch (message.what) {
			case 1:
				Bundle bundle = message.getData();
				locationAddress = bundle.getString("address");
				break;
			default:
				locationAddress = null;
			}		

			EndRoute.setAddress(locationAddress);
			dialog = new MyCustomDialog(AddAndMoveMarkes.this, marker, EndRoute, position);			
			try {
				dialog.show(getSupportFragmentManager(), "CustomDialog");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
