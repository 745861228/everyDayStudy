package com.bwei.everydaystudy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.interfaces.IResetShowingPageListener;
import com.bwei.everydaystudy.utils.CommonUtils;
import com.bwei.everydaystudy.utils.ToastUtil;
import com.bwei.everydaystudy.view.ShowingPager;

/**
 * author by LiKe on 2017/1/10.
 */

public class CircleFragment extends BaseFragment {

    private ImageView title_addFriend;
    private TabLayout title_tabLayout;
    private ImageView title_search;
    private ViewPager circleOutViewPager;
    private String[] titles = {"话题","热门","关注"};

    /**
     * 成功视图
     * @return
     */
    @Override
    public View setBaseSuccessView() {
        View view = CommonUtils.inflate(R.layout.circlefm_item);
        circleOutViewPager = (ViewPager) view.findViewById(R.id.circleOutViewPager);
        return view;
    }

    /**
     * 标题视图
     * @param v
     */
    @Override
    public void setBaseTitleView(View v) {
        title_addFriend = (ImageView) v.findViewById(R.id.title_addFriend);
        title_tabLayout = (TabLayout) v.findViewById(R.id.title_TabLayout);
        title_search = (ImageView) v.findViewById(R.id.title_search);

        //设置控件显示
        title_addFriend.setVisibility(View.VISIBLE);
        title_tabLayout.setVisibility(View.VISIBLE);
        title_search.setVisibility(View.VISIBLE);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

    }
}
