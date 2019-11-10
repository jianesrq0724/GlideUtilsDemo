package com.carl.glideutilsdemo.util;

import android.util.Log;

import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

/**
 * 日志打印工具类
 *
 * @author zou.sq
 */
public class EvtLog {
    private static final String TAG = "azoyaclub";
    private static boolean IS_DEBUG_LOGGABLE;
    private static boolean IS_ERROR_LOGGABLE;

    static {
        IS_DEBUG_LOGGABLE = true;
        IS_ERROR_LOGGABLE = true;
    }

    public static void setDebugEnable(boolean enable) {
        IS_DEBUG_LOGGABLE = enable;
    }

    /**
     * 输出debug信息
     *
     * @param msg 信息
     */
    public static void d(String msg) {
        if (IS_DEBUG_LOGGABLE) {
            Log.d(TAG, msg);
        }
    }

    /**
     * 输出debug信息
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void d(String tag, String msg) {
        if (IS_DEBUG_LOGGABLE) {
            Log.d(tag, msg);
        }
    }

    /**
     * @param msg 信息
     */
    public static void i(String msg) {
        if (IS_DEBUG_LOGGABLE) {
            Log.i(TAG, msg);
        }
    }

    /**
     * @param tag 标签
     * @param msg 信息
     */
    public static void i(String tag, String msg) {
        if (IS_DEBUG_LOGGABLE) {
            Log.i(tag, msg);
        }
    }

    /**
     * @param msg 信息
     */
    public static void w(String msg) {
        if (IS_DEBUG_LOGGABLE) {
            Log.w(TAG, msg);
        }
    }

    /**
     * @param tag 标签
     * @param msg 信息
     */
    public static void w(String tag, String msg) {
        if (IS_DEBUG_LOGGABLE) {
            Log.w(tag, msg);
        }
    }

    /**
     * 输出错误信息
     *
     * @param tag       标签
     * @param exception 输出异常信息到控制台
     */
    public static void w(String tag, Throwable exception) {
        if (IS_DEBUG_LOGGABLE) {
            Log.w(tag, exception);
        }
    }

    /**
     * 输出error信息并在程序中toast显示，该错误不会记录在日志文件中
     *
     * @param msg 信息
     */
    public static void e(String msg) {
        if (IS_ERROR_LOGGABLE) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 输出error信息并在程序中toast显示，该错误不会记录在日志文件中
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void e(String tag, String msg) {
        if (IS_ERROR_LOGGABLE) {
            Log.e(tag, msg);
        }
    }

    /**
     * 输出错误信息
     *
     * @param tag       标签
     * @param exception 异常对象：如果是MessageException，则会抛出提示信息；
     *                  如果是NetworkException，只会在控制台输出；
     *                  如果是其他的Exception，则会记录日志信息，并输出通用的提示信息
     */
    public static void e(String tag, Throwable exception) {
        if (IS_ERROR_LOGGABLE) {
            if (isNetworkException(exception)) {
                if (exception.getMessage() != null) {
                    Log.d(tag, exception.getMessage());
                }
            } else {
                Log.e(tag, "", exception);
            }
        }
    }

    private static boolean isNetworkException(Throwable exception) {
        return (exception instanceof HttpRetryException
                || exception instanceof MalformedURLException
                || exception instanceof ProtocolException
                || exception instanceof SocketException
                || exception instanceof SocketTimeoutException
                || exception instanceof UnknownHostException
                || exception instanceof UnknownServiceException
                || exception instanceof URISyntaxException);
    }
}
