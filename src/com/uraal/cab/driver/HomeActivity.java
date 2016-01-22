package com.uraal.cab.driver;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.uraal.cab.driver.baseClasses.BaseFragmentActivity;
import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.fragments.NavigationDrawerFragment;
import com.uraal.cab.driver.utilities.ToastCustomClass;

public class HomeActivity extends BaseFragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
	


	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	private static final int TIME_INTERVAL = 1000; // # milliseconds, desired time passed between two back presses.
	

	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_home);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		//mTitle = getTitle();

		
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,	(DrawerLayout) findViewById(R.id.drawer_layout));	
	
	}


	@Override
	public void onNavigationDrawerItemSelected(int position) {
		invalidateOptionsMenu();
		//ToastCustomClass.showToast(this, "onNavigationDrawerItemSelected=="+position);
		switch (position) {
		case 0:	
			//title = CommunityFragment.TAG;			
			//replaceFragement(CommunityFragment.newInstance(null), CommunityFragment.TAG, isFirst);
			
			break;
		case 1:
			//title = FeedsFragment.TAG;
			//replaceFragement(FeedsFragment.newInstance(null), FeedsFragment.TAG, isFirst);
			break;
		case 2:	
			//title = ChannelFragment.TAG;
			//replaceFragement(ChannelFragment.newInstance(null), ChannelFragment.TAG, isFirst);
			break;
		case 3:
			//title = InboxFragment.TAG;
			//replaceFragement(InboxFragment.newInstance(null), InboxFragment.TAG, isFirst);
			break;
		case 4:
			//title = ContactsFragment.TAG;
			//replaceFragement(ContactsFragment.newInstance(null), ContactsFragment.TAG, isFirst);
			break;	
		}

	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {	
		super.onPostCreate(savedInstanceState);
		mNavigationDrawerFragment.mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mNavigationDrawerFragment.mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void onSectionAttached(int number) {		
		mTitle = getResources().getStringArray(R.array.drawer)[number];		
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	protected void initControls(Bundle savedInstanceState) {

	}

	@Override
	protected void setValueOnUI() {

	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {	
		super.onActivityResult(arg0, arg1, arg2);		
	}



	@Override
	public void onBackPressed() { 
		super.onBackPressed();		
	}

	/*@Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		 if (mNavigationDrawerFragment.mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }
	  return super.onOptionsItemSelected(item);
	 }*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home, menu);
		super.onCreateOptionsMenu(menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:						
			if(getBackStackCount() > 1){
				onBackPressed();
				disableBackButton();
				setTitleOnAction(title);
				return true;  
			}else {
				if (mNavigationDrawerFragment.mDrawerToggle.onOptionsItemSelected(item)) {
					return true;
				}
			}
			break;			
		
		}	
		return false;
	}

	@Override
	protected Boolean callBackFromApi(Object object, Activity act, int requstCode) {
		if (super.callBackFromApi(object, act, requstCode)) {
			commonCallBack(object, requstCode, act);
		}
		return true; 
	}


	@Override
	protected Boolean callBackFromApi(Object object, Fragment fragment,	int requstCode) {
		if (super.callBackFromApi(object, fragment, requstCode)) {
			commonCallBack(object, requstCode, fragment);			
		}
		return true;
	}

	private void commonCallBack(Object object, int requestCode, Object fragment2){
		JSONObject jObject;
		String message ="";
		jObject = (JSONObject) object;
		message = jObject.optString(Constants.message);
		switch (requestCode){	
	
		case Constants.forgetPass:
			if(jObject.optInt(Constants.dataToFollow, Constants.NOT_FLOW) == Constants.FLOW){
				
			}else {

				ToastCustomClass.showToast(this, message);
			}

			break;
		
		}
	}
	

}
