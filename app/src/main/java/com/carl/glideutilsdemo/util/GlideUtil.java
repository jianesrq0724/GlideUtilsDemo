package com.carl.glideutilsdemo.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.carl.glideutilsdemo.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideUtil {

    public static void show(String url, ImageView imageView) {
        show(url, imageView, 0);
    }

    public static void show(String url, ImageView imageView, RequestListener listener) {
        show(getUrl(imageView, url), imageView, 0, false, listener);
    }

    public static void show(String url, ImageView imageView, int resId) {
        show(getUrl(imageView, url), imageView, resId, false, null);
    }

    public static void show(String url, ImageView imageView, int resId, int width, int height) {
        show(formatUrl(url, width, height), imageView, resId, false, null);
    }

    public static void showCircle(String url, ImageView imageView, int resId) {
        show(getUrl(imageView, url), imageView, resId, true, null);
    }

    public static void show(String url, ImageView imageView, int resId, boolean isCircle, RequestListener listener) {

        RequestOptions options = new RequestOptions();

        if (0 != resId) {
            options = options
//                    .placeholder(resId)
                    .error(resId)
                    .dontAnimate();
            //Glide的一个问题，不能用占位图，否则会变形，去掉动画就可以
        }

        if (isCircle) {
            options = options.circleCrop();
        }

        Glide.with(BaseApplication.getInstance())
                .load(url)
                .apply(options)
                .listener(listener)
                .into(imageView);
    }

    public static void showBlur(String url, ImageView imageView, int radius) {
        RequestOptions options = RequestOptions
                .bitmapTransform(new BlurTransformation(radius))
                .skipMemoryCache(true);

        Glide.with(BaseApplication.getInstance())
                .load(getUrl(imageView, url))
                .apply(options)
                .into(imageView);
    }

    public static void showRoundRadius(String url, ImageView imageView, int radius, int margin) {
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCornersTransformation(radius, margin));

        Glide.with(BaseApplication.getInstance())
                .load(url)
                .apply(options)
                .into(imageView);
    }


    public static void showRoundRadius(String url, int phResId, int errorResId, ImageView imageView, int radius, RoundedCornersTransformation.CornerType type) {
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCornersTransformation(radius, 0, type))
                .placeholder(phResId)
                .error(errorResId);

        Glide.with(BaseApplication.getInstance())
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void showLocalDrawable(Integer resId, ImageView mImage) {
        Glide.with(BaseApplication.getInstance())
                .load(resId)
                .into(mImage);
    }

    public static void showFile(Context context, String fileName, ImageView mImage, int resId) {
        File targetFile = StorageUtils.getTargetFile(context, fileName);
        showFile(targetFile, mImage, resId);
    }

    public static void showFile(File file, ImageView mImage, int resId) {
        if (file.exists()) {
            Glide.with(BaseApplication.getInstance())
                    .load(file)
                    .error(resId)
                    .into(mImage);
        } else {
            showLocalDrawable(resId, mImage);
        }

    }


    public static void loadShareBitmap(Activity activity, String imageUrl, CustomTarget<Bitmap> target) {
        Glide.with(activity)
                .asBitmap()
                .load(formatUrl(imageUrl, 150, 150))
                .into(target);
    }

    private static String getUrl(ImageView imageView, String url) {
        if (null == imageView) {
            return url;
        }
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        return formatUrl(url, width, height);
    }

    private static String formatUrl(String url, int width, int height) {
        if (0 == width || 0 == height || StringUtil.isNullOrEmpty(url) || !url.startsWith("http") || !url.contains("cdn.azoyaclub.com")) {
            return url;
        }
        return String.format(Locale.getDefault(), "%s?imageView2/0/w/%s/h/%s", url, width, height);
    }

    public static void clearMemory(Activity mActivity) {
        Glide.get(mActivity).clearMemory();
    }

    public static void loadBitmap(Context context, String url, CustomTarget<Drawable> target, int width, int height) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .override(width, height)
                .centerCrop()
                .into(target);
    }

    public static void saveImageToLocal(Context context, String url) {
        if (StringUtil.isNullOrEmpty(url)) {
            return;
        }

        final File targetFile = StorageUtils.getTargetFile(context, url);
        if (targetFile.exists()) {
            return;
        }

        downImage(context, url, targetFile);

    }


    private static void downImage(final Context context, final String url, final File targetFile) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Glide.with(context.getApplicationContext())
                        .download(url)
                        .into(new CustomTarget<File>() {

                            @Override
                            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                try {
                                    FileUtil.copyFile(resource, targetFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {

                            }
                        });
            }
        }).start();


    }

}
