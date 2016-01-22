package com.uraal.cab.driver.utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

public class CountryCode 
{
	public HashMap<String, String> getCountryCodeWithAddress(Context context, Double lat, Double lng)
	{
		Geocoder geocoder = new Geocoder(context, Locale.getDefault());
		try 
		{
			List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
			Address obj = addresses.get(0);
			String add = obj.getAddressLine(0);
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("code", obj.getCountryCode());
			map.put("name", obj.getCountryName());
			
			add = add + "\n" + obj.getAdminArea();
			add = add + "\n" + obj.getPostalCode();
			add = add + "\n" + obj.getSubAdminArea();
			add = add + "\n" + obj.getLocality();
			add = add + "\n" + obj.getSubThoroughfare();

			Log.v("IGA", "Address" + add);
			// Toast.makeText(this, "Address=>" + add,
			// Toast.LENGTH_SHORT).show();

			// TennisAppActivity.showDialog(add);
			
			return map;
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
			return null;
		}
	}
}
