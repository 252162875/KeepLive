package com.dc.keeplivelibrary;

import android.content.Intent;
import android.util.Log;


/**
 *
 */

public class MyLiveService extends KeepliveService {
    private String TAG = "KEEP_ALIVE";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //do something
        int i = super.onStartCommand(intent, flags, startId);
        Log.e(TAG, "MyLiveService process = " + android.os.Process.myPid());
        Log.e(TAG, "MyLiveService - onStartCommand");
        return i;
    }
}
