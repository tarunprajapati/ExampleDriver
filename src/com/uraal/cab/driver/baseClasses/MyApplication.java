package com.uraal.cab.driver.baseClasses;

import java.util.Date;

import android.app.Application;
import android.graphics.Typeface;



public class MyApplication extends Application {
	private String contactsString = null;
	// public static boolean withDummyData = false;

	private static boolean activityVisible;
	// private ApiResponse apiResponce;
	private double currentLatitude;
	private double currentLongitude;
	// Screen in Pixels
	private int widthPixel = 1280;
	private int heightPixel = 1920;
	public static long locationDetectionTimeInMin;
	private Date date = null;
	private static MyApplication myApplication;
	public static Typeface typeJACKPORT_REGULAR_NCV;
	public static Typeface typeFaceOrgano;
	public static Typeface typeFaceSkipLegDay;
	public static Typeface typeFaceStarzy_Darzy;

	@Override
	public void onCreate() {
		super.onCreate();
		// Get Date ----------------
		date = new Date();

		myApplication = this;
		
	}

	public static MyApplication getApplication() {
		return myApplication;
	}

	public double getCurrentLongitude() {
		return currentLongitude;
	}

	public void setCurrentLongitude(double currentLongitude) {
		this.currentLongitude = currentLongitude;
	}

	public double getCurrentLatitude() {
		return currentLatitude;
	}

	public void setCurrentLatitude(double currentLatitude) {
		this.currentLatitude = currentLatitude;
	}

	public int getWidthPixel() {
		return widthPixel;
	}

	public void setWidthPixel(int widthPixel) {
		this.widthPixel = widthPixel;
	}

	public int getHeightPixel() {
		return heightPixel;
	}

	public void setHeightPixel(int heightPixel) {
		this.heightPixel = heightPixel;
	}
}
