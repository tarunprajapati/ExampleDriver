package com.uraal.cab.driver.networkTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.uraal.cab.driver.R;



public class Is_Internet_Available_Class
{
	public synchronized static boolean internetIsAvailable(Context context) 
	{
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) 
		{
			return true;
		}
		else
		{
			if(cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED || cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTING) 
			{
				return true;
			} 
			else if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTING) 
			{

				return true;
			} 
			else
			{
				/*Toast toast = ToastCustomClass.showToast(context, context.getString(R.string.internet_not_available), 2000); 
			toast.setGravity(Gravity.CENTER,  0 , 0);
			toast.show();*/
				alertWarningMsg(context);
				return false;
			}
		}
	}
	public static void alertWarningMsg(final Context context)
	{
		try
		{
			((Activity)context).runOnUiThread(new Runnable() 
			{	    
				@Override
				public void run() 
				{
					AlertDialog alert = new AlertDialog.Builder(context).create();
					alert.setTitle(context.getString(R.string.warning));
					alert.setMessage(context.getString(R.string.start_internet));
					alert.setIcon(android.R.drawable.ic_dialog_alert);
					// Setting OK Button
					alert.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener()
					{	    
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							/*
							 * Kill application when the root activity is killed.
							 */							

							/*Intent startMain = new Intent(Intent.ACTION_MAIN);
							startMain.addCategory(Intent.CATEGORY_HOME);
							startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(startMain);
							
							((Activity)context).finish();*/
						}
					});

					alert.setButton(AlertDialog.BUTTON_POSITIVE, "Settings", new DialogInterface.OnClickListener()
					{	    
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							//UIHelper.killApp(true);

							context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
//							Intent startMain = new Intent(Intent.ACTION_MAIN);
//							startMain.addCategory(Intent.CATEGORY_HOME);
//							startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//							context.startActivity(startMain);			    
							/*
							 * Kill application when the root activity is killed.
							 */

							//((Activity)context).finish();
						}
					});
					// Showing Alert Message
					alert.show();
				}
			});
		}
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
	}

	public static void alertWarningMsgForMockLocation(final Context context)
	{
		try
		{
			((Activity)context).runOnUiThread(new Runnable() 
			{	    
				@Override
				public void run() 
				{
					AlertDialog alert = new AlertDialog.Builder(context).create();
					alert.setTitle("Warning!");
					alert.setMessage("Please disable mock location.!");
					alert.setIcon(android.R.drawable.ic_dialog_alert);
					// Setting OK Button
					alert.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener()
					{	    
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{			
							//((Activity) context).finish();

							/*
							 * Kill application when the root activity is killed.
							 */


							/*Intent startMain = new Intent(Intent.ACTION_MAIN);
							startMain.addCategory(Intent.CATEGORY_HOME);
							startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(startMain);*/
						}
					});

					alert.setButton(AlertDialog.BUTTON_POSITIVE,"Settings", new DialogInterface.OnClickListener()
					{	    
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));		    

						}
					});
					// Showing Alert Message
					alert.show();
				}
			});
		}
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
	}
}
