package com.bwei.everydaystudy.manager;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by qwe on 2017/1/13.
 */

public class GlideImageLoader extends ImageLoader {


    @Override
    public void displayImage(final Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);

    }
}
