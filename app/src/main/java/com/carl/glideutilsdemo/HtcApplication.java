package com.carl.glideutilsdemo;

import android.app.Application;
import android.content.Context;

/**
 * 全局应用程序
 *
 * @author zou.sq
 */
public class HtcApplication extends Application {

    private static Context mHtcApp;

    public static Context getInstance() {
        return mHtcApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mHtcApp = this;
        BaseApplication.initInstanceApp().initAppContext(this);

    }

}
