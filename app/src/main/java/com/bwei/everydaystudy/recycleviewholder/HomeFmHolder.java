package com.bwei.everydaystudy.recycleviewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.everydaystudy.CarouselFigureActivity;
import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseHolder;
import com.bwei.everydaystudy.bean.HomeBean;
import com.bwei.everydaystudy.manager.GlideImageLoader;
import com.bwei.everydaystudy.utils.LogUtils;
import com.bwei.everydaystudy.view.MyRoolViewPager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwe on 2017/1/12.
 */

public class HomeFmHolder extends BaseHolder<List<HomeBean.DataEntity.SliderEntity>> {


    private List<String> imgUrl = new ArrayList<>();
    private final Banner home_banner;

    public HomeFmHolder(View itemView) {
        super(itemView);
        home_banner = (Banner) itemView.findViewById(R.id.home_banner);
    }

    @Override
    public void setData(final Context context, final List<HomeBean.DataEntity.SliderEntity> list) {
        imgUrl.clear();
        for (int i = 0; i < list.size(); i++) {
            imgUrl.add(list.get(i).getImg());
        }
        home_banner.setImageLoader(new GlideImageLoader(list.size()));
        home_banner.setImages(imgUrl);
        home_banner.start();
        home_banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position == 2 || position == 4) {
                    Intent intent = new Intent(context, CarouselFigureActivity.class);
                    intent.putExtra("cid", list.get(position-1).getUrl());
                    context.startActivity(intent);
                }

            }
        });
    }


}



