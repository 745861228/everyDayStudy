package com.bwei.everydaystudy.base;

import android.os.Bundle;
import android.view.View;

import com.bwei.everydaystudy.interfaces.IResetShowingPageListener;
import com.bwei.everydaystudy.utils.NetUtils;
import com.bwei.everydaystudy.view.ShowingPager;
import com.zhy.autolayout.AutoLayoutActivity;


/**
 * author by LiKe on 2017/1/12.
 */

public abstract class BaseShowingActivity extends AutoLayoutActivity {

    public ShowingPager showingPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showingPager = new ShowingPager(this) {
            @Override
            public View setSuccessView() {
                return setBaseSuccessView();
            }
            @Override
            public void setTitleView(View titleView) {
                    setBaseTitleView(titleView);
                setBaseTitleView(titleView);
            }
        };

        setContentView(showingPager);

        //调用onLoad()方法加载数据
        //首先判断网络状态
        if (!NetUtils.isHaveNet()) {
            showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
        } else {
            onLoad();
        }

        //刷新数据
        showingPager.setIResetShowingPageListener(new IResetShowingPageListener() {
            @Override
            public void onResetClick(View view) {
                onLoad();
            }
        });
    }

    protected abstract void setBaseTitleView(View titleView);

    //创建成功展示的视图
    public abstract View setBaseSuccessView();

    //创建标题视图
    public abstract void setBaseTitleView(View titleView);

    //加载数据
    public abstract void onLoad();
}
