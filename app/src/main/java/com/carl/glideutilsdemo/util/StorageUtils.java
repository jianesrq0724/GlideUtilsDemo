package com.carl.glideutilsdemo.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;
import java.io.IOException;


/**
 * @author zou.sq
 */
public class StorageUtils {

    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    /**
     * @param context 获取缓存目录
     * @return app的缓存目录
     */
    public static File getCacheDirectory(Context context, String subDir) {
        File appCacheDir = null;
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (Exception e) { // (sh)it happens too (Issue #989)
            externalStorageState = "";
        }
        if (Environment.MEDIA_MOUNTED.equals(externalStorageState) && hasExternalStoragePermission(context)) {
            appCacheDir = getExternalCacheDir(context, subDir);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            String cacheDirPath = "/data/data/" + context.getPackageName() + "/" + subDir + "/";
            appCacheDir = new File(cacheDirPath);
        }
        File ignoreFile = new File(appCacheDir + "/.nomedia");
        if (!ignoreFile.exists()) {
            try {
                ignoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return appCacheDir;
    }

    private static File getExternalCacheDir(Context context, String subDir) {
//        String path = "club/com.azoya.club";
        String path = "Pictures";
        File appCacheDir;
        if (StringUtil.isNullOrEmpty(path)) {
            File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
            appCacheDir = new File(new File(dataDir, context.getPackageName()), subDir);
        } else {
            File dataDir = new File(Environment.getExternalStorageDirectory(), path);
            appCacheDir = new File(dataDir, subDir);
            // TODO: 2019/11/19  
            appCacheDir = dataDir;
        }
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                return null;
            }
        }
        return appCacheDir;
    }


    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }
}
