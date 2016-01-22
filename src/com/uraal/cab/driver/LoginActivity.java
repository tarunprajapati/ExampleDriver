package com.uraal.cab.driver;

import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiConfiguration.Status;
import android.os.Bundle;
import android.os.StrictMode;
import android.service.carrier.CarrierMessagingService.ResultCallback;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.uraal.cab.driver.baseClasses.BaseFragmentActivity;
import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.beanClasses.User;
import com.uraal.cab.driver.networkTask.ApiManager;
import com.uraal.cab.driver.networkTask.URLsClass;
import com.uraal.cab.driver.notification.CommonUtilities;
import com.uraal.cab.driver.preference.MySharedPreferences;
import com.uraal.cab.driver.utilities.ToastCustomClass;
import com.uraal.cab.driver.utilities.UtilsClass;



public class LoginActivity extends BaseFragmentActivity implements OnClickListener 
{
	// for Google plus
	public static final int RC_SIGN_IN = 0;
	public static final String TAG = "Login";
	private static final int PROFILE_PIC_SIZE = 400;
	private GoogleApiClient mGoogleApiClient;
	private boolean mIntentInProgress;
	private boolean mSignInClicked;
	private ConnectionResult mConnectionResult;
	
	private ImageView fb_login;
	private EditText login_username;
	private EditText login_password;
	private Button button_login;
	private TextView login_forgotpass;
	private EditText edit_emailad;
	AlertDialog alert;
	String registrationId="";
	private ProgressDialog dialog;
	private TextView signUp;

	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		// getSupportActionBar().hide();
		enableBackButton();
		setTitleOnAction(getResources().getString(R.string.login_upercase));
		setContentView(R.layout.activity_login);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		initControls(savedInstanceState);

