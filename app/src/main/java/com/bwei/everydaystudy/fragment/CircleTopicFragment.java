package com.bwei.everydaystudy.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.activity.TopicParticularsActivity;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.bean.CircleAttentionBean;
import com.bwei.everydaystudy.interfaces.IResetShowingPageListener;
import com.bwei.everydaystudy.manager.GlideImageLoader;
import com.bwei.everydaystudy.utils.CommonUtils;
import com.bwei.everydaystudy.utils.DividerItemDecoration;
import com.bwei.everydaystudy.utils.LogUtils;
import com.bwei.everydaystudy.utils.NetUtils;
import com.bwei.everydaystudy.utils.ToastUtil;
import com.bwei.everydaystudy.utils.UrlUtils;
import com.bwei.everydaystudy.view.MyHeader;
import com.bwei.everydaystudy.view.ShowingPager;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import recyclerview.CommonAdapter;
import recyclerview.base.ViewHolder;


/**
 * author by LiKe on 2017/1/13.
 */

public class CircleTopicFragment extends BaseFragment implements SpringView.OnFreshListener {
    private boolean isOnline = true;
    private View view;
    private Banner circleTopicFm_banner;

    //轮播图的集合
    private List<String> imgUrl = new ArrayList<>();
    private SpringView circleTopicFm_springView;
    private TextView mineCircle_tv;
    private RecyclerView mineRecyclerView;
    private TextView hotCircle_tv;
    private RecyclerView hotRecyclerView;
    private CommonAdapter<CircleAttentionBean.DataBean.CircleBean> commonAdapter;
    private ArrayList<CircleAttentionBean.DataBean.CircleBean> myCircleList = new ArrayList<>();
    private CommonAdapter<CircleAttentionBean.DataBean.CircleBean> mineCommonAdapter;
    private AutoLinearLayout myAutoLinearLayout;

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
        view = View.inflate(getActivity(), R.layout.circle_topicfm_item, null);
        initView();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        circleTopicFm_banner = (Banner) view.findViewById(R.id.circleTopicFm_banner);
        circleTopicFm_springView = (SpringView) view.findViewById(R.id.circleTopicFm_springView);

