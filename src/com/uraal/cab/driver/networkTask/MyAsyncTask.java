package com.uraal.cab.driver.networkTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

public class MyAsyncTask implements URLsClass {	
	private CallBackListener callBackListener = null;
	private ParsingClass parsingClass = null;	
	public boolean isProgressBarShow = true;
	private boolean isPost = true;
	private HashMap<String, String> params;
	private Context context;
	private String[] urls;
	private String progressMsg;	

	public CallBackListener getCallBackListener() {
		return callBackListener;
	}

	public void setCallBackListener(CallBackListener callBackListener) {
		this.callBackListener = callBackListener;
	}

	public MyAsyncTask(@NonNull Context context, @NonNull String[] urls, HashMap<String, String> params, String progressMsg, boolean isPost) {
		this.context = context;
		this.urls = urls;
		this.params = params;
		this.progressMsg = progressMsg;
		this.isPost = isPost;
		
		parsingClass = ParsingClass.getInstance(context);			
	}

	public void execute() {		
		if (isProgressBarShow) {
			try {
				ProgressDialog progressDialog = new ProgressDialog(context);
				progressDialog.setMessage(progressMsg);
				progressDialog.setCancelable(false);
				if (!progressDialog.isShowing())
					progressDialog.show();
				
				doInBackground(progressDialog);
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	protected ApiResponse doInBackground(ProgressDialog progressDialog) {
		ApiResponse apiResponse = new ApiResponse();
		for (String url : urls) {
			apiResponse = parsingClass.startUpConfigrationOfParsingPost(url, params, apiResponse, isPost, callBackListener, progressDialog);
		}
		return apiResponse;
	}

	public interface CallBackListener {
		public void callback(Object object);
	}

	/*public ApiResponse fileUploadMethod(String string,
			HashMap<String, String> params2, ApiResponse apiResponse,
			boolean isPost2) {

		MultipartEntity multipartEntity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		if (params != null && params.size() > 0) {
			Log.i("fileUploadMethod", params2.toString());
			Set<String> keySet = params.keySet();
			Iterator<String> iterator = keySet.iterator();
			MCrypt cifrar = new MCrypt();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if (key.equalsIgnoreCase(Constants.IMAGE)) {
					String file = (String) params.get(key);
					if (!file.equals("")) {
						File file2 = new File(file);
						multipartEntity.addPart(key, new FileBody(file2));
					}

					// multipartEntity.addPart(key, new ByteArrayBody((byte[])
					// params.get(key),"image.jpg"));
				} else {
					String keyValue = String.valueOf(params.get(key));
					try {
						//keyValue = MCrypt.bytesToHex(cifrar.encrypt(keyValue));
						multipartEntity.addPart(key, new StringBody(keyValue));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			try {
				multipartEntity.writeTo(bytes);
				String content = bytes.toString();
				Log.i("MULTPART", content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			apiResponse = postUrlResponse(string, multipartEntity, apiResponse);
			Log.i("fileUploadMethod", "==fileUploadMethod==" + string
					+ multipartEntity.toString());

		}
		return apiResponse;
	}*/

	/*public ApiResponse postUrlResponse(String url,
			MultipartEntity nameValuePairs, ApiResponse apiResponse) {
		Log.i("postUrlResponse", "==postUrlResponse");
		try {

			HttpPost post = new HttpPost(url);
			post.setEntity(nameValuePairs);
			HttpClient client = new DefaultHttpClient();// httpParams
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			apiResponse = convertStreamToString(entity.getContent(),
					apiResponse, url);

		} catch (ConnectTimeoutException cte) {
			// TODO: handle exception
			Log.i("INTERNET EXCEPTION 2", cte.getMessage());
			apiResponse.setErrorMsg(cte.getMessage());
			return apiResponse;

		} catch (Exception e) {

			Log.i("INTERNET EXCEPTION 1", e.getMessage());
			apiResponse.setErrorMsg(e.getMessage());
			return apiResponse;
			// Log.i("INTERNET EXCEPTION 2", e.getMessage().toString());
		}
		return apiResponse;

	}*/

	private ApiResponse convertStreamToString(InputStream is,
			ApiResponse apiResponse, String url) {
		Log.i("convertStreamToString", "==convertStreamToString");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is),
				8 * 1024);
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
		Log.i("convertStreamToString", "==" + sb.toString());
		apiResponse.addResponce(url, sb.toString());
		return apiResponse;
	}

}

/*
 * package com.jaipurnews.dis.networkTask;
 * 
 * import java.util.HashMap;
 * 
 * import android.app.Activity; import android.app.ProgressDialog; import
 * android.content.Context; import android.os.AsyncTask; import
 * android.support.v4.app.FragmentActivity;
 * 
 * import com.jaipurnews.dis.MyApplication;
 * 
 * public class MyAsyncTask extends AsyncTask<String, Void, ApiResponse>
 * implements URLsClass { private Context context = null; private String
 * progressMsg = null; private ProgressDialog progressDialog; private
 * CallBackListener callBackListener = null; private ParsingClass parsingClass=
 * null; //private int parsingType = 1; public boolean IsProgressBarShow = true;
 * private boolean isPost = false; private HashMap<String, Object> params;
 * 
 * public CallBackListener getCallBackListener() { return callBackListener; }
 * 
 * public void setCallBackListener(CallBackListener callBackListener) {
 * this.callBackListener = callBackListener; }
 * 
 * 
 * public MyAsyncTask(Context context , HashMap<String, Object> params, String
 * progressMsg, Boolean isPost) { this.context = context; this.progressMsg =
 * progressMsg; //this.parsingType = parsingType; parsingClass =
 * ParsingClass.getInstance(context); this.isPost = isPost; this.params =
 * params; }
 * 
 * @Override protected void onPreExecute() { super.onPreExecute();
 * if(IsProgressBarShow) { try { if(context instanceof FragmentActivity){
 * progressDialog = new ProgressDialog((FragmentActivity)context); }else{
 * progressDialog = new ProgressDialog((Activity)context); }
 * progressDialog.setMessage(progressMsg); progressDialog.setCancelable(false);
 * if(!progressDialog.isShowing()) progressDialog.show(); } catch (Exception e)
 * { e.printStackTrace(); } catch (Throwable e) { e.printStackTrace(); } } }
 * 
 * @Override protected ApiResponse doInBackground(String... arg0) { ApiResponse
 * apiResponse = null;
 * 
 * apiResponse = MyApplication.getApplication().getApisResponce(); for(String
 * string : arg0) { apiResponse =
 * parsingClass.startUpConfigrationOfParsingPost(string, params, apiResponse,
 * isPost); } return apiResponse; }
 * 
 * @Override protected void onPostExecute(ApiResponse result) {
 * super.onPostExecute(result); if(progressDialog != null) { try {
 * progressDialog.dismiss(); } catch (Throwable e) { e.printStackTrace(); } }
 * 
 * if(callBackListener != null) callBackListener.callback(result); }
 * 
 * public interface CallBackListener { public void callback(Object object); } }
 */