package com.uraal.cab.driver.utilities;

import android.app.Activity;
import android.provider.Settings;

public class IsMokeLocationEnabled 
{
    public static boolean isMockLocEnable(Activity activity)
    {
	// returns true if mock location enabled, false if not enabled.
	if (Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION).equals("0")) 
	    return false; 
	else 
	    return true;
    }
}
