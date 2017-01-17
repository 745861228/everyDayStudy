package com.bwei.everydaystudy.recycleviewholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.everydaystudy.CourseDetailsActivity;
import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseHolder;
import com.bwei.everydaystudy.bean.HomeBean;
import java.util.List;

/**
 * Created by qwe on 2017/1/13.
 */

public class EveryoneLearningHolder extends BaseHolder<List<HomeBean.DataEntity.IndexothersEntity>> {

    private final ListView everone_lv;

    public EveryoneLearningHolder(View itemView) {
        super(itemView);
        everone_lv = (ListView) itemView.findViewById(R.id.everone_lv);
    }

    @Override
    public void setData(final Context context, final List<HomeBean.DataEntity.IndexothersEntity> list) {

        everone_lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
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
                if (view == null) {
                    view = LayoutInflater.from(context).inflate(R.layout.recommend_lv_item, null);
                }
                ImageView recommend_iv = (ImageView) view.findViewById(R.id.recommend_iv);
                Glide.with(context).load(list.get(i).getCourse_pic()).into(recommend_iv);
                TextView recommend_tv_titleName = (TextView) view.findViewById(R.id.recommend_tv_titleName);
                TextView recommend_tv_schollname = (TextView) view.findViewById(R.id.recommend_tv_schollname);
                TextView recommend_tv_price = (TextView) view.findViewById(R.id.recommend_tv_price);
                TextView recommend_tv_usercount = (TextView) view.findViewById(R.id.recommend_tv_usercount);
                recommend_tv_titleName.setText(list.get(i).getCourse_name());
                recommend_tv_schollname.setText(list.get(i).getSchool_name());
                recommend_tv_usercount.setText(list.get(i).getUsercount() + "人在学");
                if (!list.get(i).getCourse_price().equals("0.00")) {
                    recommend_tv_price.setText("￥ "+list.get(i).getCourse_price());
                    recommend_tv_price.setTextColor(Color.RED);
                }
                return view;
            }
        });

        everone_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent  =  new Intent(context, CourseDetailsActivity.class);
                    intent.putExtra("cid",list.get(i).getCid()+"");
                    context.startActivity(intent);
            }
        });

    }
}
