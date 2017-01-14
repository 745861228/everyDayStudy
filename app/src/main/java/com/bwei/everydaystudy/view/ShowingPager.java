package com.bwei.everydaystudy.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.interfaces.IResetShowingPageListener;
import com.bwei.everydaystudy.utils.CommonUtils;
import com.bwei.everydaystudy.utils.NetUtils;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * author by LiKe on 2017/1/12.
 */

public abstract class ShowingPager extends FrameLayout implements View.OnClickListener {

    /**
     * 定义状态
     */
    public static final int STATE_LOADING = 1;
    public static final int STATE_LOAD_ERROR = 2;
    public static final int STATE_LOAD_SUCCESS = 3;
    private final Context context;

    //定义当前状态
    public int currentState = STATE_LOADING;//得到当前的状态
    private final View view;
    private final View showLoadError, showLoading;
    private final FrameLayout showFrameLayout;
    private final AutoLinearLayout titleLayout;
    private final TextView showing_error_tv_reset;
    private IResetShowingPageListener iResetShowingPageListener;
    private final LayoutParams params;
    private final View titleView;

    public ShowingPager(Context context) {
        super(context);
        this.context = context;

        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //获取主布局视图
        view = View.inflate(context, R.layout.showing_pager, null);
        titleLayout = (AutoLinearLayout) view.findViewById(R.id.showLinearLayoutTitle);
        showLoadError = view.findViewById(R.id.showLoadError);
        showLoading = view.findViewById(R.id.showLoading);
        showFrameLayout = (FrameLayout) view.findViewById(R.id.showFrameLayout);
        this.addView(view,params);

        //查找重置按钮
        showing_error_tv_reset = (TextView) showLoadError.findViewById(R.id.showing_error_tv_reset);
        showing_error_tv_reset.setOnClickListener(this);


        //添加title布局
        titleView = LayoutInflater.from(getContext()).inflate(R.layout.common_title,null);
        titleLayout.addView(titleView,params);
        setTitleView(titleView);

        /**
         * 添加成功视图
         */
        View successView = setSuccessView();
        if (successView == null){
            showFrameLayout.setVisibility(View.GONE);
        }else {
            showFrameLayout.removeAllViews();
            showFrameLayout.addView(successView,params);
        }
        showPage();
    }

    //添加成功的视图
    public abstract View setSuccessView();

    //添加Title
    public abstract void setTitleView(View titleView);

    //设置当前状态
    public void setCurrentState(StateType stateType) {
        currentState = stateType.currentState;
        showPage();
    }

    private void showPage() {
        //在主线程执行
        CommonUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                showPageOnUI();
            }
        });
    }

    private void showPageOnUI() {
        showLoading.setVisibility(currentState == STATE_LOADING ? View.VISIBLE : View.GONE);
        showLoadError.setVisibility(currentState == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);
        showFrameLayout.setVisibility(currentState == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);
    }


    public void goneTitleListener(){
        titleLayout.setVisibility(GONE);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.showing_error_tv_reset:
                if (NetUtils.isHaveNet()) {
                    /*if (currentState != STATE_LOADING)
                        currentState = STATE_LOADING;
                    showPage();
                    onLoad();*/
                    this.setCurrentState(StateType.STATE_LOADING);
                    if(iResetShowingPageListener!=null){
                        iResetShowingPageListener.onResetClick(view);
                    }
                }
                break;
        }
    }

    /**
     * 枚举类
     */
    public enum StateType {
        //请求类型
        STATE_LOADING(1), STATE_LOAD_ERROR(2), STATE_LOAD_SUCCESS(3);
        private final int currentState;

        StateType(int currentState) {
            this.currentState = currentState;
        }

        public int getCurrentState() {
            return currentState;
        }
    }

    public void setIResetShowingPageListener(IResetShowingPageListener iResetShowingPageListener){
        this.iResetShowingPageListener = iResetShowingPageListener;
    }

}
