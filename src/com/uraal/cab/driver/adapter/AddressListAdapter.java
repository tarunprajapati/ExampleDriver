package com.uraal.cab.driver.adapter;

import java.util.ArrayList;

import com.uraal.cab.driver.R;
import com.uraal.cab.driver.beanClasses.EndRoute;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class AddressListAdapter extends BaseAdapter {
	private LayoutInflater inflator;
	//private Holder holder;
	//private android.widget.GridView.LayoutParams params;
	//private android.widget.RelativeLayout.LayoutParams paramsRel;
	private ArrayList<EndRoute> addresses;
	private Context context;

	public AddressListAdapter(Context context, ArrayList<EndRoute> addresses){
		this.context = context;
		this.addresses = addresses;
		inflator = LayoutInflater.from(context);	
	}

	@Override
	public int getCount() {		
		return addresses.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void setList(ArrayList<EndRoute> addresses){
		this.addresses = null;
		this.addresses = addresses;
	}
	
	public ArrayList<EndRoute> getList(){
		return addresses;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*if(convertView == null){*/
		//holder = new Holder();
		//convertView = (View) inflator.inflate(R.layout.item_home_grid, null);
		convertView = (View) inflator.inflate(R.layout.item_spinner, null);
		TextView tvname = (TextView) convertView.findViewById(R.id.name);			

		//convertView.setTag(holder);
		/*}else{
			holder = (Holder) convertView.getTag();
		}*/


		try {
			//holder.root.setLayoutParams(params);
			//holder.root.setLayoutParams(paramsRel);
		} catch (ClassCastException e) {			
			e.printStackTrace();

		}

		String name = addresses.get(position).getAddress();
		tvname.setText(name);
		convertView.setTag(addresses.get(position));

		return convertView;
	}

	static class Holder{
		public RelativeLayout root;
		//ImageView imgV = null;
		TextView name = null;
	}
}
