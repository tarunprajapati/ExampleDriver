package com.uraal.cab.driver.networkTask;

import java.util.HashMap;
import java.util.Map;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.uraal.cab.driver.networkTask.MyAsyncTask.CallBackListener;


@TargetApi(Build.VERSION_CODES.KITKAT)
public class ParsingClass  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9898989898l;
	private static final String AUTH_TOKEN_KEY = "authtoken";
	//private static ParsingClass parsingClass=null;
	Context context;
	RequestQueue queue;
	//int methodpost=Request.Method.POST;
	//int methodget=Request.Method.GET;
	//int method;
	//String responcemain;
	private RestClient restClient;


	public static ParsingClass getInstance(Context context){
		//if(parsingClass == null)
		ParsingClass parsingClass = new ParsingClass(context);
		return parsingClass;
	}

	private ParsingClass(Context context)
	{
		this.context = context;
	}

	public ApiResponse startUpConfigrationOfParsingPost(final String strUrl, final HashMap<String, String> params, final ApiResponse apiResponce, boolean isPost, final CallBackListener callBackListener, final ProgressDialog progressDialog)
	{		
		if(Is_Internet_Available_Class.internetIsAvailable(context))
		{			
			queue = Volley.newRequestQueue(context);
			// Post Method HttpPost			
			try 
			{
				int method;
				if(isPost)
					method=Request.Method.POST;
				else
					method=Request.Method.GET;
				
				
				Log.d("Url :=", strUrl);
				if(params != null && params.size() > 0){
					Log.d("Params :=", params.toString());
					
					StringRequest req = new StringRequest(method, strUrl, new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							Log.d("Response :=", response);
							apiResponce.addResponce(strUrl, response);
							
							callBack(progressDialog, callBackListener, apiResponce);
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							error.printStackTrace();
							Log.e("error :=", error.toString());
							apiResponce.setErrorMsg(error.getMessage());
							
							callBack(progressDialog, callBackListener, apiResponce);
						}
					}) {
						@Override
						protected Map<String, String> getParams() throws AuthFailureError {
							return params;
						}
					};
					queue.add(req);
				}
			}						
			catch (Exception e2) 
			{			
				e2.printStackTrace();
				apiResponce.setErrorMsg(e2.getMessage());
				return apiResponce;
			}
			return apiResponce;
		}
		else
		{
			//ToastCustomClass.showToast(context, "No Internet connection found.\n Please start your internet!", 4000).show();	    
			//alertWarningMsg(context);
			apiResponce.setErrorMsg("No Internet connection found.\n Please start your internet!");
			return apiResponce;
		}
	}
	
	public void callBack(ProgressDialog progressDialog, CallBackListener callBackListener, ApiResponse apiResponce){
		dismissProgress(progressDialog);
		
		if (callBackListener != null)
			callBackListener.callback(apiResponce);
	}
	
	public void dismissProgress(ProgressDialog progressDialog){
		if (progressDialog != null && progressDialog.isShowing()) {
			try {
				progressDialog.dismiss();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	/*	public ApiResponse startUpConfigrationOfParsingPost(String strUrl, HashMap<String, String> params, ApiResponse apiResponce, boolean isPost)
	{		
		if(Is_Internet_Available_Class.internetIsAvailable(context))
		{


			// Post Method HttpPost			
			try 
			{				
				//System.out.println("Print inside Parsing API calling time : "+strUrl);
				restClient = new RestClient(strUrl);				
				try
				{					
					//System.out.println("After Full URL Encoding is : "+strUrl);
					// Post Method HttpPost 
					try 
					{
						if(params != null && params.size() >0){
							System.out.println("Params :"+params.toString());
							MCrypt cifrar = new MCrypt();
							Set<String> keySet = params.keySet();
							Iterator<String> iterator = keySet.iterator();
							while(iterator.hasNext()){
								String key = iterator.next();								
								try {									
									String keyValue = String.valueOf(params.get(key));
								//	System.out.println("String encrypt: "+keyValue);	
									  //   keyValue = MCrypt.bytesToHex(cifrar.encrypt(keyValue));
									//System.out.println("String encrypt: "+keyValue);									
									//System.out.println("String decrypted: "+cifrar.decrypt(keyValue));
									restClient.AddParam(key, keyValue);
								} catch (Exception e) {
									e.printStackTrace();
								}								
							}
						}

						if(isPost)
							restClient.Execute(RequestMethod.POST);
						else
							restClient.Execute(RequestMethod.GET);
					} 
					catch (Exception e2) 
					{			
						e2.printStackTrace();
						apiResponce.setErrorMsg(e2.getMessage());
						return apiResponce;
					}

					String response = restClient.getResponse();
					apiResponce.addResponce(strUrl, response);


					if(response != null && response.length() > 0){
						JSONArray jsonArray = new JSONArray(response);
						for(int index = 0; index < jsonArray.length(); index++){
							JSONObject obj = (JSONObject) jsonArray.get(index);
							String id = (String) obj.get(Constants.category_id);
							String category = (String) obj.get("category");							
						}						
					}
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					apiResponce.setErrorMsg(e.getMessage());
					return apiResponce;
				}
			} 
			catch (Exception e) 
			{		
				e.printStackTrace();
				apiResponce.setErrorMsg(e.getMessage());
				return apiResponce;
			}

			return apiResponce;
		}
		else
		{
			//ToastCustomClass.showToast(context, "No Internet connection found.\n Please start your internet!", 4000).show();	    
			//alertWarningMsg(context);
			apiResponce.setErrorMsg("No Internet connection found.\n Please start your internet!");
			return apiResponce;
		}
	}*/
}



