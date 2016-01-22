package com.uraal.cab.driver.geofence;


import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;

public class DownloadResultReceiver extends ResultReceiver{

	/*public DownloadResultReceiver(Handler handler) {
		super(handler);
		
	}*/
	
	/*@Override
	protected void onReceiveResult(int resultCode, Bundle resultData) {
		super.onReceiveResult(resultCode, resultData);
	}*/
    private MyReceiver mReceiver;

    public DownloadResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(MyReceiver receiver) {
        mReceiver = receiver;
    }

    public interface MyReceiver {
        public void onMyReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onMyReceiveResult(resultCode, resultData);
        }
    }
}

