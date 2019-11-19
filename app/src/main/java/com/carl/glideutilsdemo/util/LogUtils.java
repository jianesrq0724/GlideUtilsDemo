package com.carl.glideutilsdemo.util;

import android.text.TextUtils;
import android.util.Log;


public class LogUtils {
    public static final String TAG = "TAG";   //自定义TAG

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int LEVEL = VERBOSE;

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            if (checkNetWorkEnvironment() && !TextUtils.isEmpty(msg)) {
                Log.v(tag, msg);
//                FileUtils.writeLogToSDCard(tag, msg);
            }
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            if (checkNetWorkEnvironment() && !TextUtils.isEmpty(msg)) {
                Log.d(tag, msg);
//                FileUtils.writeLogToSDCard(tag, msg);
            }
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            if (checkNetWorkEnvironment() && !TextUtils.isEmpty(msg)) {
                Log.i(tag, msg);
//                FileUtils.writeLogToSDCard(tag, msg);
            }
        }
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            if (checkNetWorkEnvironment() && !TextUtils.isEmpty(msg)) {
                Log.w(tag, msg);
//                FileUtils.writeLogToSDCard(tag, msg);
            }
        }
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            if (checkNetWorkEnvironment() && !TextUtils.isEmpty(msg)) {
                Log.e(tag, msg);
//                FileUtils.writeLogToSDCard(tag, msg);
            }
        }
    }

    /**
     * 非正式环境打印日志
     *
     * @return
     */
    private static boolean checkNetWorkEnvironment() {
        return true;
    }

}
