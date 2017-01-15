package com.bwei.everydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bwei.everydaystudy.base.BaseFragment;

/**
 * author by LiKe on 2017/1/15.
 */

public class HotTitlesFragment extends BaseFragment {
    @Override
    public View setBaseSuccessView() {
        return null;
    }

    @Override
    public void setBaseTitleView(View v) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showingPager.goneTitleListener();
        initDatas();
    }

    /**
     * 请求数据
     */
    private void initDatas() {
        String title = this.getArguments().getString("title");

    }

    public static Fragment getTitleFragment(String title){
        HotTitlesFragment hotTitlesFragment = new HotTitlesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        hotTitlesFragment.setArguments(bundle);
        return hotTitlesFragment;
    }
}
