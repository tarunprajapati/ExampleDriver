package com.uraal.cab.driver.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.uraal.cab.driver.R;
import com.uraal.cab.driver.adapter.RydeHistoryAdapter;
import com.uraal.cab.driver.baseClasses.BaseFragment;
import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.beanClasses.RydeHistorys;
import com.uraal.cab.driver.networkTask.ApiManager;
import com.uraal.cab.driver.utilities.ToastCustomClass;


public class RydeHistory extends BaseFragment{
	
	public static final String TAG = Constants.RydeHistory;
	private ListView listView;
	private RydeHistoryAdapter adapter;

	public RydeHistory() {

	}

	public static RydeHistory newInstance(Bundle bundle) {
		RydeHistory fragment = new RydeHistory();
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
		View view = inflater.inflate(R.layout.fragment_ryde_history, container,false);
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
		listView = (ListView) view.findViewById(R.id.listView);
		
	}

	@Override
	protected void setValueOnUi() {
		// TODO Auto-generated method stub
		//ApiManager.getInstance().getHistory(this);
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
	/*	listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putString(Constants.rydeId, (String) view.getTag());
				startMyActivity(PayrollActivity.class, bundle);
				
			}
		});
		*/
	}

	@Override
	public boolean onBackPressedListener() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void serverResponse(ArrayList<RydeHistorys> arrayList){
		ToastCustomClass.showToast(getActivity(), "TESTING");
		if (arrayList != null) {
			adapter = new RydeHistoryAdapter(this, arrayList);
			listView.setAdapter(adapter);
		}
	}

}
