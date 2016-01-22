package com.uraal.cab.driver.mapAPIv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.maps.model.Marker;
import com.uraal.cab.driver.baseClasses.BaseFragmentActivity;
import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.beanClasses.EndRoute;
import com.uraal.cab.driver.utilities.UtilsClass;


public class MyCustomDialog extends DialogFragment {
	Marker marker;
	private EndRoute route;
	private Context context;
	private int pos;
	
	public MyCustomDialog(Context context, Marker marker, EndRoute route, int position){
		this.context = context;
		this.marker = marker;
		this.route = route;		
		this.pos = position;
	}	

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final EditText markerName = new EditText(getActivity());

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		if(route.getAddress() != null && route.getAddress().length() > 0){
			builder.setTitle("Do you want to set this address?");
			markerName.setText(route.getAddress());
			markerName.setSelection(route.getAddress().length()-1);
		}else{
			builder.setTitle("Add address to marker");	
		}
		builder.setPositiveButton("YES", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String placeName = markerName.getText().toString();
				long currentDate = UtilsClass.getCurrentTimeInMili();
				Log.i("current time", currentDate+"==="+UtilsClass.getDateTime(currentDate));
				marker.setTitle(placeName);
				route.setAddress(placeName);
				route.setCreatedDate(currentDate);
				route.setUpdatedDate(currentDate);
				route.setIs_completed(false);//discuss
				Intent intent = new Intent();
				intent.putExtra(Constants.object, route);
				intent.putExtra(Constants.position, pos);
				((BaseFragmentActivity)context).setResult(Activity.RESULT_OK, intent);
				((BaseFragmentActivity)context).finish();
			}
		});

		builder.setNegativeButton("NO", null);

		builder.setView(markerName);

		return builder.create();
	}
}
