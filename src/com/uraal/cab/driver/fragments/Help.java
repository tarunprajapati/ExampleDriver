package com.uraal.cab.driver.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uraal.cab.driver.R;
import com.uraal.cab.driver.baseClasses.BaseFragment;
import com.uraal.cab.driver.baseClasses.Constants;



public class Help extends BaseFragment {

	public static final String TAG = Constants.Help;

	public Help() {

	}

	public static Help newInstance(Bundle bundle) {
		Help fragment = new Help();
		if (bundle != null)
			fragment.setArguments(bundle);
		// fragment.setUserVisibleHint(true);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitleOnAction(TAG, false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_help, container, false);
		initUi(view);
		setListener();
		setValueOnUi();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initUi(View view) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setValueOnUi() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}

}
