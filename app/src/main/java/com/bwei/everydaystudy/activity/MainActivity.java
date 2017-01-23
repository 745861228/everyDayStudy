package com.bwei.everydaystudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseActivity;
import com.bwei.everydaystudy.factory.FragmentFactory;
import com.bwei.everydaystudy.fragment.MineFragment;
import com.bwei.everydaystudy.utils.ToastUtil;
import com.bwei.everydaystudy.view.LazyViewPager;

public class MainActivity extends BaseActivity implements LazyViewPager.OnPageChangeListener {

    private LazyViewPager main_lazyViewPager;
    private RadioGroup rb;
    private long mExitTime = 0;
    private String[] titles = {"首页", "课程分类", "圈子", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();
    }

    private void initView() {
        main_lazyViewPager = (LazyViewPager) findViewById(R.id.main_lazyViewPager);
        rb = (RadioGroup) findViewById(R.id.rg);
        //监听事件
        main_lazyViewPager.setOnPageChangeListener(this);

        main_lazyViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.getFragment(titles[position]);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });


        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < rb.getChildCount(); i++) {
                    RadioButton childAt = (RadioButton) rb.getChildAt(i);
                    if (childAt.getId() == checkedId) {
                        main_lazyViewPager.setCurrentItem(i);
                    }
                }
            }
        });

    }


    /**
     * 一键退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动监听
     *
     * @param position Position index of the new selected page.
     */
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < rb.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) rb.getChildAt(i);
            if (i == position) {
                radioButton.setChecked(true);
            } else {
                radioButton.setChecked(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MineFragment mineFragment = (MineFragment) FragmentFactory.getFragment("我的");
        if (resultCode == 111) {
            String screen_name = data.getStringExtra("screen_name");
            String profile_image_url = data.getStringExtra("profile_image_url");
            mineFragment.upDataMessage(profile_image_url,screen_name);
        } else if ( resultCode == 101) {
            String user_big_log = data.getStringExtra("user_big_log");
            String user_name = data.getStringExtra("user_name");
            mineFragment.upDataMessage(user_big_log, user_name);
        }
    }



}
