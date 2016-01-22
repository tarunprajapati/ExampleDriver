package com.uraal.cab.driver.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;




public class Receiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("com.uraal.cab.DISPLAY_MESSAGE")) {
			String newMessage = intent.getExtras().getString(CommonUtilities.EXTRA_MESSAGE);
			Bundle bundle = intent.getExtras();
			/*PNModel pnModel = (PNModel) bundle.getSerializable("pnModel");
				if (pnModel != null) {

					Utility.showCustomToast(context, pnModel.senderName, pnModel.alert, pnModel.senderImage);
					//Toast.makeText(context, "MESSAGE", Toast.LENGTH_SHORT).show();

				}*/
		}
	}
}
