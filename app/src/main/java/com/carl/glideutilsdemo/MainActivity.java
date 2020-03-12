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
import com.carl.glideutilsdemo.bean.ExtendedBean;
import com.carl.glideutilsdemo.util.GlideUtil;
import com.carl.glideutilsdemo.util.SystemUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button;

    ImageView mIvTabOne;
    ImageView mIvTabTwo;
    ImageView mIvTabThree;
    ImageView mIvTabFour;
    ImageView mIvTabFive;

    ExtendedBean.TabIcons bean = new ExtendedBean.TabIcons();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();

        bean = new ExtendedBean.TabIcons(
                "https://cdn.azoyaclub.com/FlEAd6WOmZ_sLEQsOzIMgn3Os5-8", "https://cdn.azoyaclub.com/FmVd_dnWCmSG-9yCxtUcKd6B5WSA",
                "https://cdn.azoyaclub.com/Fn1bMS_7PaEzRXMyE_9Rw0Ttdokf", "https://cdn.azoyaclub.com/FoVS1RAY2OE0CDyDFnVQup7T8-M1",
                "https://cdn.azoyaclub.com/FlKOuDubaTd_UWM4Ko2WMgdydiEk", "https://cdn.azoyaclub.com/Ft7yv9ISsyvkBznPTeAY9rYi7n0F",
                "https://cdn.azoyaclub.com/FhAgfrvHHGjfu8fVeel99w51ZyCJ", "https://cdn.azoyaclub.com/FjsRCd1F4l1sLGy7HYJk6ye_l4s3",
                "https://cdn.azoyaclub.com/FlKOuDubaTd_UWM4Ko2WMgdydiEk", "https://cdn.azoyaclub.com/Fv2KaPi8UDFaOpZAb4CPubmp08zu"
        );

        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);


        if (bean != null) {

            GlideUtil.saveImageToLocal(this, bean.getIndex());
            GlideUtil.saveImageToLocal(this, bean.getIndexActive());

            GlideUtil.saveImageToLocal(this, bean.getSite());
            GlideUtil.saveImageToLocal(this, bean.getSiteActive());


            GlideUtil.saveImageToLocal(this, bean.getPd());
            GlideUtil.saveImageToLocal(this, bean.getPdActive());

            GlideUtil.saveImageToLocal(this, bean.getDiscover());
            GlideUtil.saveImageToLocal(this, bean.getDiscoverActive());


            GlideUtil.saveImageToLocal(this, bean.getMy());
            GlideUtil.saveImageToLocal(this, bean.getMyActive());
        }


    }


    private void findViewById() {
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        mIvTabOne = findViewById(R.id.iv_tab_one);
        mIvTabTwo = findViewById(R.id.iv_tab_two);
        mIvTabThree = findViewById(R.id.iv_tab_three);
        mIvTabFour = findViewById(R.id.iv_tab_four);
        mIvTabFive = findViewById(R.id.iv_tab_five);
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

    public void onClick(View view) {
        setNormalTab();

        switch (view.getId()) {
            case R.id.button:
                downImage();
                break;
            case R.id.iv_tab_one:
                setOneTabChecked(true, bean);
                break;
            case R.id.iv_tab_two:
                setTwoTabChecked(true, bean);
                break;
            case R.id.iv_tab_three:
                setThreeTabChecked(true, bean);
                break;
            case R.id.iv_tab_four:
                setFourTabChecked(true, bean);
                break;
            case R.id.iv_tab_five:
                setFiveTabChecked(true, bean);
                break;
            default:
                break;
        }
    }

    private void setNormalTab() {
        setOneTabChecked(false, bean);
        setTwoTabChecked(false, bean);
        setThreeTabChecked(false, bean);
        setFourTabChecked(false, bean);
        setFiveTabChecked(false, bean);
    }

    private void setOneTabChecked(boolean isChecked, ExtendedBean.TabIcons bean) {
        if (isChecked) {
            GlideUtil.showFile(this, bean.getMyActive(), mIvTabOne, R.mipmap.tab_main_pressed);
        } else {
            GlideUtil.showFile(this, bean.getMy(), mIvTabOne, R.mipmap.tab_main_normal);
        }

    }

    private void setTwoTabChecked(boolean isChecked, ExtendedBean.TabIcons bean) {
        if (isChecked) {
            GlideUtil.showFile(this, bean.getSiteActive(), mIvTabTwo, R.mipmap.tab_site_pressed);
        } else {
            GlideUtil.showFile(this, bean.getSite(), mIvTabTwo, R.mipmap.tab_site_normal);
        }
    }

    private void setThreeTabChecked(boolean isChecked, ExtendedBean.TabIcons bean) {
        if (isChecked) {
            GlideUtil.showFile(this, bean.getPdActive(), mIvTabThree, R.mipmap.tab_group_buy_pressed);
        } else {
            GlideUtil.showFile(this, bean.getPd(), mIvTabThree, R.mipmap.tab_group_buy_normal);
        }
    }

    private void setFourTabChecked(boolean isChecked, ExtendedBean.TabIcons bean) {
        if (isChecked) {
            GlideUtil.showFile(this, bean.getDiscoverActive(), mIvTabFour, R.mipmap.tab_found_pressed);
        } else {
            GlideUtil.showFile(this, bean.getDiscover(), mIvTabFour, R.mipmap.tab_found_normal);
        }
    }


    private void setFiveTabChecked(boolean isChecked, ExtendedBean.TabIcons bean) {
        if (isChecked) {
            GlideUtil.showFile(this, bean.getMyActive(), mIvTabFive, R.mipmap.tab_mine_pressed);
        } else {
            GlideUtil.showFile(this, bean.getMy(), mIvTabFive, R.mipmap.tab_mine_normal);
        }


    }
}
