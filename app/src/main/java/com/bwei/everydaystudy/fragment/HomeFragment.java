package com.bwei.everydaystudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.bean.HomeBean;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.interfaces.IResetShowingPageListener;
import com.bwei.everydaystudy.recycleviewadapter.HomeFmAdapter;
import com.bwei.everydaystudy.utils.NetUtils;
import com.bwei.everydaystudy.view.ShowingPager;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;

import static com.bwei.everydaystudy.R.id.home_recyclerview;
import static com.bwei.everydaystudy.R.id.springview;


/**
 * author by LiKe on 2017/1/10.
 */

public class HomeFragment extends BaseFragment {


    private boolean isOnline = true;
    private View view;
    private SpringView springview;
    private RecyclerView home_recyclerview;

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
        // 加载成功试图
        View view = initView();
        return view;
    }

    private View initView() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.home_fragment_item,null);
        springview = (SpringView) view.findViewById(R.id.springview);
        home_recyclerview = (RecyclerView) view.findViewById(R.id.home_recyclerview);
        home_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void setBaseTitleView(View v) {
        ImageView title_home = (ImageView) v.findViewById(R.id.title_home);
        ImageView title_search = (ImageView) v.findViewById(R.id.title_search);
        title_home.setVisibility(View.VISIBLE);
        title_search.setVisibility(View.VISIBLE);
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
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(data, HomeBean.class);
                home_recyclerview.setAdapter(new HomeFmAdapter(getActivity(),homeBean));
            }

            @Override
            public void setResulttError(int state) {
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
            }
        }.getData("http://www.meirixue.com", "http://www.meirixue.com/api.php?a=indexv9&c=index", BaseData.NORMALTIME);
    }
}
