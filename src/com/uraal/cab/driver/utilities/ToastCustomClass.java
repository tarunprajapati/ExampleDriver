package com.uraal.cab.driver.utilities;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastCustomClass
{
	public static void showToast(Context activity , String msg)
	{
		Toast toast = Toast.makeText(activity, msg , 2000);
		toast.setGravity(Gravity.CENTER, 0 , 0);
		toast.show();
	}
}
