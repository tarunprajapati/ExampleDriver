package com.uraal.cab.driver.baseClasses;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uraal.cab.driver.R;
import com.uraal.cab.driver.networkTask.ApiResponse;
import com.uraal.cab.driver.networkTask.MyAsyncTask;
import com.uraal.cab.driver.networkTask.MyAsyncTask.CallBackListener;
import com.uraal.cab.driver.networkTask.URLsClass;
import com.uraal.cab.driver.utilities.ToastCustomClass;
import com.uraal.cab.driver.utilities.UtilsClass;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BaseFragmentActivity extends ActionBarActivity{
	protected final boolean isTest = false;
	protected abstract void initControls(Bundle savedInstanceState);
	protected abstract void setValueOnUI();
	public CharSequence mTitle;
	protected static String TAG = "Home";
	protected static final int requestLogin = 9034;
	protected static final int requestOrder = 332;
	protected static final int requestCart = 232;
	protected static final int requestWish = 233;
	private boolean isOnline = true;

	@Override
	protected void onStart() {	
		super.onStart();
		//addOnBackStackChangedLister();
	}

	protected Bundle getBundle(){
		return getIntent().getBundleExtra(Constants.bundleArg);
	}

	public void startMyActivity(Class targetActivity, Bundle myBundle){
		Intent intent = new Intent(this, targetActivity);
		if(myBundle != null)
			intent.putExtra(Constants.bundleArg, myBundle);
		startActivity(intent);
	}

	public void startMyActivityForResult(Class targetActivity, Bundle myBundle, int requestCode){
		Intent intent = new Intent(this, targetActivity);
		if(myBundle != null)
			intent.putExtra(Constants.bundleArg, myBundle);
		startActivityForResult(intent, requestCode);
	}

	public void setTextFaceFuturab(View view, String text){
		if(view instanceof EditText){
			((EditText)view).setText(text);
			((EditText)view).setTypeface(MyApplication.typeFaceOrgano);
		}else if(view instanceof TextView){
			((TextView)view).setText(text);
			((TextView)view).setTypeface(MyApplication.typeFaceSkipLegDay);
		}		
	}

	@SuppressLint("NewApi")
	public void setTitleOnAction(String text){
		ActionBar actionBar = getSupportActionBar();
		//actionBar.setIcon(R.drawable.email_icon);		
		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		if(text != null)
			mTitle = text;
		try {
			actionBar.setDisplayShowTitleEnabled(true);
			actionBar.setTitle(UtilsClass.setTitle(mTitle));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//actionBar.setIcon(R.drawable.ic_launcher);
		//actionBar.setIcon(null);
	}


	public void sendEmail(String emailAddress){
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{emailAddress});
		i.putExtra(Intent.EXTRA_SUBJECT, "Request to inquiry");
		i.putExtra(Intent.EXTRA_TEXT   , "Welcome...");
		try {
			startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(BaseFragmentActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}

	public void makeCall(String no) {
		//Log.i("Make call", no);

		Intent phoneIntent = new Intent(Intent.ACTION_CALL);
		phoneIntent.setData(Uri.parse("tel:"+no));

		try {
			startActivity(phoneIntent);
			//finish();
			//Log.i("Finished making a call...", "");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(BaseFragmentActivity.this, 
					"Call faild, please try again later.", Toast.LENGTH_SHORT).show();
		}
	}

	public void serviceCaller(final Fragment fragment, String urlsArr[], HashMap<String, String> params, boolean isPost, boolean isFileUploading, final int requstCode, boolean isShowProgress) {
		final String urls[] = urlsArr;

		CallBackListener callBackListener = new CallBackListener() {			
			@Override
			public void callback(Object object) {
				//===================
				//Live Code
				if(object == null)
					return;
				if(object instanceof ApiResponse){
					ApiResponse apiResponce = (ApiResponse) object;
					HashMap<String, String> mapOfResponce = apiResponce.getResponceMap();
					if(mapOfResponce != null && mapOfResponce.size() > 0){
						String res = mapOfResponce.get(urls[0]);
						if(res != null){
							JSONObject jOb = null;
							try {
								jOb = new JSONObject(res);							
								callBackFromApi(jOb, fragment, requstCode);
							} catch (JSONException e1) {
								e1.printStackTrace();	
							}
						}
						else{
							callBackFromApi(null, fragment, requstCode);
							ToastCustomClass.showToast(BaseFragmentActivity.this, apiResponce.getErrorMsg());
						}
					}
				}
			}
		};

		MyAsyncTask asy = new MyAsyncTask(fragment.getActivity(), urls, params,  "Loading..", isPost);
		asy.setCallBackListener(callBackListener);
		asy.isProgressBarShow = isShowProgress;
		asy.execute();
	}


	public void serviceCaller(final BaseFragmentActivity act, String urlsArr[], HashMap<String, String> params, boolean isPost, boolean isFileUploading, final int requstCode, boolean isShowProgress) {
		final String urls[] = urlsArr;
		CallBackListener callBackListener = new CallBackListener() {			
			@Override
			public void callback(Object object) {				
				//===================
				//Live Code
				if(object == null)
					return;
				if(object instanceof ApiResponse){
					ApiResponse apiResponce = (ApiResponse) object;
					HashMap<String, String> mapOfResponce = apiResponce.getResponceMap();
					if(mapOfResponce != null && mapOfResponce.size() > 0){
						String res = mapOfResponce.get(urls[0]);
						if(res != null){
							JSONObject jOb = null;
							try {
								jOb = new JSONObject(res);
								callBackFromApi(jOb, act, requstCode);
							} catch (JSONException e1) {
								e1.printStackTrace();
								try {
									JSONArray jArr = new JSONArray(res);
									callBackFromApi(jArr, act, requstCode);
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}				
						}
						else{
							callBackFromApi(null, act, requstCode);
						}
					}
				}
			}
		};

		MyAsyncTask asy = new MyAsyncTask(act, urls, params,  "Loading..", isPost);
		asy.setCallBackListener(callBackListener);
		asy.isProgressBarShow = isShowProgress;
		asy.execute();
	}



	//protected void callApi(){}
	protected Boolean callBackFromApi(Object object, Fragment fragment, int requstCode){
		return isOk(object);
	}

	private boolean isOk(Object object){
		if(object != null){			
			try {
				if(object instanceof JSONObject){					
					//jObRoot = jArr.getJSONObject(0);
					JSONObject jOb =(JSONObject) object;					
					if(jOb != null && jOb.optInt(URLsClass.status, URLsClass.NOTOK) == URLsClass.OK){
						//ToastCustomClass.showToast(this, ":)");
						isOnline = true;						
					}else{
						ToastCustomClass.showToast(this, jOb.optString(Constants.message));
						isOnline = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				isOnline = false;
			}		
		}else{
			isOnline = false;			
		}

		invalidateOptionsMenu();
		return isOnline;
	} 

	protected Boolean callBackFromApi(Object object, Activity act, int requstCode){
		return isOk(object);
	}


	public void addFragement(Fragment fragment, String tagValue, boolean addToBackStack){
		//MyApplication.getApplication().addFragment(fragment);
		FragmentTransaction ft = getFragmentTransaction();
		ft.add(R.id.container, fragment, tagValue);
		if(addToBackStack)
			ft.addToBackStack(tagValue);		
		ft.commit();		
	}

	public FragmentTransaction getFragmentTransaction(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		//transaction.setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_up);
		transaction.setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_up, R.anim.slide_out_down, R.anim.slide_in_up);
		return transaction;
	}

	public android.app.FragmentTransaction getFragmentTransactionNormal(){
		android.app.FragmentManager fragmentManager = getFragmentManager();
		android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();		
		return transaction;
	}

	public void addFragement(Fragment fragment, String tag){		
		FragmentTransaction transaction = getFragmentTransaction();
		transaction.add(R.id.container, fragment, tag);
		transaction.addToBackStack(null);		
		transaction.commit();		
	}

	public void addWithoutAnimationFragement(Fragment fragment, String tag, String tagValue, boolean addToBackStack){
		//MyApplication.getApplication().addFragment(fragment);
		FragmentTransaction ft = getFragmentNormalTransaction();
		ft.add(R.id.container, fragment, tag);
		if(addToBackStack)
			ft.addToBackStack(tag);		
		ft.commit();		
	}

	public FragmentTransaction getFragmentNormalTransaction(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		//transaction.setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_up);
		//transaction.setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_up, R.anim.slide_out_down, R.anim.slide_in_up);
		return transaction;
	}


	public void addOnBackStackChangedLister(){
		getSupportFragmentManager().addOnBackStackChangedListener(
				new FragmentManager.OnBackStackChangedListener() {
					public void onBackStackChanged() {
						//Log.i(TAG, "Added");
						if(getSupportFragmentManager().getBackStackEntryCount() > 0){							
							//System.out.println("onBack Of BaseFragment > 0");
						}else{
							//System.out.println("onBack Of BaseFragment == 0");
							List<Fragment> list = getSupportFragmentManager().getFragments();
							if(list != null && list.size() > 0){
								setTitleOnAction(((BaseFragmentActivity)list.get(0).getActivity()).TAG);
							}
							//System.out.println("Frgs list "+list);
						}								
					}
				});
	}

	public void removeOnBackStackChangedListener(){
		getSupportFragmentManager().removeOnBackStackChangedListener(new OnBackStackChangedListener() {

			@Override
			public void onBackStackChanged() {
				//Log.i(TAG, "Removed");
			}
		});
	}

	public void replaceFragement(Fragment fragment, String tagValue){
		FragmentTransaction transaction = getFragmentTransaction();
		transaction.replace(R.id.container, fragment, tagValue).commit();
	}

	public void replaceFragement(Fragment fragment, String tagValue, boolean addToBackStack){
		FragmentTransaction transaction = getFragmentTransaction();
		if(addToBackStack)
			transaction.addToBackStack(tagValue);	
		transaction.replace(R.id.container, fragment, tagValue).commit();
	}

	public void replaceFragement(Fragment fragment){
		FragmentTransaction transaction = getFragmentTransaction();
		transaction.replace(R.id.container, fragment).commit();
	}

	public void replaceFragement(android.app.Fragment fragment){
		android.app.FragmentTransaction transaction = getFragmentTransactionNormal();
		transaction.replace(R.id.container, fragment).commit();
	}  


	public void removeFragement(Fragment fragment){
		FragmentTransaction transaction = getFragmentTransaction();
		//transaction.setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_down);
		transaction.remove(fragment).commit();
		//getSupportFragmentManager().popBackStack();		
		boolean isAddBackStatckAllowed = transaction.isAddToBackStackAllowed();
	}	
	public void removeTopFragement(){				
		getSupportFragmentManager().popBackStack();	
	}

	public BaseFragment getTopFragment(boolean isRefreshTitle){
		BaseFragment fr = null;
		try {
			android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
			int count = fm.getBackStackEntryCount();
			if(count > 0){
				BackStackEntry en = fm.getBackStackEntryAt(count-1);
				String name = en.getName();
				fr = (BaseFragment) fm.findFragmentByTag(name);
				if(isRefreshTitle)
					setTitleOnAction(name);
			}
			return fr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getBackStackCount(){		
		try {
			android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
			int count = fm.getBackStackEntryCount();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Fragment getFragmentByTag(String tAG) {
		return getSupportFragmentManager().findFragmentByTag(tAG);
	}

	/*	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if(featureId == Window.FEATURE_ACTION_BAR && menu != null){
			if(menu.getClass().getSimpleName().equals("MenuBuilder")){
				try{
					Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);					
				}
				catch(NoSuchMethodException e){
					Log.e(TAG, "onMenuOpened", e);
				}
				catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}*/

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.base_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem item = menu.findItem(R.id.menu_item_home);		
		if(isOnline)
			item.setIcon(R.drawable.home_online);
		else
			item.setIcon(R.drawable.home_offline);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		case R.id.menu_item_home:
			finish();
			return true;		
		}
		return super.onOptionsItemSelected(item);
	}*/
	public boolean enableBackButton() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeAsUpIndicator(R.drawable.icn_back_home);
		actionBar.setDisplayHomeAsUpEnabled(true);		
		return true;
	}

	protected boolean disableBackButton() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeAsUpIndicator(R.drawable.icon_menu);
		actionBar.setDisplayHomeAsUpEnabled(true); 
		/*ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);	*/	
		return true;
	}	

	@Override
	protected void onResume() {	
		super.onResume();
		invalidateOptionsMenu();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(getTopFragment(true) == null)
			finish();

		/*}else if (getTopFragment().onBackPressedListener()) {
			super.onBackPressed();
		}*/
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;				
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		//menu.clear();
		return super.onCreateOptionsMenu(menu);
	}
}
