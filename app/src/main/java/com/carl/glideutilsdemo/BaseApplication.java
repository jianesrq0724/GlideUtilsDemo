package com.carl.glideutilsdemo;

import android.app.Application;

/**
 * 基类BaseApplication
 *
 * @author zou.sq
 */
public class BaseApplication {

    private static Application mInstance;
    private static BaseApplication mHtcAppBase;

    private BaseApplication() {
    }

    public static Application getInstance() {
        return mInstance;
    }

    public static BaseApplication initInstanceApp() {
        if (null == mHtcAppBase) {
            synchronized (BaseApplication.class) {
                if (null == mHtcAppBase) {
                    mHtcAppBase = new BaseApplication();
                }
            }
        }
        return mHtcAppBase;
    }

    public void initAppContext(Application mApp) {
        mInstance = mApp;
    }
}
