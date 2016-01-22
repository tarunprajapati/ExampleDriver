package com.uraal.cab.driver.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public final class CommonUtilities {
	
	// give your server registration url here
  

    // Google project id
    //public static final String SENDER_ID = "202194985850"; 
    public static final String SENDER_ID = "763215504296";
    //AIzaSyCUmy9riZWyIbK8OhWsejMzmYGKiHDSiQs
    /**
     * Tag used on log messages.
     */
    static final String TAG = "Uraal GCM";

    public static final String DISPLAY_MESSAGE_ACTION = "com.uraal.cab.DISPLAY_MESSAGE";

    public static final String EXTRA_MESSAGE = "message";

    /**    
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
  public static void displayMessage(Context context, String message ) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        Bundle bundleObject = new Bundle();
		//bundleObject.putSerializable("pnModel", pnModel);
		intent.putExtras(bundleObject);
        intent.putExtra(EXTRA_MESSAGE, message);
        
        context.sendBroadcast(intent);
    }
}
