package com.uraal.cab.driver.baseClasses;

import java.util.HashMap;

import com.uraal.cab.driver.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BaseFragment extends android.support.v4.app.Fragment implements OnClickListener{
	protected abstract void initUi(View view);
	protected abstract void setValueOnUi();
	protected abstract void setListener();	

	public abstract boolean onBackPressedListener();
	protected static final String ARG_SECTION_NUMBER = "section_number";
	protected Bundle myBundle = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		getActivity().invalidateOptionsMenu();
		setHasOptionsMenu(true);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		/*if(activity instanceof HomeActivity)
			if(getArguments() != null)
				((HomeActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));*/
	}

	@Override
	public void onResume() {	
		super.onResume();
		//this.setTitleOnAction(title);	in
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {	
		//menu.clear();
		super.onCreateOptionsMenu(menu, inflater);
	}

	@SuppressLint("NewApi")
	public void setTitleOnAction(String text, boolean isUpperCase){
		//title  = text;
		((BaseFragmentActivity)getActivity()).setTitleOnAction(text);
	}

	protected void startMyActivity(Class targetActivity, Bundle myBundle){
		Intent intent = new Intent(getActivity(), targetActivity);
		if(myBundle != null)
			intent.putExtra(Constants.bundleArg, myBundle);
		startActivity(intent);
	}
	
	public void serviceCaller(Fragment fragment, String urlsArr[], HashMap<String, String> params, Boolean isPost, Boolean isFileUploading, int requstCode, boolean isShowProgress) {
		((BaseFragmentActivity)getActivity()).serviceCaller(fragment,urlsArr, params, isPost, isFileUploading, requstCode, isShowProgress);
	}

	protected void addFragment(Fragment fragment, String value, boolean addToBackStack) {
		((BaseFragmentActivity)getActivity()).addFragement(fragment, value, addToBackStack);
	}

	protected void replaceFragment(Fragment fragment, String tag) {
		((BaseFragmentActivity)getActivity()).replaceFragement(fragment);
	}
	
	protected void replaceFragment(android.app.ListFragment fragment, String tag) {
		((BaseFragmentActivity)getActivity()).replaceFragement(fragment);
	}
	

	protected void  removeTopFragment() {
		((BaseFragmentActivity)getActivity()).removeTopFragement();
	}

	protected boolean enableBackButton() {
		ActionBar actionBar = getActionBar();
		actionBar.setHomeAsUpIndicator(R.drawable.back);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);		
		return true;
	}

	protected boolean disableBackButton() {
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);		
		return true;
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
	}

	protected ActionBar getActionBar() {
		return ((BaseFragmentActivity)getActivity()).getSupportActionBar();
	}
	
/*	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {		
		super.onCreateOptionsMenu(menu, inflater);
	}*/
}