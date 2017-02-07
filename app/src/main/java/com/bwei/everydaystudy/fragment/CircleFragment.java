package com.bwei.everydaystudy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.factory.FragmentFactory;
import com.bwei.everydaystudy.utils.CommonUtils;
import com.bwei.everydaystudy.view.ShowingPager;

/**
 * author by LiKe on 2017/1/10.
 */

public class CircleFragment extends BaseFragment {

    private ImageView title_addFriend;
    private TabLayout title_tabLayout;
    private ImageView title_search;
    private ViewPager circleOutViewPager;
    private String[] titles = {"话题", "热门", "关注"};

    /**
     * 成功视图
     *
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
     *
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
        showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
        initView();
    }

    private void initView() {
        LinearLayout linearLayout = (LinearLayout) title_tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(CommonUtils.dip2px(20));
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.layout_divider_vertical));
        //设置viewpager适配器
        circleOutViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.getFragment(titles[position]);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
        //设置tabLayout和viewPager关联
        title_tabLayout.setupWithViewPager(circleOutViewPager);

        title_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                circleOutViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        circleOutViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(title_tabLayout));
    }
}
