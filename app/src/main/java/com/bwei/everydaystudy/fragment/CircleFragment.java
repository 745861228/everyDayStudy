package com.bwei.everydaystudy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.interfaces.IResetShowingPageListener;
import com.bwei.everydaystudy.utils.ToastUtil;
import com.bwei.everydaystudy.view.ShowingPager;

/**
 * author by LiKe on 2017/1/10.
 */

public class CircleFragment extends BaseFragment {

    /**
     * 成功视图
     * @return
     */
    @Override
    public View setBaseSuccessView() {

        return null;
    }

    /**
     * 标题视图
     * @param v
     */
    @Override
    public void setBaseTitleView(View v) {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
    }
}
