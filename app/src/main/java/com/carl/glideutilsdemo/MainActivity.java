package com.carl.glideutilsdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.carl.glideutilsdemo.util.SystemUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downImage();
            }
        });


        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);

    }


    public void downImage() {
        //        String url = "https://cdn.azoyaclub.com/FiGPcZ3xXkoTwHM0nghTGCRhH6AW";
//        final String url = "https://cdn.azoyaclub.com/Fq2JaDTlewwp7DfYDjHaBaNsZk_p";

//        final String url = "http://ojchzlu04.bkt.clouddn.com/Flfrt_xgSvGYERWBmsjHnwm5vYsV";

        final String url = "https://cdn.azoyaclub.com/FlZs8ifDNOtB90LLLXQuVjupfI4x";


        Glide.with(this)
                .download(url)
                .into(new CustomTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        try {
                            FileInputStream fis = new FileInputStream(resource);
                            Bitmap bitmap = BitmapFactory.decodeStream(fis);
                            if (bitmap != null) {
                                imageView.setImageBitmap(bitmap);
                            }

                            SystemUtils.saveImageToGallery(MainActivity.this, bitmap);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });


        Glide.with(MainActivity.this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

    }


    private void glideImage() {
        //        String url = "https://cdn.azoyaclub.com/FiGPcZ3xXkoTwHM0nghTGCRhH6AW";
//        final String url = "https://cdn.azoyaclub.com/Fq2JaDTlewwp7DfYDjHaBaNsZk_p";

        final String url = "http://ojchzlu04.bkt.clouddn.com/Flfrt_xgSvGYERWBmsjHnwm5vYsV";


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

//        GlideUtil.show(url, imageView, R.mipmap.main_newer);
//
//
//        Disposable subscribe = Flowable.timer(200, TimeUnit.MICROSECONDS)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.e("tag", String.valueOf(aLong));
//                        GlideUtil.show(url, imageView, R.mipmap.main_newer);
////                        GlideUtil.show(url, imageView);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });


//        GlideUtil.show(url, imageView);

//        GlideUtil.showBlur(url, imageView, 20);


//        GlideUtil.loadShareBitmap(this, url, new SimpleTarget() {
//
//            @Override
//            public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {
//
//            }
//        });


//        Glide.with(this)
//                .asBitmap()
//                .load(url)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//
//                    }
//                });


//     Glide.with(this)
//                    .download(url)
//                    .submit()
//             .get()


        Glide.with(this)
                .download(url)
                .into(new CustomTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
//                        Bitmap bitmap = BitmapFactory.decodeFile(resource);

                        try {
                            FileInputStream fis = new FileInputStream(resource);
                            Bitmap bitmap = BitmapFactory.decodeStream(fis);
                            if (bitmap != null) {
//                            imageView.setImageBitmap(bitmap);
                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });


        Glide.with(MainActivity.this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });


//        GlideUtil.show(url, imageView, R.color.colorAccent, false, new RequestListener<BitmapDrawable>() {
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
