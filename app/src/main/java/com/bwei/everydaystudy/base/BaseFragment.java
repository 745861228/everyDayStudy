package com.bwei.everydaystudy.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.everydaystudy.view.ShowingPager;

/**
 * author by LiKe on 2017/1/12.
 */

public abstract class BaseFragment extends Fragment {

    public ShowingPager showingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        showingPager = new ShowingPager(getActivity()) {
            @Override
            public View setSuccessView() {
                return setBaseSuccessView();
            }

            @Override
            public void setTitleView(View titleView) {
                 setBaseTitleView(titleView);
            }


        };
        return showingPager;
    }

    public abstract View setBaseSuccessView();

    public abstract void setBaseTitleView(View v);
}