        //我的圈子
        myAutoLinearLayout = (AutoLinearLayout) view.findViewById(R.id.myAutoLinearLayout);
        mineCircle_tv = (TextView) view.findViewById(R.id.mineCircle_tv);
        mineRecyclerView = (RecyclerView) view.findViewById(R.id.mineRecyclerView);
        mineRecyclerView.setFocusable(false);
        //热门圈子
        hotCircle_tv = (TextView) view.findViewById(R.id.hotCircle_tv);
        hotRecyclerView = (RecyclerView) view.findViewById(R.id.hotRecyclerView);
        hotRecyclerView.setFocusable(false);
        //设置下拉刷新，上拉加载
        circleTopicFm_springView.setListener(this);
        //设置springView默认头和尾
        circleTopicFm_springView.setHeader(new MyHeader());
        // home_springView.setFooter(new DefaultFooter(getActivity()));
        //设置springView头部隐藏
        circleTopicFm_springView.setType(SpringView.Type.FOLLOW);


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
        new BaseData() {
            @Override
            public void setResultData(String data) {
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                CircleAttentionBean circleAttentionBean = gson.fromJson(data, CircleAttentionBean.class);
                //设置轮播图方法
                setBannerDatas(circleAttentionBean);
                //设置热门圈子
                setHotCircleDatas(circleAttentionBean);
            }

            @Override
            public void setResulttError(int state) {
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
            }
        }.getData("http://www.meirixue.com/", "http://www.meirixue.com/api.php?c=circle&a=getCircleNamesIndexV2", BaseData.NORMALTIME);
    }


    /**
     * 热门圈子
     *
     * @param circleAttentionBean
     */
    private void setHotCircleDatas(CircleAttentionBean circleAttentionBean) {
        final List<CircleAttentionBean.DataBean.CircleBean> circleBeanList = circleAttentionBean.data.circle;
        //设置recyclerView布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        hotRecyclerView.setLayoutManager(linearLayoutManager);
        hotRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));


        commonAdapter = new CommonAdapter<CircleAttentionBean.DataBean.CircleBean>(getActivity(), R.layout.circletopicfm_rv_item, circleBeanList) {

            @Override
            protected void convert(final ViewHolder holder, final CircleAttentionBean.DataBean.CircleBean circleBean, final int position) {
                ImageView n_small_img = holder.getView(R.id.n_small_img);
                Glide.with(getActivity()).load(circleBean.n_small_img).into(n_small_img);
                holder.setText(R.id.n_title_tv, circleBean.n_title);
                holder.setText(R.id.n_brief_tv, circleBean.n_brief);
                holder.setText(R.id.n_user_count_tv, circleBean.n_user_count + "人关注");
                holder.setText(R.id.n_post_count_tv, circleBean.n_post_count + "帖子");
                ImageView addAttention_img = holder.getView(R.id.addAttention_img);
                addAttention_img.setVisibility(View.VISIBLE);
                AutoLinearLayout parent_linearLayout = holder.getView(R.id.parent_linearLayout);
                parent_linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), TopicParticularsActivity.class);
                        intent.putExtra("nid", circleBean.nid + "");
                        getActivity().startActivity(intent);
                    }
                });


                //点击删除
                addAttention_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       /* //获取cookie
                        String cookie = CommonUtils.getString("cookie");
                        LogUtils.i("cookie",cookie);
                        //判断是否为登陆状态
                       // isLoginState();*/
                        Log.i("aaaaaaaaaaaaaaa", "onClick: " + position);
                        int adapterPosition = holder.getAdapterPosition();
                        commonAdapter.notifyItemRemoved(adapterPosition);
                        myCircleList.add(circleBeanList.get(adapterPosition));
                        circleBeanList.remove(adapterPosition);
                        if (myCircleList.size() > 0) {
                            mineCircle_tv.setVisibility(View.VISIBLE);
                            mineRecyclerView.setVisibility(View.VISIBLE);
                            myAutoLinearLayout.setVisibility(View.VISIBLE);
                            setMineRecyclerViewAdapater();
                        }
                    }

                    private void setMineRecyclerViewAdapater() {
                        mineRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        if (mineCommonAdapter == null) {
                            mineCommonAdapter = new CommonAdapter<CircleAttentionBean.DataBean.CircleBean>(getActivity(), R.layout.circletopicfm_rv_item, myCircleList) {
                                @Override
                                protected void convert(final ViewHolder holder, final CircleAttentionBean.DataBean.CircleBean circleBean, final int position) {
                                    ImageView n_small_img = holder.getView(R.id.n_small_img);
                                    Glide.with(getActivity()).load(circleBean.n_small_img).into(n_small_img);
                                    holder.setText(R.id.n_title_tv, circleBean.n_title);
                                    holder.setText(R.id.n_brief_tv, circleBean.n_brief);
                                    holder.setText(R.id.n_user_count_tv, circleBean.n_user_count + "人关注");
                                    holder.setText(R.id.n_post_count_tv, circleBean.n_post_count + "帖子");
                                    ImageView addAttention_img = holder.getView(R.id.addAttention_img);
                                    ImageView cannelAttention_img = holder.getView(R.id.cannelAttention_img);
                                    addAttention_img.setVisibility(View.GONE);
                                    cannelAttention_img.setVisibility(View.VISIBLE);
                                    AutoLinearLayout parent_linearLayout = holder.getView(R.id.parent_linearLayout);

                                    parent_linearLayout.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(getActivity(), TopicParticularsActivity.class);
                                            intent.putExtra("nid", circleBean.nid + "");
                                            getActivity().startActivity(intent);
                                        }
                                    });
                                }
                            };
                            mineRecyclerView.setAdapter(mineCommonAdapter);
                        } else {
                            mineCommonAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        };
        hotRecyclerView.setAdapter(commonAdapter);
    }

    /**
     * 设置轮播图
     *
     * @param circleAttentionBean
     */
    private void setBannerDatas(CircleAttentionBean circleAttentionBean) {
        imgUrl.clear();
        for (int i = 0; i < circleAttentionBean.data.banner.size(); i++) {
            imgUrl.add(circleAttentionBean.data.banner.get(i).img);
        }
        //设置banner样式
        circleTopicFm_banner.setImageLoader(new GlideImageLoader());
        circleTopicFm_banner.setImages(imgUrl);
        circleTopicFm_banner.start();
        circleTopicFm_banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.show(getActivity(), "点击了" + position);
            }
        });
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        lode();
    }

    @Override
    public void onLoadmore() {

    }

    //停止刷新
    public void lode() {
        circleTopicFm_springView.scrollTo(0, 0);
    }
}
