package com.bwei.everydaystudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.interfaces.IResetShowingPageListener;
import com.bwei.everydaystudy.utils.NetUtils;
import com.bwei.everydaystudy.view.ShowingPager;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * author by LiKe on 2017/1/13.
 */

public class CircleAttentionFragment extends BaseFragment {

    private boolean isOnline = true;
    private View view;
    private Banner circleTopicFm_banner;

    //轮播图的集合
    private List<String> imgUrl = new ArrayList<>();


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
        view = View.inflate(getActivity(), R.layout.circletopicfm_item, null);
        circleTopicFm_banner = (Banner) view.findViewById(R.id.circleTopicFm_banner);
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
        if (isOnline) {
               initDatas();
        } else {
            showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
        }

        //响应重置按钮监听事件
        showingPager.setIResetShowingPageListener(new IResetShowingPageListener() {
            @Override
            public void onResetClick(View view) {
                initDatas();
            }
        });
    }

    /**
     * 请求数据
     */
    private void initDatas() {

    }


}
