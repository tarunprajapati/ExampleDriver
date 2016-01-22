package com.uraal.cab.driver.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.uraal.cab.driver.R;
import com.uraal.cab.driver.baseClasses.BaseFragment;
import com.uraal.cab.driver.baseClasses.MyApplication;
import com.uraal.cab.driver.beanClasses.RydeHistorys;



public class RydeHistoryAdapter extends BaseAdapter implements Filterable
{
	private LayoutInflater inflater;
	private BaseFragment fragment;
	private ArrayList<RydeHistorys> arrlist;
	private ArrayList<RydeHistorys> orig;
	private Holder holder;
	private int width;

	public RydeHistoryAdapter() {
		// TODO Auto-generated constructor stub
	}

	public RydeHistoryAdapter(BaseFragment fragment, ArrayList<RydeHistorys> arrlist) {
		this.fragment = fragment;
		this.arrlist = arrlist;
		inflater = LayoutInflater.from(fragment.getActivity());
		width = (MyApplication.getApplication().getWidthPixel() / 3) - 40;
	}

	public void setList(ArrayList<RydeHistorys> arrSearched) {
		this.arrlist = arrSearched;
	}

	public ArrayList<RydeHistorys> getList() {
		return this.arrlist;
	}

	@Override
	public int getCount() {
		return arrlist.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public View getView(int postion, View view, ViewGroup parent) {
		holder = new Holder();
		if (view == null) {
			view = inflater.inflate(R.layout.item_ryde_history, null);
			holder.carName = (TextView) view.findViewById(R.id.carName);
			holder.carType = (TextView) view.findViewById(R.id.carType);
			holder.date = (TextView) view.findViewById(R.id.date);
			holder.time = (TextView) view.findViewById(R.id.time);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}

		
		holder.carName.setTag(postion);
		view.setTag(arrlist.get(postion).getId());
		
		holder.carName.setText(arrlist.get(postion).getCarName());
		holder.carType.setText(arrlist.get(postion).getCarType());
		holder.date.setText(arrlist.get(postion).getDate());
		holder.time.setText(arrlist.get(postion).getTime());
	

		return view;
	}

	static class Holder {
		TextView carName = null;
		TextView carType = null;
		TextView date = null;
		TextView time = null;
	}
	
	@Override
	public Filter getFilter() {
		return new Filter() {
			@Override
			protected void publishResults(CharSequence constraint,	FilterResults results) {
				arrlist = (ArrayList<RydeHistorys>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				final FilterResults oReturn = new FilterResults();
				final ArrayList<RydeHistorys> results = new ArrayList<RydeHistorys>();
				if (orig == null)
					orig = arrlist;
				if (constraint != null) {
					if (orig != null && orig.size() > 0) {
						for (final RydeHistorys g : orig) {
							if (g.getSource().toLowerCase().contains(constraint.toString()))
								results.add(g);
						}
					}
					oReturn.values = results;
				}
				return oReturn;
			}
		};
	}
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}
}


