package com.uraal.cab.driver.utilities;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;

public class LocationService extends Service 
{
	
	private Timer timer;
	private Context context;
	protected LocationManager locationManeger;
	private LocationListener locationListener;
	
	public LocationService(Context context) 
	{
		this.context = context;
		
	}
	TimerTask timerLocationTask = new TimerTask() 
	{		
		@Override
		public void run() 
		{		
			
		}
	};

	@Override
	public void onCreate() 
	{	
		super.onCreate();
		//System.out.println("onCreate Service");
		timer = new Timer();
		timer.schedule(timerLocationTask, 1000*60);
	}
	
	@Override
	public IBinder onBind(Intent arg0) 
	{	
		return null;
	}

}
