package com.bwei.everydaystudy.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.activity.CourseListActivity;
import com.bwei.everydaystudy.adapter.CategoryGridAdapter;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.utils.CommonUtils;
import com.bwei.everydaystudy.view.ShowingPager;

import java.util.ArrayList;
import java.util.List;


/**
 * author by LiKe on 2017/1/10.
 */

public class CategoryFragment extends BaseFragment implements View.OnClickListener {
    private boolean isVisible = true;
    private boolean Visible = true;
  //  String[] arr = new String[] { "电子游戏", "互联网", "电子商务", "计算机软件", "计算机硬件", "程序员"};

    //全部分类
    private RelativeLayout fenleis, hots, interest, portfolio, hat, language;
    private LinearLayout g_hot, g_interest, L_portfolio, l_hat, l_language;
    private ImageView off, hot, iv_diamond, offs, hatss, languagess;
    private ImageView on, coffee, diamond, ons, hats, languages;
    private TextView classify, t_hod, t_diamond, t_portfolio, t_hat, t_language;

    private GridView fenlei;
    private List<String> list;
    private CategoryGridAdapter adapter;

    /**
     * 成功视图等级
     *
     * @return
     */
    @Override
    public View setBaseSuccessView() {
        View view = CommonUtils.inflate(R.layout.fragment_category);
     //初始化控件
        initView(view);
        //初始化数据
        initData();
        initGrid();


        return view;
    }

