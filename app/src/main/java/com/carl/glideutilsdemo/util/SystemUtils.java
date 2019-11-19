package com.carl.glideutilsdemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Carl
 * version 1.0
 * @since 2019/1/13
 */
public class SystemUtils {

    public static final String PAY_IMG_NAME = "pay_img_name";


    public static DisplayMetrics getWindowDisplayMetrics(Context context) {
        WindowManager wm = (android.view.WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }


    public static Bitmap generateImageFromView(View view, int width, int height) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        view.layout(0, 0, width, height);
        Bitmap image = Bitmap.createBitmap(view.getDrawingCache());
        view.destroyDrawingCache();

        return image;
    }


    /**
     * 将图片保存到系统相册
     *
     * @param context
     * @param bmp
     * @return
     */
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        return saveImageToGallery(context, String.valueOf(System.currentTimeMillis()), bmp);
    }


    public static Bitmap captureScreenWindow(Activity activity) {
//        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
//        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
//        return bmp;

        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Log.i("TAG", "" + statusBarHeight);

        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();

        Bitmap b = Bitmap.createBitmap(bitmap, 0, statusBarHeight, width, height - statusBarHeight);

        return b;

    }


    /**
     * 手机截图
     *
     * @return 返回截图的路径
     */
    public static String getScreenshot() {
        Process process = null;
        String filePath = getSaveImgDir() + "/" + SystemUtils.PAY_IMG_NAME + ".jpg";
        LogUtils.e(filePath);
        try {
            process = Runtime.getRuntime().exec("su");
            PrintStream outputStream = null;
            outputStream = new PrintStream(new BufferedOutputStream(process.getOutputStream(), 8192));
            outputStream.println("screencap -p " + filePath);
            outputStream.flush();
            outputStream.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return filePath;
    }


    public static boolean saveImageToGallery(Context context, String name, Bitmap bmp) {
        return saveImageToGallery(context, name, bmp, ".jpg");
    }

    public static boolean saveImageToGallery(final Context context, String name, Bitmap bmp, String fileNameSuffix) {
        File galleryDir = StorageUtils.getCacheDirectory(context, "image");
        if (!galleryDir.exists()) {
            galleryDir.mkdirs();
        }
        String fileName = name + fileNameSuffix;
        File file = new File(galleryDir, fileName);

        FileOutputStream fos = null;
        boolean isSuccess = false;

        try {
            fos = new FileOutputStream(file);
            isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        Uri uri = Uri.fromFile(new File(file.getPath()));
        Intent scanFileIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(scanFileIntent);




        return isSuccess;
    }

    @NonNull
    public static String getSaveImgDir() {
        return "mnt/sdcard/Pictures/Custom";
    }


    /**
     * 及时扫描拍照后的照片，在相册就能看到
     *
     * @param context 上下文对象
     * @param path    照片的路径
     */
    public static void scanMedia(Context context, String path) {
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Intent scanFileIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(scanFileIntent);
    }

}
