package com.bwei.everydaystudy.recycleviewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseHolder;
import com.bwei.everydaystudy.bean.HomeBean;
import com.bwei.everydaystudy.manager.GlideImageLoader;
import com.bwei.everydaystudy.view.MyRoolViewPager;
import com.youth.banner.Banner;
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
        home_banner.setImageLoader(new GlideImageLoader());
        home_banner.setImages(imgUrl);
        home_banner.start();
    }


}



