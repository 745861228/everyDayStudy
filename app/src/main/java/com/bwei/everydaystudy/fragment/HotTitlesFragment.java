package com.bwei.everydaystudy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.base.BaseFragment;
import com.bwei.everydaystudy.bean.HotTitlesBean;
import com.bwei.everydaystudy.bean.HotTitlesContentBean;
import com.bwei.everydaystudy.bean.LableBean;
import com.bwei.everydaystudy.bean.SendCodeBean;
import com.bwei.everydaystudy.utils.DividerItemDecoration;
import com.bwei.everydaystudy.utils.LogUtils;
import com.bwei.everydaystudy.utils.ShowDialog;
import com.bwei.everydaystudy.utils.UrlUtils;
import com.bwei.everydaystudy.view.MyHeader;
import com.bwei.everydaystudy.view.ShowingPager;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.melnykov.fab.FloatingActionButton;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import recyclerview.CommonAdapter;
import recyclerview.base.ViewHolder;




/**
 * author by LiKe on 2017/1/15.
 */

public class HotTitlesFragment extends BaseFragment implements SpringView.OnFreshListener {

    private RecyclerView hot_titles_fm_item_recyclerView;
    private FloatingActionButton floatingActionButton;
    private int page = 1;
    private SpringView hot_titles_fm_item_springView;
    private boolean isRefreshLoad = true;
    private ArrayList<HotTitlesContentBean.DataBean> refreshLoadList = new ArrayList<>();
    private CommonAdapter<HotTitlesContentBean.DataBean> commonAdapter;

