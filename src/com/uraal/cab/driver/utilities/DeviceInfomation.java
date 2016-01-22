package com.uraal.cab.driver.utilities;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;

public class DeviceInfomation 
{
	private String os = "";
	private String osversion = "";
	private String devicename = "";
	private String hardwareid = "";

	public DeviceInfomation(Context context)
	{
		String uid = Secure.getString(context.getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
		setOs(android.os.Build.VERSION.CODENAME);
		setOsversion(android.os.Build.VERSION.SDK_INT);
		setDevicename(getDeviceName());
		setHardwareid(uid);
		System.out.println("Device Info "+toString());
	}
	
	/*public static String getAndroid_device_id(Context context){
		String uid = Secure.getString(context.getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
		return uid;
	}*/

	private void setOs(String sdk) 
	{
		this.os = sdk;
	}

	public String getDeviceName() 
	{
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer))
		{
			return capitalize(model);
		}
		else 
		{
			return capitalize(manufacturer) + " " + model;
		}
	}


	private String capitalize(String s) 
	{		
		if (s == null || s.length() == 0) 
		{
			return "";
		}

		char first = s.charAt(0);
		if (Character.isUpperCase(first)) 
		{
			return s;
		}
		else 
		{
			return Character.toUpperCase(first) + s.substring(1);
		}
	}


	public String getOs() {
		return os;
	}


	/*	public void setOs(i os) {
		this.os = os;
	}*/


	public String getOsversion() {
		return osversion;
	}


	public void setOsversion(int sdkInt) 
	{
		this.osversion = sdkInt+"";
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}


	public String getHardwareid() {
		return hardwareid;
	}


	public void setHardwareid(String hardwareid) {
		this.hardwareid = hardwareid;
	} 

	@Override
	public String toString() 
	{	
		return "os : "+os+" version : "+osversion+" devie name : "+devicename+" hardwareid : "+hardwareid+" " ;
	}
}
