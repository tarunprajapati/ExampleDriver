package com.uraal.cab.driver.mapAPIv2;
/*package dk.lowtide.MapAPIv2;

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

import android.animation.Animator;
import android.app.Dialog;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.inzaana.todo.R;
import com.inzaana.todo.baseClasses.BaseFragmentActivity;
import com.inzaana.todo.beanClasses.LatLonObject;

public class UsingPins extends BaseFragmentActivity implements LocationListener {

	protected static final String TAG = null;
//	private TextView tv_name;
//	private TextView tv_add;
//	private TextView tv_dis;
	private ArrayList<Marker> list;
	private GoogleMap map;
	//private ImageView img_call;
	//private ImageView img_email;
	//private ImageView img_route;
	private ArrayList<LatLonObject> listPin;
	double mLatitude=0;
	double mLongitude=0;
	public Polyline polyLine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_and_move_markes);
		//tv_name = (TextView) findViewById(R.id.tv_name);
		//tv_add = (TextView) findViewById(R.id.tv_add);
		//tv_dis = (TextView) findViewById(R.id.tv_dis);
		//img_call = (ImageView) findViewById(R.id.img_call);
		//img_email = (ImageView) findViewById(R.id.img_email);
		//img_route = (ImageView) findViewById(R.id.img_route);

		
		img_route.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LatLng latLng = (LatLng)arg0.getTag();
				drawRoute(true, null, latLng);
			}
		});

		//SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		//map = mapFrag.getMap();
		//map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		//map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		setMapProperties();

		addPins(map);
	}

	private void setMapProperties(){
		// Getting Google Play availability status
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

		if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
			dialog.show();

		}else { // Google Play Services are available
			// Initializing 
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


			map.setOnMarkerClickListener(new OnMarkerClickListener() {

				@Override
				public boolean onMarkerClick(final Marker marker) {
					showPopup(marker);
					return true;
				}
			});
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.using_pins, menu);
		return true;
	}

	// button clicked - move to another activity!
	public void nextExample(View view){ 
		startActivity(new Intent(getApplicationContext(), AddAndMoveMarkes.class));
	}

	private ArrayList<Marker> addPins(GoogleMap map){
		list = new ArrayList<Marker>();
		listPin = new ArrayList<Pin>();
		Pin pin  = null;
		LatLngBounds.Builder builder = new LatLngBounds.Builder();
		Marker marker = null;
		for(int i=0;i<20;i++){
			pin = new Pin();
			pin.setLatLng(new LatLng(26.903759+(i),75.784550+(i)));
			pin.setTitle("Title "+Integer.valueOf(i).byteValue());
			pin.setPhoneNo("9603484049");
			pin.setEmail("tarun@mgail.com");
			listPin.add(pin);

			MarkerOptions markerOption = new MarkerOptions();
			markerOption.position(pin.getLatLng());
			markerOption.title(pin.getTitle());			
			markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location));

			marker = map.addMarker(markerOption);
			list.add(marker);
		}

		showPopup(list.get(0));

		for (Marker mar : list) {
			builder.include(mar.getPosition());
		}

		LatLngBounds bounds = builder.build();
		int padding = 0; // offset from edges of the map in pixels
		CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
		map.animateCamera(cu);

		return list;
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

	private void showPopup(final Marker marker){
		int markerId = -1;				
		String id = marker.getId();
		Log.i(TAG, "Marker id: " + id);
		for(int i=0; i<list.size(); i++) {
			markerId = i;
			Marker m = list.get(i);
			if(m.getId().equals(id)){
				img_call.setTag(listPin.get(i).getPhoneNo());
				img_email.setTag(listPin.get(i).getEmail());
				img_route.setTag(listPin.get(i).getLatLng());
				break;
			}
		}

		marker.showInfoWindow();		
		//map.animateCamera(CameraUpdateFactory.zoomTo(9));
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 8f));
		YoYo.with(Techniques.ZoomOutDown)
		.duration(1000)
		.interpolate(new AccelerateDecelerateInterpolator())
		.withListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				tv_dis.setText(distance(mLatitude, mLongitude, marker.getPosition().latitude, marker.getPosition().longitude, 'K')+" KM");
				tv_name.setText(marker.getTitle());

				YoYo.with(Techniques.ZoomInUp).duration(1000).playOn(findViewById(R.id.dialogV));
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				Toast.makeText(UsingPins.this, "canceled", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		})
		.playOn(findViewById(R.id.dialogV));	
	}

	private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		dist = roundMyData(dist, 2);
		return (dist);
	}

	public static double roundMyData(double Rval, int numberOfDigitsAfterDecimal) {
		double p = (float)Math.pow(10,numberOfDigitsAfterDecimal);
		Rval = Rval * p;
		double tmp = Math.floor(Rval);
		System.out.println("~~~~~~tmp~~~~~"+tmp);
		return (double)tmp/p;
	}


	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

		system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
	system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
	system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");

	private String getDirectionsUrl(LatLng origin,LatLng dest){

		// Origin of route
		String str_origin = "origin="+origin.latitude+","+origin.longitude;

		// Destination of route
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

	*//** A method to download json data from url *//*
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



	*//** A class to download data from Google Directions URL *//*
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

	*//** A class to parse the Google Directions in JSON format *//*
	private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

		private PolylineOptions lineOptions;

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
			for(int i=0;i<result.size();i++){
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();

				// Fetching i-th route
				List<HashMap<String, String>> path = result.get(i);

				// Fetching all the points in i-th route
				for(int j=0;j<path.size();j++){
					HashMap<String,String> point = path.get(j);					

					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);	

					points.add(position);						
				}

				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(2);
				lineOptions.color(Color.RED);	
			}

			// Drawing polyline in the Google Map for the i-th route
			try {
				if(lineOptions != null)
					polyLine = map.addPolyline(lineOptions);
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("E", "Map :"+map+"Lines :"+lineOptions);
			}							
		}			
	}   

		private void drawMarker(LatLng point){
		mMarkerPoints.add(point);

		// Creating MarkerOptions
		MarkerOptions options = new MarkerOptions();

		// Setting the position of the marker
		options.position(point);

	 *//** 
	 * For the start location, the color of marker is GREEN and
	 * for the end location, the color of marker is RED.
	 *//*
		if(mMarkerPoints.size()==1){
			options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
		}else if(mMarkerPoints.size()==2){
			options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
		}

		// Add new marker to the Google Map Android API V2
		mGoogleMap.addMarker(options);		
	}

	@Override
	public void onLocationChanged(Location location) {
		// Draw the marker, if destination location is not set
		mLatitude = location.getLatitude();
		mLongitude = location.getLongitude();
		LatLng point = new LatLng(mLatitude, mLongitude);

		map.moveCamera(CameraUpdateFactory.newLatLng(point));
		map.animateCamera(CameraUpdateFactory.zoomTo(12));        
		//drawMarker(point);			
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

	@Override
	protected void callBackFromApi(Object object) {
		// TODO Auto-generated method stub

	}
}
*/