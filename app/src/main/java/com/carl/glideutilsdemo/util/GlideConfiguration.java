package com.carl.glideutilsdemo.util;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * Glide 配置文件
 *
 * @author car.shen
 */
@GlideModule
public class GlideConfiguration extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {

        int cacheSize100MegaBytes = 100 * 1024 * 1024;
        String imagePath = StorageUtils.getCacheDirectory(context, "image").getAbsolutePath();
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                .setDiskCache(new DiskLruCacheFactory(imagePath, cacheSize100MegaBytes));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
