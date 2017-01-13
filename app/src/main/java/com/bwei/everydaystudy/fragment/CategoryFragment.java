package com.bwei.everydaystudy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.view.ShowingPager;


/**
 * author by LiKe on 2017/1/10.
 */

public class CategoryFragment extends BaseFragment {


    @Override
    public View setBaseSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText("********************************************few");
        return textView;
    }


    @Override
    public void setBaseTitleView(View v) {
        ImageView title_back = (ImageView) v.findViewById(R.id.title_back);
        ImageView title_addFriend = (ImageView) v.findViewById(R.id.title_addFriend);
        title_addFriend.setVisibility(View.VISIBLE);
        title_back.setVisibility(View.VISIBLE);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
    }
}
