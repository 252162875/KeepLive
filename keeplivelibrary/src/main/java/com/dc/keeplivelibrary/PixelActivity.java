package com.dc.keeplivelibrary;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;


/**
 * 一像素的Activity
 */

public class PixelActivity extends Activity {
    private static boolean b = false;
    private static String TAG = "KEEP_ALIVE";
    private static PixelActivity instance;
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            killKeepLive();
        }
    };

    /**
     * 关闭保活页面
     */
    public static void killKeepLive() {
        Log.e(TAG, "killKeepLive");
        if (b) {
            if (instance != null) {
                b = false;
                instance.finish();
            } else {
                //当startKeepLive调用以后（b为true），LiveActivity实例instance还是null是就不停的递归调用killKeepLive直到instance != null 时关闭页面
                mHandler.sendEmptyMessage(0);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "instance");
        Window window = getWindow();
        //放在左上角
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        //宽高设计为1个像素
        attributes.width = 1;
        attributes.height = 1;
        //起始坐标
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (instance == null) {
            instance = this;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (instance == null) {
            instance = this;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        b = false;
        finish();
        return super.onTouchEvent(event);
    }
}