		registrationId = UtilsClass.getGCMRegistrationID(LoginActivity.this);
		/*if(registrationId.equalsIgnoreCase("")){
			dialog = UtilsClass.showProgress(this, "", "Please wait...");
			registerReceiver(mHandleMessageReceiver, new IntentFilter(CommonUtilities.DISPLAY_MESSAGE_ACTION));
		}*/
	}

	@Override
	protected void initControls(Bundle savedInstanceState) {
	
		
		login_username = (EditText) findViewById(R.id.login_username);
		login_password = (EditText) findViewById(R.id.login_password);
		button_login = (Button) findViewById(R.id.button_login);
		login_forgotpass =  (TextView) findViewById(R.id.login_forgotpass);
		signUp =  (TextView) findViewById(R.id.signUp);
		login_forgotpass.setPaintFlags(login_forgotpass.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
		signUp.setPaintFlags(signUp.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
		//login_username.setText(MySharedPreferences.getInstance().getString(this, Constants.phone, ""));
		//login_username.setEnabled(false);
		signUp.setOnClickListener(this);
		fb_login.setOnClickListener(this);
		
		button_login.setOnClickListener(this);
		login_forgotpass.setOnClickListener(this);


	

		
		//Log.i("Android Device model", UtilsClass.getDeviceModel(this));
		//Log.i("Android id", UtilsClass.getAndroidI(this));
		//Log.i("Android device id", UtilsClass.getAndroidDevic(this));

		//Log.i("Android Device VERSION ", UtilsClass.getDeviceSDKVersion(this));
		//Log.i("Android Device VERSION Name", UtilsClass.getDeviceSDKVersionName(this));
		String text = "<font color='#142D8A'>"+"<a href=''>"+"Forgot your password?</a></font>";
		login_forgotpass.setText(Html.fromHtml(text));


		// initialize gmail
		/*mGoogleApiClient = new GoogleApiClient.Builder(this)
		.addConnectionCallbacks(this)
		.addOnConnectionFailedListener(this).addApi(Plus.API)
		.addScope(Plus.SCOPE_PLUS_LOGIN).build();*/
		
	}

	
	@Override
	protected void setValueOnUI() {

	}

	@Override
	public void onBackPressed() {
		//startMyActivity(LoginSignupActivity.class, null);
		super.onBackPressed();		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action buttons
		switch (item.getItemId()) {
		case android.R.id.home:		
			onBackPressed();
			return true;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	
		case R.id.button_login:
			
			if (login_username.getText().toString().equalsIgnoreCase("")) {
				ToastCustomClass.showToast(this, getResources().getString(R.string.val_ph_username_not_null));
			}else if (login_password.getText().toString().trim().equalsIgnoreCase("")) {
				ToastCustomClass.showToast(this, getResources().getString(R.string.val_password_not_null));
			}else if (UtilsClass.isConnectingToInternet(this)) {				
				String email = login_username.getText().toString().trim();
				String pass = login_password.getText().toString().trim();
				//ph_no = UtilsClass.withoutZeroPH(ph_no);
				/*int length = ph_no.length();				
				//ph_no = ph_no.replaceFirst("^0+(?!$)", "");
				if (length >= 8) {*/
				if(UtilsClass.isEmailValid(email) && pass.length() > 4){
					user = null;
					user = new User();
					user.setEmail(email);
					user.setPassword(pass);
					user.setLoginType(Constants.normal_login_type);
					user.setGcmId(registrationId);
					ApiManager.getInstance().login(this, user); 
					//ApiManager.getInstance().login(LoginActivity.this, email, pass, registrationId, Constants.login_type_normal);
				}
				else
					ToastCustomClass.showToast(this, "Please enter valid email or password");
				//}
			}else {    
				UtilsClass.plsStartInternet(this);
			}
			break;
		case R.id.login_forgotpass:
			forgotPasswordDialog();
			break;
		case R.id.btn_cancel:
			alert.cancel();
			break;
		case R.id.btn_request:
			if (edit_emailad.getText().toString().equalsIgnoreCase("")) {
				ToastCustomClass.showToast(this, getResources().getString(R.string.val_email_not_null));
			}else if (!UtilsClass.isEmailValid(edit_emailad.getText().toString().trim())) {
				ToastCustomClass.showToast(this, getResources().getString(R.string.val_email_valid));
			}else if (UtilsClass.isConnectingToInternet(this)) {
				String[] url = {URLsClass.baseUrl+URLsClass.service_type_forget_pass};
				HashMap<String, String> params = new HashMap<String, String>();
				params.put(Constants.API_KEY, Constants.API_KEY_VALUE);
				params.put(Constants.emailId, edit_emailad.getText().toString().trim());
				serviceCaller(LoginActivity.this, url, params, true, false, Constants.forgetPass, true);
			}else {
				UtilsClass.plsStartInternet(this);
			}
			break;
		}
	}

	

	@Override
	protected Boolean callBackFromApi(Object object, Activity act, int requstCode) {
		if(super.callBackFromApi(object, act, requstCode)){
			JSONObject jsonObject = (JSONObject) object;
			int dataFlow = jsonObject.optInt(Constants.dataToFollow);
			String statusText = jsonObject.optString(Constants.message);
			if(dataFlow == 1){
				JSONObject data = jsonObject.optJSONObject(Constants.data);
				switch (requstCode) {
				case Constants.login:	
					if(data != null){
						MySharedPreferences.getInstance().saveUserInfo(LoginActivity.this, data);
						startMyActivity(HomeActivity.class, null);
						finish();
					}
					break;
				case Constants.forgetPass:
					alert.cancel();
					UtilsClass.showAlertDialog(this, statusText, "Message");
					break;
				}
			}
		}
		
		return true;
	}

	
	public void forgotPasswordDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
		LayoutInflater inflater = getLayoutInflater();
		View view = inflater.inflate(R.layout.forget_password_dialog, null);
		builder.setView(view);
		edit_emailad = (EditText) view.findViewById(R.id.edit_emailad);
		TextView btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
		TextView btn_request = (TextView) view.findViewById(R.id.btn_request);

		btn_cancel.setOnClickListener(this);
		btn_request.setOnClickListener(this);

		alert = builder.create();
		alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		alert.show();
		/*WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(alert.getWindow().getAttributes());
		lp.gravity = Gravity.BOTTOM;
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		alert.show();
		alert.getWindow().setAttributes(lp);
		alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));*/
	}

	BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			//APA91bEpGpzMUpDOd_obs9Gp6V0YDSFJHQWCrTmVBnMN7vAVyDrb2MogO1LOn7TqFpP_GocH1CJOGDHY0VfDXkUby59VU41N-eHIceJmQtJOeP7ukm87KNhlM0s7TfXaReRFDXC4EAzF
			registrationId = intent.getExtras().getString(CommonUtilities.EXTRA_MESSAGE);
			Log.i("Registriation page receiver", "=="+registrationId);

			if (registrationId.equals("")) {
				GCMRegistrar.register(LoginActivity.this, CommonUtilities.SENDER_ID);
			}else{
				dialog.dismiss();
			}
		}
	};
	private ProgressDialog pDialog;

	@Override
	protected void onDestroy() {
		try {
			unregisterReceiver(mHandleMessageReceiver);
			GCMRegistrar.onDestroy(this);
		} catch (Exception e) {
			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
		}
		super.onDestroy();
	}

	

	

	
	
	public void showLoader(){
		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();

	}

	public void dismissLoader(){
		if(pDialog != null)
			pDialog.dismiss();

		pDialog = null;
	}

}