    private void initGrid() {
        adapter = new CategoryGridAdapter(getContext(), list);
        fenlei.setAdapter(adapter);

        fenlei.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"hhhhh",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), CourseListActivity.class);
                // name传到详情页
                intent.putExtra("name",list.get(position));
                startActivity(intent);
            }
        });
    }

    private void initData() {
        list = new ArrayList<String>();
        list.add("电子游戏");
        list.add("互联网");
        list.add("电子商务");
        list.add("计算机软件");
        list.add("计算机硬件");
        list.add("程序员");
    }

    //初始化控件
    private void initView(View view) {
        //热门分类
        fenleis = (RelativeLayout) view.findViewById(R.id.fenleis);
        fenlei = (GridView) view.findViewById(R.id.fenlei);
        off = (ImageView) view.findViewById(R.id.off);
        on = (ImageView) view.findViewById(R.id.on);
        classify = (TextView) view.findViewById(R.id.classify);
        //点击事件
        fenleis.setOnClickListener(this);
        //隐藏的控件
        off.setVisibility(View.INVISIBLE);

        //多彩生活
        hots = (RelativeLayout) view.findViewById(R.id.hots);
        g_hot = (LinearLayout) view.findViewById(R.id.g_hot);
        hot = (ImageView) view.findViewById(R.id.hot);
        coffee = (ImageView) view.findViewById(R.id.coffee);
        t_hod = (TextView) view.findViewById(R.id.t_hod);
        //点击事件
        hots.setOnClickListener(this);
        //隐藏的控件
        //off .setVisibility(View.INVISIBLE);
        coffee.setVisibility(View.INVISIBLE);

        //兴趣爱好
        interest = (RelativeLayout) view.findViewById(R.id.interest);
        g_interest = (LinearLayout) view.findViewById(R.id.g_interest);
        iv_diamond = (ImageView) view.findViewById(R.id.iv_diamond);
        diamond = (ImageView) view.findViewById(R.id.diamond);
        t_diamond = (TextView) view.findViewById(R.id.t_diamond);
        //点击事件
        interest.setOnClickListener(this);
        //隐藏的控件
        iv_diamond.setVisibility(View.INVISIBLE);
        //职场提示

        portfolio = (RelativeLayout) view.findViewById(R.id.portfolio);
        L_portfolio = (LinearLayout) view.findViewById(R.id.L_portfolio);
        offs = (ImageView) view.findViewById(R.id.offs);
        ons = (ImageView) view.findViewById(R.id.ons);
        t_portfolio = (TextView) view.findViewById(R.id.t_portfolio);
        //点击事件
        portfolio.setOnClickListener(this);
        //隐藏的控件
        ons.setVisibility(View.INVISIBLE);


        //热门分类
        hat = (RelativeLayout) view.findViewById(R.id.hat);
        l_hat = (LinearLayout) view.findViewById(R.id.l_hat);
        hats = (ImageView) view.findViewById(R.id.hats);
        hatss = (ImageView) view.findViewById(R.id.hatss);
        t_hat = (TextView) view.findViewById(R.id.t_hat);
        //点击事件
        hat.setOnClickListener(this);
        //隐藏的控件
        hatss.setVisibility(View.INVISIBLE);
        language = (RelativeLayout) view.findViewById(R.id.language);
        l_language = (LinearLayout) view.findViewById(R.id.l_language);
        languages = (ImageView) view.findViewById(R.id.languages);
        languagess = (ImageView) view.findViewById(R.id.languagess);
        t_language = (TextView) view.findViewById(R.id.t_language);
        //点击事件
        language.setOnClickListener(this);
        //隐藏的控件
        languages.setVisibility(View.INVISIBLE);
    }

    /**
     * 标题视图
     *
     * @param v
     */
    @Override
    public void setBaseTitleView(View v) {
        // ImageView title_back = (ImageView) v.findViewById(R.id.title_back);
        //ImageView title_addFriend = (ImageView) v.findViewById(R.id.title_addFriend);
        //title_addFriend.setVisibility(View.VISIBLE);
        //title_back.setVisibility(View.VISIBLE);
        TextView title_text = (TextView) v.findViewById(R.id.title_text);
        title_text.setVisibility(View.VISIBLE);
        ImageView title_search = (ImageView) v.findViewById(R.id.title_search);
        title_search.setVisibility(View.VISIBLE);
  title_search.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent=new Intent(getActivity(), CourseListActivity.class);
startActivity(intent);

      }
  });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
    }

    //全部分类
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fenleis:
                if (isVisible) {
                    isVisible = false;
                    fenlei.setVisibility(View.VISIBLE);
                    on.setVisibility(View.VISIBLE);
                    off.setVisibility(View.INVISIBLE);
                    //隐藏
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    L_portfolio.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    classify.setTextColor(Color.parseColor("#f95d51"));
                } else if (Visible) {

                    //隐藏qit
                    fenlei.setVisibility(View.GONE);
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    L_portfolio.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    off.setVisibility(View.VISIBLE);
                    on.setVisibility(View.INVISIBLE);
                    hot.setVisibility(View.VISIBLE);
                    coffee.setVisibility(View.INVISIBLE);
                    diamond.setVisibility(View.VISIBLE);
                    iv_diamond.setVisibility(View.INVISIBLE);
                    offs.setVisibility(View.VISIBLE);
                    ons.setVisibility(View.INVISIBLE);
                    hats.setVisibility(View.VISIBLE);
                    hatss.setVisibility(View.INVISIBLE);
                    languagess.setVisibility(View.VISIBLE);
                    languages.setVisibility(View.INVISIBLE);

                    t_hod.setTextColor(Color.parseColor("#333333"));
                    classify.setTextColor(Color.parseColor("#333333"));
                    t_diamond.setTextColor(Color.parseColor("#333333"));
                    t_portfolio.setTextColor(Color.parseColor("#333333"));
                    t_language.setTextColor(Color.parseColor("#333333"));
                    t_hat.setTextColor(Color.parseColor("#333333"));


                    isVisible = true;
                }

                break;
            case R.id.hots:
                if (isVisible) {
                    isVisible = false;
                    g_hot.setVisibility(View.VISIBLE);
                    coffee.setVisibility(View.VISIBLE);
                    hot.setVisibility(View.INVISIBLE);
                    //隐藏
                    g_interest.setVisibility(View.GONE);
                    fenlei.setVisibility(View.GONE);
                    L_portfolio.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    t_hod.setTextColor(Color.parseColor("#ff9c00"));
                } else if (Visible) {
                    //   g_hot .setVisibility(View.GONE);
                    //hot.setVisibility(View.VISIBLE);
                    //  coffee.setVisibility(View.INVISIBLE);

                    //隐藏qit
                    fenlei.setVisibility(View.GONE);
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    L_portfolio.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    off.setVisibility(View.VISIBLE);
                    on.setVisibility(View.INVISIBLE);
                    hot.setVisibility(View.VISIBLE);
                    coffee.setVisibility(View.INVISIBLE);
                    diamond.setVisibility(View.VISIBLE);
                    iv_diamond.setVisibility(View.INVISIBLE);
                    offs.setVisibility(View.VISIBLE);
                    ons.setVisibility(View.INVISIBLE);
                    hats.setVisibility(View.VISIBLE);
                    hatss.setVisibility(View.INVISIBLE);
                    languagess.setVisibility(View.VISIBLE);
                    languages.setVisibility(View.INVISIBLE);


                    t_hod.setTextColor(Color.parseColor("#333333"));
                    classify.setTextColor(Color.parseColor("#333333"));
                    t_diamond.setTextColor(Color.parseColor("#333333"));
                    t_portfolio.setTextColor(Color.parseColor("#333333"));
                    t_language.setTextColor(Color.parseColor("#333333"));
                    t_hat.setTextColor(Color.parseColor("#333333"));


                    isVisible = true;
                }

                break;

            case R.id.interest:
                if (isVisible) {
                    isVisible = false;
                    g_interest.setVisibility(View.VISIBLE);
                    iv_diamond.setVisibility(View.VISIBLE);
                    diamond.setVisibility(View.INVISIBLE);
                    //隐藏
                    fenlei.setVisibility(View.GONE);
                    g_hot.setVisibility(View.GONE);
                    L_portfolio.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    t_diamond.setTextColor(Color.parseColor("#7dcd43"));
                } else if (Visible) {
                    //  g_interest .setVisibility(View.GONE);
                    // diamond.setVisibility(View.VISIBLE);
                    //  iv_diamond.setVisibility(View.INVISIBLE);
                    //隐藏qit
                    fenlei.setVisibility(View.GONE);
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    L_portfolio.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    off.setVisibility(View.VISIBLE);
                    on.setVisibility(View.INVISIBLE);
                    hot.setVisibility(View.VISIBLE);
                    coffee.setVisibility(View.INVISIBLE);
                    diamond.setVisibility(View.VISIBLE);
                    iv_diamond.setVisibility(View.INVISIBLE);
                    offs.setVisibility(View.VISIBLE);
                    ons.setVisibility(View.INVISIBLE);
                    hats.setVisibility(View.VISIBLE);
                    hatss.setVisibility(View.INVISIBLE);
                    languagess.setVisibility(View.VISIBLE);
                    languages.setVisibility(View.INVISIBLE);
                    t_hod.setTextColor(Color.parseColor("#333333"));
                    classify.setTextColor(Color.parseColor("#333333"));
                    t_diamond.setTextColor(Color.parseColor("#333333"));
                    t_portfolio.setTextColor(Color.parseColor("#333333"));
                    t_language.setTextColor(Color.parseColor("#333333"));
                    t_hat.setTextColor(Color.parseColor("#333333"));
                    isVisible = true;
                }

                break;


            case R.id.portfolio:
                if (isVisible) {
                    isVisible = false;
                    L_portfolio.setVisibility(View.VISIBLE);
                    ons.setVisibility(View.VISIBLE);
                    offs.setVisibility(View.INVISIBLE);
                    //隐藏
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    fenlei.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    t_portfolio.setTextColor(Color.parseColor("#f95d51"));
                } else if (Visible) {
                    //隐藏qit
                    fenlei.setVisibility(View.GONE);
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    L_portfolio.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    off.setVisibility(View.VISIBLE);
                    on.setVisibility(View.INVISIBLE);
                    hot.setVisibility(View.VISIBLE);
                    coffee.setVisibility(View.INVISIBLE);
                    diamond.setVisibility(View.VISIBLE);
                    iv_diamond.setVisibility(View.INVISIBLE);
                    offs.setVisibility(View.VISIBLE);
                    ons.setVisibility(View.INVISIBLE);
                    hats.setVisibility(View.VISIBLE);
                    hatss.setVisibility(View.INVISIBLE);
                    languagess.setVisibility(View.VISIBLE);
                    languages.setVisibility(View.INVISIBLE);
                    t_hod.setTextColor(Color.parseColor("#333333"));
                    classify.setTextColor(Color.parseColor("#333333"));
                    t_diamond.setTextColor(Color.parseColor("#333333"));
                    t_portfolio.setTextColor(Color.parseColor("#333333"));
                    t_language.setTextColor(Color.parseColor("#333333"));
                    t_hat.setTextColor(Color.parseColor("#333333"));


                    isVisible = true;
                }
                break;

            case R.id.hat:
                if (isVisible) {
                    isVisible = false;
                    l_hat.setVisibility(View.VISIBLE);
                    hatss.setVisibility(View.VISIBLE);
                    hats.setVisibility(View.INVISIBLE);
                    //隐藏
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    fenlei.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    t_hat.setTextColor(Color.parseColor("#34b3ee"));
                } else if (Visible) {

                    //隐藏qit
                    fenlei.setVisibility(View.GONE);
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    L_portfolio.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    off.setVisibility(View.VISIBLE);
                    on.setVisibility(View.INVISIBLE);
                    hot.setVisibility(View.VISIBLE);
                    coffee.setVisibility(View.INVISIBLE);
                    diamond.setVisibility(View.VISIBLE);
                    iv_diamond.setVisibility(View.INVISIBLE);
                    offs.setVisibility(View.VISIBLE);
                    ons.setVisibility(View.INVISIBLE);
                    hats.setVisibility(View.VISIBLE);
                    hatss.setVisibility(View.INVISIBLE);
                    languagess.setVisibility(View.VISIBLE);
                    languages.setVisibility(View.INVISIBLE);
                    t_hod.setTextColor(Color.parseColor("#333333"));
                    classify.setTextColor(Color.parseColor("#333333"));
                    t_diamond.setTextColor(Color.parseColor("#333333"));
                    t_portfolio.setTextColor(Color.parseColor("#333333"));
                    t_language.setTextColor(Color.parseColor("#333333"));
                    t_hat.setTextColor(Color.parseColor("#333333"));

                    isVisible = true;
                }

                break;


            case R.id.language:
                if (isVisible) {
                    isVisible = false;
                    l_language.setVisibility(View.VISIBLE);
                    languages.setVisibility(View.VISIBLE);
                    languagess.setVisibility(View.INVISIBLE);
                    //隐藏
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    fenlei.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);

                    t_language.setTextColor(Color.parseColor("#90369a"));
                } else if (Visible) {

                    //隐藏qit
                    fenlei.setVisibility(View.GONE);
                    g_hot.setVisibility(View.GONE);
                    g_interest.setVisibility(View.GONE);
                    L_portfolio.setVisibility(View.GONE);
                    l_hat.setVisibility(View.GONE);
                    l_language.setVisibility(View.GONE);

                    off.setVisibility(View.VISIBLE);
                    on.setVisibility(View.INVISIBLE);
                    hot.setVisibility(View.VISIBLE);
                    coffee.setVisibility(View.INVISIBLE);
                    diamond.setVisibility(View.VISIBLE);
                    iv_diamond.setVisibility(View.INVISIBLE);
                    offs.setVisibility(View.VISIBLE);
                    ons.setVisibility(View.INVISIBLE);
                    hats.setVisibility(View.VISIBLE);
                    hatss.setVisibility(View.INVISIBLE);
                    languagess.setVisibility(View.VISIBLE);
                    languages.setVisibility(View.INVISIBLE);
                    t_hod.setTextColor(Color.parseColor("#333333"));
                    classify.setTextColor(Color.parseColor("#333333"));
                    t_diamond.setTextColor(Color.parseColor("#333333"));
                    t_portfolio.setTextColor(Color.parseColor("#333333"));
                    t_language.setTextColor(Color.parseColor("#333333"));
                    t_hat.setTextColor(Color.parseColor("#333333"));

                    isVisible = true;
                }

                break;

        }
    }


}
