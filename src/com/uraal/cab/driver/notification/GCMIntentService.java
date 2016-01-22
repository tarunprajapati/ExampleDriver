package com.uraal.cab.driver.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.uraal.cab.driver.HomeActivity;
import com.uraal.cab.driver.R;
import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.beanClasses.Notifications;
import com.uraal.cab.driver.db.DataBaseQueriesClass;
import com.uraal.cab.driver.preference.MySharedPreferences;
import com.uraal.cab.driver.utilities.UtilsClass;




public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";
	//public static String registrationId = "";

	public GCMIntentService() {
		super(CommonUtilities.SENDER_ID);
	}

	/**
	 * Method called on device registered
	 **/
	@Override
	protected void onRegistered(Context context, String registrationId) {
		Log.i(TAG, "Device registered: regId = " + registrationId);
		//this.registrationId = registrationId;
		CommonUtilities.displayMessage(context, registrationId);
		Log.d("onRegistered", "onRegistered");
		//ServerUtilities.register(context, "vasugo", "vasugo@gmail.com", registrationId);
	}

	/**
	 * Method called on device un registred
	 * */
	@Override
	protected void onUnregistered(Context context, String registrationId) {
		Log.i(TAG, "Device unregistered");
		CommonUtilities.displayMessage(context, getString(R.string.gcm_unregistered));
		// ServerUtilities.unregister(context, registrationId);
	}

	/**
	 * Method called on Receiving a new message
	 * */
	@Override
	protected void onMessage(Context context, Intent intent) {
		Log.i(TAG, "Received message");
		//  ToastCustomClass.showToast(context, "onMessage");
		String type = intent.getExtras().getString(Constants.loginType);

		String id = intent.getExtras().getString(Constants.id);

		String message = intent.getExtras().getString(Constants.message);

		if (id == null) {
			id = "";
		}
		if (message == null) {
			message = "";
		}
		if (type == null) {
			type = "";
		}

		String data = "type="+type+" id="+id+" message="+message;
		Log.i("Notification data", data);
		if(id.equalsIgnoreCase(MySharedPreferences.getInstance().getUserId(context))){
			Notifications notifications = new Notifications();
			notifications.setId(id);
			notifications.setMessage(message);
			notifications.setTime(UtilsClass.getDateTime(UtilsClass.getCurrentTimeInMili()));
			notifications.setType(type);
			Log.i("NOTIFICATION DATA", notifications.toString());
			DataBaseQueriesClass.getInstance().insertNotification(getApplication(), notifications);
			generateNotification(context, id, message);
		}
	}

	/**
	 * Method called on receiving a deleted message
	 * */
	@Override
	protected void onDeletedMessages(Context context, int total) {
		Log.i(TAG, "Received deleted messages notification");
		String message = getString(R.string.gcm_deleted, total);
		CommonUtilities.displayMessage(context, message);

		// notifies user
		//generateNotification(context, "msg");//changed
	}

	/**
	 * Method called on Error
	 * */
	@Override
	public void onError(Context context, String errorId) {
		Log.i(TAG, "Received error: " + errorId);
		CommonUtilities.displayMessage(context, getString(R.string.gcm_error, errorId));

	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		// log message
		Log.i(TAG, "Received recoverable error: " + errorId);
		CommonUtilities.displayMessage(context, getString(R.string.gcm_recoverable_error,errorId));
		return super.onRecoverableError(context, errorId);
	}

	/**
	 * Issues a notification to inform the user that server has sent a message.
	 */
	private static void generateNotification(Context context, String id, String data ) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		NotificationManager notificationManager = (NotificationManager)
				context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, data, when);
		String title = context.getString(R.string.app_name);

		Intent notificationIntent = new Intent(context, HomeActivity.class);
		notificationIntent.putExtra("id", id);
		// notificationIntent.putExtra("key", key);

		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// notificationIntent.setAction(Intent.ACTION_MAIN);
		notificationIntent.setAction(Long.toString(System.currentTimeMillis()));
		notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		PendingIntent intent =
				PendingIntent.getActivity(context, 0, notificationIntent,0);
		notification.setLatestEventInfo(context, title, data, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;



		int e=(int) System.currentTimeMillis();
		// Toast.makeText(context, "test="+e, Toast.LENGTH_SHORT).show();
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.defaults |= Notification.DEFAULT_VIBRATE;

		// notificationManager.notify(e, notification);  
		notificationManager.notify(e,notification);
		Log.i("notificatio", id+" "+data);


	}

}
