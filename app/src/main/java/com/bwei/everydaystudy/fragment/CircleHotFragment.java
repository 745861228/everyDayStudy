package com.bwei.everydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.view.ShowingPager;

/**
 * author by LiKe on 2017/1/13.
 */

public class CircleHotFragment extends BaseFragment {
    @Override
    public View setBaseSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText("热门");
        return textView;
    }

    @Override
    public void setBaseTitleView(View v) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //隐藏标题栏
        showingPager.goneTitleListener();
        showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
    }
}
