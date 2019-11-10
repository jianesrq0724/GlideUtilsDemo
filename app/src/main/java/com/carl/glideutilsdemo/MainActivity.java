package com.carl.glideutilsdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.carl.glideutilsdemo.util.GlideUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

//        String url = "https://cdn.azoyaclub.com/FiGPcZ3xXkoTwHM0nghTGCRhH6AW";

        final String url = "https://cdn.azoyaclub.com/Fq2JaDTlewwp7DfYDjHaBaNsZk_p";

//        GlideUtil.show(url, imageView, 0, true, new RequestListener() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
//                return false;
//            }
//        });

        GlideUtil.show(url, imageView, R.mipmap.main_newer);


        Disposable subscribe = Flowable.timer(200, TimeUnit.MICROSECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("tag", String.valueOf(aLong));
                        GlideUtil.show(url, imageView, R.mipmap.main_newer);
//                        GlideUtil.show(url, imageView);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

//        Disposable subscribe2 = Flowable.timer(300, TimeUnit.MICROSECONDS)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.e("tag", String.valueOf(aLong));
//                        GlideUtil.show(url, imageView, R.mipmap.main_newer);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });


//        GlideUtil.show(url, imageView);

//        GlideUtil.showBlur(url, imageView, 20);


//        GlideUtil.loadShareBitmap(this, url, new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//
//            }
//        });
//
//
//        GlideUtil.show(url, imageView, R.color.colorAccent, false, new RequestListener<BitmapDrawable>() {
//
//
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<BitmapDrawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(BitmapDrawable resource, Object model, Target<BitmapDrawable> target, DataSource dataSource, boolean isFirstResource) {
//                int width = resource.getBitmap().getWidth();
//                int height = resource.getBitmap().getHeight();
//                return false;
//            }
//        });

    }
}
