package com.bwei.everydaystudy.recycleviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseHolder;
import com.bwei.everydaystudy.bean.HomeBean;
import com.bwei.everydaystudy.recycleviewholder.ClassificationHolder;
import com.bwei.everydaystudy.recycleviewholder.EveryoneLearningHolder;
import com.bwei.everydaystudy.recycleviewholder.ExhibitionHolder;
import com.bwei.everydaystudy.recycleviewholder.HomeFmHolder;
import com.bwei.everydaystudy.recycleviewholder.PopularcourseHolder;
import com.bwei.everydaystudy.recycleviewholder.RecommendHolder;
import com.zhy.autolayout.AutoLinearLayout;


/**
 * Created by qwe on 2017/1/12.
 */

public class HomeFmAdapter extends RecyclerView.Adapter<BaseHolder> {
    private static final int CAROUSELFIGURE = 0;
    private static final int CLASSIFICATION = 1;
    private static final int EXHIBITION = 2;
    private static final int POPULARCOURSE = 3;
    private static final int RECOMMEND = 4;
    private static final int EVERYONELEARNING = 5;

    private Context context;
    private HomeBean homeBean;

    public HomeFmAdapter(Context context, HomeBean homeBean) {
        this.context = context;
        this.homeBean = homeBean;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseHolder holder = null;
        View view = null;
        switch (viewType) {
            case CAROUSELFIGURE:
                view = LayoutInflater.from(context).inflate(R.layout.carouselfigure_item, null);
                holder = new HomeFmHolder(view);
                break;
            case CLASSIFICATION:
                view = LayoutInflater.from(context).inflate(R.layout.classification, null);
                holder = new ClassificationHolder(view);
                break;
            case EXHIBITION:
                AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT);
                view = LayoutInflater.from(context).inflate(R.layout.exhibition_item, null);
                view.setLayoutParams(params);
                holder = new ExhibitionHolder(view);
                break;
            case POPULARCOURSE:
                view = LayoutInflater.from(context).inflate(R.layout.popularcourse_item, null);
                holder = new PopularcourseHolder(view);
                break;
            case RECOMMEND:
                view = LayoutInflater.from(context).inflate(R.layout.recommend, null);
                holder = new RecommendHolder(view);
                break;
            case EVERYONELEARNING:
                view = LayoutInflater.from(context).inflate(R.layout.everyone_learning,null);
                holder = new EveryoneLearningHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (position == CAROUSELFIGURE) {
            holder.setData(context, homeBean.getData().getSlider());
        } else if (position == CLASSIFICATION) {
            holder.setData(context, homeBean.getData().getHotcategory());
        } else if (position == EXHIBITION) {
            holder.setData(context, homeBean.getData().getAdlist());
        } else if (position == POPULARCOURSE) {
            holder.setData(context, homeBean.getData().getHotcourse());
        } else if (position == RECOMMEND) {
            holder.setData(context, homeBean.getData().getIndexrecommend());
        } else if(position == EVERYONELEARNING){
            holder.setData(context,homeBean.getData().getIndexothers());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == CAROUSELFIGURE) {
            return CAROUSELFIGURE;
        } else if (position == CLASSIFICATION) {
            return CLASSIFICATION;
        } else if (position == EXHIBITION) {
            return EXHIBITION;
        } else if (position == POPULARCOURSE) {
            return POPULARCOURSE;
        } else if (position == RECOMMEND) {
            return RECOMMEND;
        } else if (position == EVERYONELEARNING) {
            return EVERYONELEARNING;
        }
        return 0;
    }


    @Override
    public int getItemCount() {
        return 6;
    }
}
