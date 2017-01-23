package com.bwei.everydaystudy;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.base.BaseShowingActivity;
import com.bwei.everydaystudy.bean.CarouselFigureBean;
import com.bwei.everydaystudy.bean.CourseDetailsBean;
import com.bwei.everydaystudy.fragment.DetailsFragment;
import com.bwei.everydaystudy.view.ShowingPager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class CourseDetailsActivity extends BaseShowingActivity {
    private List<String> tab_ist = new ArrayList<>();  // tab 名字集合
    private ViewPager course_details_viewpager;
    private TabLayout coursedetails_tabLayout;
    private ImageView course_iv;
    private TextView course_tv_title;
    private TextView course_tv_price;
    private Button course_btn_hour;
    private Button course_btn_goodrate;
    private Button course_btn_paycount;
    private CourseDetailsBean courseDetailsBean;
    private String cid;

    @Override
    public View setBaseSuccessView() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_course_details, null);
        // tablayout
        coursedetails_tabLayout = (TabLayout) view.findViewById(R.id.coursedetails_tabLayout);
        initLayout(view);
        // 查找其他控件
        course_iv = (ImageView) view.findViewById(R.id.course_iv);
        course_tv_title = (TextView) view.findViewById(R.id.course_tv_title);
        course_tv_price = (TextView) view.findViewById(R.id.course_tv_price);
        course_btn_hour = (Button) view.findViewById(R.id.course_btn_hour);
        course_btn_goodrate = (Button) view.findViewById(R.id.course_btn_goodrate);
        course_btn_paycount = (Button) view.findViewById(R.id.course_btn_paycount);
        // 设置属性

        return view;
    }


    @Override
    public void setBaseTitleView(View titleView) {

    }

    @Override
    public void onLoad() {
        cid = getIntent().getStringExtra("cid");
        HashMap<String, String> map = new HashMap<>();
        map.put("courseid", cid +"");
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                courseDetailsBean = gson.fromJson(data, CourseDetailsBean.class);
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
                initData();
            }

            @Override
            public void setResulttError(int state) {
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
            }
        }.postData(false, false, "http://www.meirixue.com/", "api.php?c=course&a=getCourseInfo", map, BaseData.NOTIME);

    }


    /**
     * 设置tablayout
     *
     * @param view
     */

    private void initLayout(View view) {
        tab_ist.add("详情");
        tab_ist.add("目录");
        tab_ist.add("评论");
        coursedetails_tabLayout.addTab(coursedetails_tabLayout.newTab().setText(tab_ist.get(0)));
        coursedetails_tabLayout.addTab(coursedetails_tabLayout.newTab().setText(tab_ist.get(1)));
        coursedetails_tabLayout.addTab(coursedetails_tabLayout.newTab().setText(tab_ist.get(2)));
        LinearLayout linearLayout = (LinearLayout) coursedetails_tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(15);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.layout_horzaltol_divider_vertical));
        // viewpager
        course_details_viewpager = (ViewPager) view.findViewById(R.id.course_details_viewpager);
        course_details_viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return DetailsFragment.getFragment(tab_ist.get(position), courseDetailsBean,cid);
            }

            @Override
            public int getCount() {
                return tab_ist.size();
            }
        });
        // viewpager监听
        course_details_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(coursedetails_tabLayout));
        //TabLayout的监听
        coursedetails_tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int positon = tab.getPosition();

                course_details_viewpager.setCurrentItem(positon);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        course_details_viewpager.setCurrentItem(1);
    }

    /**
     * 设置数据
     */
    private void initData() {
        Glide.with(CourseDetailsActivity.this).load(courseDetailsBean.getData().getCourse_pic()).into(course_iv);
        course_tv_title.setText(courseDetailsBean.getData().getCourse_name());
        String course_price = courseDetailsBean.getData().getCourse_price();
        if (course_price.equals("0.00")) {
            course_tv_price.setText("免费");
        } else {
            course_tv_price.setText("￥ " + course_price);
        }
        course_btn_hour.setText("总课时: " + courseDetailsBean.getData().getCourse_hour());
        course_btn_goodrate.setText("评分: " + courseDetailsBean.getData().getGoodrate());
        course_btn_paycount.setText(courseDetailsBean.getData().getCourse_paycount() + "人学");
    }
}
