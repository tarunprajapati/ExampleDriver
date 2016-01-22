package com.uraal.cab.driver.networkTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;


public class RestClient {

//	private static ArrayList<String> shouldEncript;
	private final RequestMethod GET = null;
	private final RequestMethod POST = null;
	private ArrayList<NameValuePair> params;
	private ArrayList<NameValuePair> headers;


	private String url;

	private int responseCode;
	private String message;

	private String response;
	private DefaultHttpClient client;
	private InputStream instream;

	public InputStream getInstream() 
	{
		return instream;
	}


	public String getResponse() 
	{
		return response;
	}

	public String getErrorMessage() 
	{
		return message;
	}

	public int getResponseCode() 
	{
		return responseCode;
	}

	public RestClient(String url) 
	{
		this.url = url;
		params = new ArrayList<NameValuePair>();
		headers = new ArrayList<NameValuePair>();


		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		ConnManagerParams.setMaxTotalConnections(params, 10);

		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

		final ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);

		client = new DefaultHttpClient(cm, params);
	}

	public void AddParam(String name, String value) 
	{
		params.add(new BasicNameValuePair(name, value));
	}

	public void AddHeader(String name, String value) 
	{
		headers.add(new BasicNameValuePair(name, value));
	}

	public void Execute(RequestMethod method) throws Exception 
	{
		switch (method) 
		{
		case GET: 
		{
			// add parameters
			String combinedParams = "";
			if (!params.isEmpty()) {
				combinedParams += "?";
				for (NameValuePair p : params) 
				{
					String paramString ="";
					paramString = p.getName() + "="	+ URLEncoder.encode(p.getValue(), "UTF-8");
					if (combinedParams.length() > 1) 
					{
						combinedParams += "&" + paramString;
					} 
					else 
					{
						combinedParams += paramString;
					}
				}
			}

			HttpGet request = new HttpGet(url + combinedParams);
			// add headers
			for (NameValuePair h : headers) 
			{
				//client.getCredentialsProvider().setCredentials(new AuthScope(null, -1),  new UsernamePasswordCredentials("username", "password"));
				request.addHeader(h.getName(), h.getValue());
			}
			System.out.println("GET URL :"+url + combinedParams);
			executeRequest(request, url);
			break;
		}
		case POST: {
			//System.out.println("IN POST");
			HttpPost request = new HttpPost(url);

			// add headers
			for (NameValuePair h : headers) {
				request.addHeader(h.getName(), h.getValue());
			}

			if (!params.isEmpty()) {
				request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}

			System.out.println("POST URL:"+url + params);
			executeRequest(request, url);
			break;
		}
		}
	}

	private void executeRequest(HttpUriRequest request, String url) 
	{
		HttpResponse httpResponse;

		try 
		{
			httpResponse = client.execute(request);
			responseCode = httpResponse.getStatusLine().getStatusCode();
			message = httpResponse.getStatusLine().getReasonPhrase();

			HttpEntity entity = httpResponse.getEntity();

			if (entity != null) 
			{
				instream = entity.getContent();
				response = convertStreamToString(instream);
				System.out.println("Responce "+response);
				// Closing the input stream will trigger connection release
				//instream.close();
			}

		} 
		catch (ClientProtocolException e) 
		{
			client.getConnectionManager().shutdown();
			e.printStackTrace();
			return;
		} 
		catch (IOException e) 
		{
			client.getConnectionManager().shutdown();
			e.printStackTrace();
			return;
		}
	}

	private static String convertStreamToString(InputStream is) 
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try 
		{
			while ((line = reader.readLine()) != null) 
			{
				sb.append(line + "\n");
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			//System.out.println("In IO exception");
		} 
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				is.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				//System.out.println("In IO exception2");
			}

		}
		return sb.toString();
	}
}
