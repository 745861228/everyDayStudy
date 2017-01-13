package com.bwei.everydaystudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.interfaces.IResetShowingPageListener;
import com.bwei.everydaystudy.utils.NetUtils;
import com.bwei.everydaystudy.view.ShowingPager;



/**
 * author by LiKe on 2017/1/10.
 */

public class HomeFragment extends BaseFragment {


    private TextView textView;
    private boolean isOnline = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //进入程序后判断网络状态
        if (!NetUtils.isHaveNet()) {
            isOnline = false;
        }

    }

    /**
     * 成功视图
     *
     * @return
     */
    @Override
    public View setBaseSuccessView() {
        textView = new TextView(getActivity());
        textView.setText("********************************************few");
        return textView;
    }

    @Override
    public void setBaseTitleView(View v) {

    }


    /**
     * 创建标题栏视图
     * @return
     */


    /**
     * 执行相应的逻辑操作
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        new BaseData() {
            @Override
            public void setResultData(String data) {
                textView.setText(data);
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);

            }

            @Override
            public void setResulttError(int state) {
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
            }
        }.getData("http://www.baidu.com", "http://www.baidu.com", BaseData.NORMALTIME);
    }
}
