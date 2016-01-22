package com.uraal.cab.driver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.uraal.cab.driver.baseClasses.BaseFragmentActivity;
import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.baseClasses.MyApplication;
import com.uraal.cab.driver.preference.MySharedPreferences;


public class SplashActivity extends BaseFragmentActivity implements OnClickListener{
	private static String TAG = "Splash";	
	private static final int SPLASH_DURATION = 1000; // 2 seconds
	private RelativeLayout rel_top;
	private boolean flag;
	private Tracker mTracker;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		setContentView(R.layout.activity_splash);

		// Obtain the shared Tracker instance.
		//Get a Tracker (should auto-report)
		//((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);


		initControls(savedInstanceState);
		setValueOnUI();


		//Log.i("hii", null);
		//startMyActivity(HomeActivity.class, null);		
		//startHome();
		//startMyActivity(HomeActivityTest.class, null);
		//Log.i("Android Device VERSION Name", UtilsClass.getDeviceSDKVersionName(this));
		//UtilsClass.deviceVersionName();
		//UtilsClass.getIpAddress(this);
	}

	@Override
	protected void onStart() {	
		super.onStart();
		//Get an Analytics tracker to report app starts & uncaught exceptions etc.
		GoogleAnalytics.getInstance(this).reportActivityStart(this);
		
		getKeyHash();
	}

	private void getKeyHash(){
		try {
			PackageInfo info =  getPackageManager().getPackageInfo("com.uraal.cab", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				String sign=Base64.encodeToString(md.digest(), Base64.DEFAULT);
				Log.e("MY KEY HASH:", sign);
				Toast.makeText(getApplicationContext(),sign, Toast.LENGTH_LONG).show();
			}
		} catch (NameNotFoundException e) {
		} catch (NoSuchAlgorithmException e) {
		}
	}

	@Override
	protected void onStop() {	
		super.onStop();
		//Stop the analytics tracking
		GoogleAnalytics.getInstance(this).reportActivityStop(this);
	}

	@Override
	protected Boolean callBackFromApi(Object object, Activity act, int requstCode) {
		if(super.callBackFromApi(object, act, requstCode)){
			JSONObject jObj = ((JSONObject)object);
			int isValidUser = jObj.optInt(Constants.isValidUser);
			if(isValidUser == 0){
				//UtilsClass.showAlertDialog(this, jObj.optString(Constants.message), "Invalid user!");
				MySharedPreferences.getInstance().putBooleanKeyValue(this, Constants.VERIFY, false);
				MySharedPreferences.getInstance().putBooleanKeyValue(SplashActivity.this, Constants.LOGIN, false);
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
				alertBuilder.setTitle("Invalid user!");
				alertBuilder.setMessage(jObj.optString(Constants.message));

				alertBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						startHome();
					}
				});

				AlertDialog alertDialog = alertBuilder.create();
				alertDialog.show();
			}else{
				startHome();
			}
		}

		return true; 
	}


	@Override
	protected void initControls(Bundle savedInstanceState) {		
	}

	@Override
	protected void setValueOnUI() {
		/*View lay[] = {findViewById(R.id.imageView1)};
		UtilsClass.animationLittleUp(this, lay, 1000, 500);*/
		setHeightWidth();

		YoYo.with(Techniques.Shake).withListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator arg0) {}

			@Override
			public void onAnimationRepeat(Animator arg0) {}

			@Override
			public void onAnimationEnd(Animator arg0) {
				startHome();
			}

			@Override
			public void onAnimationCancel(Animator arg0) {}
		}).duration(2500).playOn(findViewById(R.id.imgLogo));
		//}
	}


	private void setHeightWidth() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int widthPixel = displayMetrics.widthPixels;
		int heightPixel = displayMetrics.heightPixels;
		//System.out.println("In Pixels Width : "+widthPixel+" Height : "+heightPixel);

		MyApplication.getApplication().setWidthPixel(widthPixel);
		MyApplication.getApplication().setHeightPixel(heightPixel);
	}

	/*@Override
	public void onClick(View v) {
		Bundle bundle = new Bundle();
		bundle.putInt(com.noto.mapitrealtor.baseClasses.Constants.position,(Integer) v.getTag());
		startMyActivity(HomeActivity.class, bundle);
		finish();
	}*/
	@Override
	public void onClick(View v) {

	}

	private void startHome(){		
		// LoginAct
		if (!MySharedPreferences.getInstance().getBoolean(SplashActivity.this, Constants.LOGIN, false)) {
			startMyActivity(LoginActivity.class, null);
			//startMyActivity(HomeActivity.class, null);
		}else {
			startMyActivity(HomeActivity.class, null);
		}
		finish();
	}

	@Override
	protected void onResume() {	
		super.onResume();
		Log.i(TAG, "Setting screen name: " + TAG);
		/*	mTracker.setScreenName("Image" + TAG);
		mTracker.send(new HitBuilders.ScreenViewBuilder().build());*/
	}
}

/*void registerReceiver() {
		IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
		filter.addDataScheme("package_name"); 
		ApplicationBroadcastService br = new ApplicationBroadcastService();
		registerReceiver(br, filter);
	}	*/

