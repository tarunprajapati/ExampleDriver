package com.uraal.cab.driver.adapter;

import java.util.ArrayList;

import com.uraal.cab.driver.R;
import com.uraal.cab.driver.beanClasses.Country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter {
	private ArrayList<Country> names = null;
	private LayoutInflater inflator;
	private Holder holder;

	public SpinnerAdapter(Context context, ArrayList<Country> names) {
		this.names = names;
		inflator = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return names.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = (View) inflator.inflate(R.layout.item_spinner, null);
		TextView tvname = (TextView) convertView.findViewById(R.id.name);
		tvname.setText(names.get(position).getName());
		convertView.setTag(names.get(position).getCode());
		return convertView;
	}

	static class Holder {
		public RelativeLayout root;
		ImageView imgV = null;
		TextView name = null;
	}
}
