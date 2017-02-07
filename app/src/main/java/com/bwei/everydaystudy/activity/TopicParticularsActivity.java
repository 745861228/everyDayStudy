package com.bwei.everydaystudy.activity;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.application.MyApplication;
import com.bwei.everydaystudy.base.BaseActivity;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.bean.TopicParticularsBean;
import com.bwei.everydaystudy.fragment.TopicParticularsFragment;
import com.bwei.everydaystudy.utils.ToastUtil;
import com.bwei.everydaystudy.utils.UrlUtils;
import com.google.gson.Gson;

import java.util.HashMap;



public class TopicParticularsActivity extends BaseActivity  {

    private ImageView user_image;
    private TextView n_title_tv;
    private TextView n_brief_tv;
    private CheckBox n_user_count_cb;
    private CheckBox n_post_count_cb;
    private CheckBox update_attention_cb;
    private TabLayout title_TabLayout;
    private ViewPager tooic_content_viewPager;
    private String nid;
    private String[] titles = {"最新", "最热"};
    private ImageView title_back;
    private TextView title_text;
    private ImageView title_postInfo;
    private ImageView image_bg;
    public AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_particulars);
        //获取具体对应的nid数据
        nid = getIntent().getStringExtra("nid");

        initView();
        //初始化数据
        initDatas();
    }


    /**
     * 初始化头部信息
     */
    private void initDatas() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("nid", nid);
        hashMap.put("order", "1");
        hashMap.put("page", 1 + "");
        new BaseData() {
            @Override
            public void setResultData(String data) {
                //解析json并复制
                Gson gson = new Gson();
                TopicParticularsBean topicParticularsBean = gson.fromJson(data, TopicParticularsBean.class);
                //为控件赋值
                setViewDatas(topicParticularsBean);
            }

            @Override
            public void setResulttError(int state) {
                ToastUtil.show(TopicParticularsActivity.this, "网络出错");
            }
        }.postData(false, false, UrlUtils.baseUrl, "api.php?c=circle&a=getCircleNameInfo", hashMap, BaseData.NOTIME);
    }

    /**
     * 头部控件复制
     *
     * @param topicParticularsBean
     */
    private void setViewDatas(TopicParticularsBean topicParticularsBean) {
        title_text.setText(topicParticularsBean.data.n_title);
        Glide.with(MyApplication.getContext()).load(topicParticularsBean.data.n_big_img).into(image_bg);
        image_bg.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(MyApplication.getContext()).load(topicParticularsBean.data.n_small_img).into(user_image);
        n_title_tv.setText(topicParticularsBean.data.n_title);
        n_brief_tv.setText(topicParticularsBean.data.n_brief);
        n_user_count_cb.setText(topicParticularsBean.data.n_user_count + "");
        n_post_count_cb.setText(topicParticularsBean.data.n_post_count + "");

    }

    /**
     * 初始化控件
     *
     * @param
     */
    private void initView() {
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        //初始化标题栏控件
        //返回图标
        title_back = (ImageView) findViewById(R.id.title_back);
        //中间带文字的textView
        title_text = (TextView) findViewById(R.id.title_text);
        //右边发表帖子图标
        title_postInfo = (ImageView) findViewById(R.id.title_postInfo);
        title_back.setVisibility(View.VISIBLE);
        title_text.setVisibility(View.VISIBLE);
        title_postInfo.setVisibility(View.VISIBLE);


        image_bg = (ImageView) findViewById(R.id.image_bg);                 //北京图
        user_image = (ImageView) findViewById(R.id.user_image);             //用户头像
        n_title_tv = (TextView) findViewById(R.id.n_title_tv);              //用户名
        n_brief_tv = (TextView) findViewById(R.id.n_brief_tv);              //介绍
        n_user_count_cb = (CheckBox) findViewById(R.id.n_user_count_cb);    // n_user_count总数
        n_post_count_cb = (CheckBox) findViewById(R.id.n_post_count_cb);    //n_post_count 总数
        update_attention_cb = (CheckBox) findViewById(R.id.update_attention_image);     //关注取消按钮

        title_TabLayout = (TabLayout) findViewById(R.id.top_Title_TabLayout);               //TabLayout
        tooic_content_viewPager = (ViewPager) findViewById(R.id.tooic_content_viewPager);   //ViewPager
        tooic_content_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(title_TabLayout));
        title_TabLayout.setupWithViewPager(tooic_content_viewPager);


        initTabLayoutViewPager();
    }

    private void initTabLayoutViewPager() {
        tooic_content_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TopicParticularsFragment.getFragment(titles[position], nid);
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
    }



}
