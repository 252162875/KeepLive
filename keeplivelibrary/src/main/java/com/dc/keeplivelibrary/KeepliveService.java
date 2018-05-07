package com.dc.keeplivelibrary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 *
 */

public class KeepliveService extends Service {
    private String TAG = "KEEP_ALIVE";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "KeepliveService - onCreate");
        KeepLiveManager.getInstance().registerReceiver(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "KeepliveService - onStartCommand");
        KeepLiveManager.getInstance().setServiceForeground(this);

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "KeepliveService - onDestroy");
        KeepLiveManager.getInstance().unRegisterReceiver(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
