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

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class DrawRoute {
	private GoogleMap map;
	private ProgressDialog progressDialog;
	public void drawRoute(Context context, GoogleMap map, ArrayList<LatLng> list){
		this.map = map;
		/*if(fromCurrentLocation || origin == null){
			origin = new LatLng(mLatitude, mLongitude);			
		}*/
		// Getting URL to the Google Directions API
		String url[] = new String[list.size()-1];
		for(int i=1;i<list.size();i++){
			url[i-1]= getDirectionsUrl(list.get(i-1), list.get(i));
		}
		
		try 
		{

			progressDialog = new ProgressDialog(context);
			progressDialog.setMessage("Loading route. Please wait!");
			progressDialog.setCancelable(false);
			if(!progressDialog.isShowing())
				progressDialog.show();
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		DownloadTask downloadTask = new DownloadTask();

		// Start downloading json data from Google Directions API
		downloadTask.execute(url);
	}


	/*public void drawRoute(GoogleMap map, LatLng origin, LatLng dest){
		this.map = map;
		if(fromCurrentLocation || origin == null){
			origin = new LatLng(mLatitude, mLongitude);			
		}
		// Getting URL to the Google Directions API
		String url = getDirectionsUrl(origin, dest);				

		DownloadTask downloadTask = new DownloadTask();

		// Start downloading json data from Google Directions API
		downloadTask.execute(url);
	}*/

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
	private class DownloadTask extends AsyncTask<String, Void, String[]>{			

		// Downloading data in non-ui thread
		@Override
		protected String[] doInBackground(String... url) {

			// For storing data from web service
			String data[] = new String[url.length];

			try{
				for(int i=0;i<url.length;i++){
					// Fetching the data from web service
					data[i] = downloadUrl(url[i]);
				}
			}catch(Exception e){
				Log.d("Background Task",e.toString());
			}
			return data;		
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String[] result) {			
			super.onPostExecute(result);			

			ParserTask parserTask = new ParserTask();
			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);
		}		
	}

	/** A class to parse the Google Directions in JSON format */
	private class ParserTask extends AsyncTask<String, Integer, List<List<List<HashMap<String,String>>>>>{

		private PolylineOptions lineOptions;
		private Polyline polyLine;

		// Parsing the data in non-ui thread    	
		@Override
		protected List<List<List<HashMap<String, String>>>> doInBackground(String... jsonData) {

			JSONObject jObject;	
			List<List<List<HashMap<String, String>>>> routes = new ArrayList<List<List<HashMap<String,String>>>>();			           

			try{
				for(int i=0;i<jsonData.length;i++){
					jObject = new JSONObject(jsonData[i]);
					DirectionsJSONParser parser = new DirectionsJSONParser();

					// Starts parsing data
					routes.add(parser.parse(jObject));  
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute( List<List<List<HashMap<String, String>>>> resultTop) {
			if(polyLine != null){
				polyLine.remove();
			}
			ArrayList<LatLng> points = null;
			lineOptions = new PolylineOptions();
			for(int k=0;k<resultTop.size();k++){
				List<List<HashMap<String, String>>> result = resultTop.get(k);
				// Traversing through all the routes
				for(int i=0;i<result.size();i++){
					points = new ArrayList<LatLng>();
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
					//lineOptions.width(5);
					//lineOptions.color(Color.BLUE);	
				}
			}

			// Drawing polyline in the Google Map for the i-th route
			try {
				if(lineOptions != null)
					polyLine = map.addPolyline(lineOptions);
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("E", "Map :"+map+"Lines :"+lineOptions);
			}	
			
			if(progressDialog != null){
				progressDialog.dismiss();
			}
		}			
	}   
}