    @Override
    public View setBaseSuccessView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.hot_titles_fm_item, null);
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        hot_titles_fm_item_springView = (SpringView) view.findViewById(R.id.hot_titles_fm_item_springView);
        //设置下拉刷新，上拉加载
        hot_titles_fm_item_springView.setListener(this);
        //设置springView默认头和尾
        hot_titles_fm_item_springView.setHeader(new MyHeader());
        hot_titles_fm_item_springView.setFooter(new DefaultFooter(getActivity()));
        //设置springView头部隐藏
        hot_titles_fm_item_springView.setType(SpringView.Type.FOLLOW);

        //recyclerView      floatingActionButton
        hot_titles_fm_item_recyclerView = (RecyclerView) view.findViewById(R.id.hot_titles_fm_item_recyclerView);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        //设置recyclerView布局管理
        hot_titles_fm_item_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerView条目间隔
        hot_titles_fm_item_recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
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
        HotTitlesBean.DataBean dataBean = (HotTitlesBean.DataBean) this.getArguments().getSerializable("dataBean");
        HashMap<String, String> hashMap = new HashMap<>();
        if (!TextUtils.isEmpty(dataBean.tid)) {
            hashMap.put("tid", dataBean.tid);
            hashMap.put("page", page + "");
            //请求数据
            new BaseData() {
                @Override
                public void setResultData(String data) {
                    if (data != null) {
                        showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
                        Gson gson = new Gson();
                        HotTitlesContentBean hotTitlesContentBean = gson.fromJson(data, HotTitlesContentBean.class);
                        //设置recyclerView适配器
                        initRecyclerViewDatas(hotTitlesContentBean);
                    }
                }

                @Override
                public void setResulttError(int state) {
                    showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
                }
            }.postData(false, false, UrlUtils.baseUrl, "api.php?c=circle&a=getCirclePostListByTid", hashMap, BaseData.NORMALTIME);
        }

    }

    /**
     * 设置recyclerView适配器
     *
     * @param hotTitlesContentBean
     */
    private void initRecyclerViewDatas(HotTitlesContentBean hotTitlesContentBean) {
        List<HotTitlesContentBean.DataBean> dataBeanList = hotTitlesContentBean.data;
        if (isRefreshLoad) {
            refreshLoadList.clear();
            refreshLoadList.addAll(dataBeanList);
        } else {
            refreshLoadList.addAll(dataBeanList);
        }
        if (commonAdapter == null) {
            setRecyclerViewAdapter(refreshLoadList);
            hot_titles_fm_item_recyclerView.setAdapter(commonAdapter);
        } else {
            commonAdapter.notifyDataSetChanged();
        }

        floatingActionButton.attachToRecyclerView(hot_titles_fm_item_recyclerView);
    }

    private void setRecyclerViewAdapter(final List<HotTitlesContentBean.DataBean> refreshLoadList) {
        commonAdapter = new CommonAdapter<HotTitlesContentBean.DataBean>(getActivity(), R.layout.hottitlesfm_rv_item, refreshLoadList) {
            @Override
            protected void convert(ViewHolder holder, HotTitlesContentBean.DataBean dataBean, int position) {
                //分割线
                AutoLinearLayout divider_linearLayout = holder.getView(R.id.divider_linearLayout);
                if (position == 0) {
                    divider_linearLayout.setVisibility(View.GONE);
                } else {
                    divider_linearLayout.setVisibility(View.VISIBLE);
                }
                //查找控件
                CircleImageView user_small_log_image = holder.getView(R.id.user_small_log_image);       //用户头像
                Glide.with(getActivity()).load(dataBean.user_small_log).into(user_small_log_image);     //设置用户头像
                holder.setText(R.id.user_name_tv, dataBean.user_name);                                   //设置用户名
                CheckBox attention_cb = holder.getView(R.id.attention_cb);                              //查找关注按钮
                //查找图片按钮
                AutoLinearLayout three_linearLayout = holder.getView(R.id.three_linearLayout);          //三张图片的父控件
                ImageView left_image = holder.getView(R.id.left_image);                                 //左边图
                ImageView middle_image = holder.getView(R.id.middle_image);                             //中间边图
                ImageView right_image = holder.getView(R.id.right_image);                               //右边图
                left_image.setScaleType(ImageView.ScaleType.FIT_XY);
                middle_image.setScaleType(ImageView.ScaleType.FIT_XY);
                right_image.setScaleType(ImageView.ScaleType.FIT_XY);

                AutoLinearLayout two_linearLayout = holder.getView(R.id.two_linearLayout);              //两张图片的父控件
                ImageView image_left = holder.getView(R.id.image_left);                                 //左边图
                ImageView image_right = holder.getView(R.id.image_right);
                image_left.setScaleType(ImageView.ScaleType.FIT_XY);//右边图
                image_right.setScaleType(ImageView.ScaleType.FIT_XY);//右边图
                Gson gson = new Gson();

                ImageView large_image = holder.getView(R.id.large_image);                               //一张大图
                large_image.setScaleType(ImageView.ScaleType.FIT_XY);
                //获取图片数据
                String source = (String) dataBean.source;
                //判断数据是否为空
                if (!TextUtils.isEmpty(source)) {
                    //解析数据

                    String[] imageArray = gson.fromJson(source, String[].class);
                    if (imageArray.length >= 3) {
                        two_linearLayout.setVisibility(View.GONE);
                        large_image.setVisibility(View.GONE);
                        three_linearLayout.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imageArray[0]).placeholder(R.mipmap.default_three).into(left_image);
                        Glide.with(getActivity()).load(imageArray[1]).placeholder(R.mipmap.default_three).into(middle_image);
                        Glide.with(getActivity()).load(imageArray[2]).placeholder(R.mipmap.default_three).into(right_image);
                    } else if (imageArray.length == 2) {
                        large_image.setVisibility(View.GONE);
                        three_linearLayout.setVisibility(View.GONE);
                        two_linearLayout.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imageArray[0]).placeholder(R.mipmap.default_two).into(image_left);
                        Glide.with(getActivity()).load(imageArray[1]).placeholder(R.mipmap.default_two).into(image_right);
                    } else if (imageArray.length == 1) {
                        three_linearLayout.setVisibility(View.GONE);
                        two_linearLayout.setVisibility(View.GONE);
                        large_image.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imageArray[0]).placeholder(R.mipmap.default_one).into(large_image);
                    }
                }

                holder.setText(R.id.p_title_tv, dataBean.p_title);                   //设置title
                //holder.setText(R.id.p_content_tv,dataBean.p_content);               //设置content
              //  holder.setText(R.id.p_tids_tv, dataBean.p_tids);                     //设置tids

                /*标签*/
                TextView p_tids_tv = holder.getView(R.id.p_tids_tv);
                String p_tids = dataBean.p_tids;
                Spanned spanned = Html.fromHtml(p_tids);
                LableBean[] lableBeen = gson.fromJson(spanned.toString(), LableBean[].class);
                p_tids_tv.setText("#"+lableBeen[0].getTname()+"#");

                holder.setText(R.id.dianzan_tv, dataBean.p_dig);
                holder.setText(R.id.share_tv, dataBean.p_sharecount);
                holder.setText(R.id.message_tv, dataBean.p_replycount);

                //分享按钮
                AutoLinearLayout share_linearLayout = holder.getView(R.id.share_linearLayout);
                share_linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new ShowDialog(getActivity());
                    }
                });
            }
        };
    }

    public static Fragment getTitleFragment(HotTitlesBean.DataBean dataBean) {
        HotTitlesFragment hotTitlesFragment = new HotTitlesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataBean", dataBean);
        hotTitlesFragment.setArguments(bundle);
        return hotTitlesFragment;
    }

    @Override
    public void onRefresh() {
        page = 1;
        isRefreshLoad = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initDatas();
                lode();
            }
        }, 2000);
    }

    @Override
    public void onLoadmore() {
        page++;
        isRefreshLoad = false;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initDatas();
                lode();
            }
        }, 2000);
    }

    //停止刷新
    public void lode() {
        hot_titles_fm_item_springView.scrollTo(0, 0);
    }


    @Override
    public void onStop() {
        super.onStop();
        commonAdapter = null;
    }
}
