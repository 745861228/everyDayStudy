package com.bwei.everydaystudy.recycleviewholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.everydaystudy.CourseDetailsActivity;
import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseHolder;
import com.bwei.everydaystudy.bean.HomeBean;


import java.util.List;

import static android.R.id.list;

/**
 * Created by qwe on 2017/1/13.
 */

public class RecommendHolder extends BaseHolder<HomeBean.DataEntity.IndexrecommendEntity> {

    private final GridView recommend_gv;
    private final ListView recommend_lv;

    public RecommendHolder(View itemView) {
        super(itemView);
        recommend_gv = (GridView) itemView.findViewById(R.id.recommend_gv);
        recommend_lv = (ListView) itemView.findViewById(R.id.recommend_lv);
    }

    @Override
    public void setData(final Context context, HomeBean.DataEntity.IndexrecommendEntity indexrecommendEntity) {
        // gridview集合
        final List<HomeBean.DataEntity.IndexrecommendEntity.TopEntity> top = indexrecommendEntity.getTop();
        //listview集合
        final List<HomeBean.DataEntity.IndexrecommendEntity.ListviewEntity> listview = indexrecommendEntity.getListview();

        /**
         * Gridview展示
         */
        recommend_gv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return top.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                ImageView imageView = new ImageView(context);
                Glide.with(context).load(top.get(i).getCourse_pic()).into(imageView);
                return imageView;
            }
        });

        recommend_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent  =  new Intent(context, CourseDetailsActivity.class);
                intent.putExtra("cid",top.get(i).getCid());
                context.startActivity(intent);
            }
        });

        /**
         * listview展示
         */

        recommend_lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return listview.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view == null){
                    view = LayoutInflater.from(context).inflate(R.layout.recommend_lv_item,null);
                }
                ImageView recommend_iv = (ImageView) view.findViewById(R.id.recommend_iv);
                Glide.with(context).load(listview.get(i).getCourse_pic()).into(recommend_iv);
                TextView recommend_tv_titleName = (TextView) view.findViewById(R.id.recommend_tv_titleName);
                TextView recommend_tv_schollname = (TextView) view.findViewById(R.id.recommend_tv_schollname);
                TextView recommend_tv_price = (TextView) view.findViewById(R.id.recommend_tv_price);
                TextView recommend_tv_usercount = (TextView) view.findViewById(R.id.recommend_tv_usercount);
                recommend_tv_titleName.setText(listview.get(i).getCourse_name());
                recommend_tv_schollname.setText(listview.get(i).getSchool_name());
                recommend_tv_usercount.setText(listview.get(i).getUsercount()+"人在学");
                if(!listview.get(i).getCourse_price().equals("0.00")){
                    recommend_tv_price.setText("￥ "+listview.get(i).getCourse_price());
                    recommend_tv_price.setTextColor(Color.RED);
                }
                return view;
            }
        });

        recommend_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent  =  new Intent(context, CourseDetailsActivity.class);
                intent.putExtra("cid",listview.get(i).getCid()+"");
                context.startActivity(intent);
            }
        });

    }
}
