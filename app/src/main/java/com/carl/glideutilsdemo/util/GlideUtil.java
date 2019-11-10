package com.carl.glideutilsdemo.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.carl.glideutilsdemo.BaseApplication;

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


        RequestBuilder<Drawable> drawableRequestBuilder = Glide.with(BaseApplication.getInstance())
                .load(url);

        if (0 != resId) {
            drawableRequestBuilder = drawableRequestBuilder
                    .placeholder(resId)
                    .error(resId);
        }


        if (isCircle) {
            drawableRequestBuilder = drawableRequestBuilder.circleCrop();
        }

        if (null != listener) {
            drawableRequestBuilder = drawableRequestBuilder.listener(listener);
        }

        drawableRequestBuilder.into(imageView);

    }


    public static void showBlur(String url, ImageView imageView, int radius) {
        RequestBuilder<Drawable> drawableRequestBuilder = Glide.with(BaseApplication.getInstance())
                .load(getUrl(imageView, url))
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(radius)))
                .skipMemoryCache(true);
        drawableRequestBuilder.into(imageView);

    }

    public static void showRoundRadius(String url, ImageView imageView, int radius, int margin) {
        Glide.with(BaseApplication.getInstance())
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(radius, margin)))
                .into(imageView);
    }


    public static void showRoundRadius(String url, int phResId, int errorResId, ImageView imageView, int radius, RoundedCornersTransformation.CornerType type) {
        Glide.with(BaseApplication.getInstance())
                .load(url)
//                .thumbnail(0.1f)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(radius, 0, type)))
//                .skipMemoryCache(true)
                .placeholder(phResId)
                .error(errorResId)
                .into(imageView);
    }

    public static void showLocalDrawable(Integer resId, ImageView mImage) {
        Glide.with(BaseApplication.getInstance())
                .load(resId)
                .into(mImage);
    }


    public static void loadShareBitmap(Activity activity, String imageUrl, SimpleTarget<Bitmap> target) {
        Glide.with(activity).asBitmap().load(formatUrl(imageUrl, 150, 150)).into(target);
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

    public static void loadBitmap(Context context, String url, SimpleTarget<Drawable> target, int width, int height) {
        Glide.with(context.getApplicationContext()).load(url).override(width, height).centerCrop().into(target);
    }

}
