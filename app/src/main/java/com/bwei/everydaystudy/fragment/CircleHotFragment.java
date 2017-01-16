package com.bwei.everydaystudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.bean.HotTitlesBean;
import com.bwei.everydaystudy.factory.FragmentFactory;
import com.bwei.everydaystudy.factory.FragmentTitleFactory;
import com.bwei.everydaystudy.utils.LogUtils;
import com.bwei.everydaystudy.utils.NetUtils;
import com.bwei.everydaystudy.utils.ToastUtil;
import com.bwei.everydaystudy.utils.UrlUtils;
import com.bwei.everydaystudy.view.ShowingPager;
import com.google.gson.Gson;

import static com.bwei.everydaystudy.R.id.circleOutViewPager;

/**
 * author by LiKe on 2017/1/13.
 */

public class CircleHotFragment extends BaseFragment {

    private boolean isOnline = true;
    private View view;
    private ViewPager circle_hotfm_item_viewPager;
    private TabLayout circle_hotfm_item_tabLayout;
    private HotTitlesBean hotTitlesBean;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //进入程序后判断网络状态
        if (!NetUtils.isHaveNet()) {
            isOnline = false;
        }
    }


    @Override
    public View setBaseSuccessView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.circle_hotfm_item, null);
        circle_hotfm_item_viewPager = (ViewPager) view.findViewById(R.id.circle_hotfm_item_viewPager);
        circle_hotfm_item_tabLayout = (TabLayout) view.findViewById(R.id.circle_hotfm_item_tabLayout);
        return view;
    }

    @Override
    public void setBaseTitleView(View v) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //隐藏标题栏
        showingPager.goneTitleListener();

        //请求服务器获取标题栏
        initTitlesDatas();

    }

    /**
     * 获取标题栏信息
     */
    private void initTitlesDatas() {
        new BaseData() {
            @Override
            public void setResultData(String data) {
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                hotTitlesBean = gson.fromJson(data, HotTitlesBean.class);
                initView();
            }

            @Override
            public void setResulttError(int state) {
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
            }
        }.getData(UrlUtils.baseUrl,"api.php?c=circle&a=getRecommendTag",BaseData.NORMALTIME);
    }

    private void initView() {
        //设置viewpager适配器
        circle_hotfm_item_viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentTitleFactory.getFragment(hotTitlesBean.data.get(position).name);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return hotTitlesBean.data.get(position).name;
            }

            @Override
            public int getCount() {
                return hotTitlesBean.data.size();
            }
        });
        //设置tabLayout和viewPager关联
        circle_hotfm_item_tabLayout.setupWithViewPager(circle_hotfm_item_viewPager);

        circle_hotfm_item_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                circle_hotfm_item_viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        circle_hotfm_item_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                circle_hotfm_item_tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
