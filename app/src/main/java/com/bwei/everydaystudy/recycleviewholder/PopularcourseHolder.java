package com.bwei.everydaystudy.recycleviewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseHolder;
import com.bwei.everydaystudy.bean.HomeBean;

import java.util.List;

/**
 * Created by qwe on 2017/1/12.
 */

public class PopularcourseHolder extends BaseHolder<List<HomeBean.DataEntity.HotcourseEntity>> {

    private final GridView popilar_gv;

    public PopularcourseHolder(View itemView) {
        super(itemView);
        popilar_gv = (GridView) itemView.findViewById(R.id.popilar_gv);
    }

    @Override
    public void setData(final Context context, final List<HomeBean.DataEntity.HotcourseEntity> list) {
        popilar_gv.setAdapter(new BaseAdapter() {
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
                    if(view == null){
                        view = LayoutInflater.from(context).inflate(R.layout.popularcourse,null);
                    }
                ImageView popularcourse_iv = (ImageView) view.findViewById(R.id.popularcourse_iv);
                TextView popularcourse_name = (TextView) view.findViewById(R.id.popularcourse_name);
                TextView popularcourse_title = (TextView) view.findViewById(R.id.popularcourse_title);
                Glide.with(context).load(list.get(i).getImg()).into(popularcourse_iv);
                popularcourse_name.setText(list.get(i).getName());
                popularcourse_title.setText(list.get(i).getTitle());
                return view;
            }
        });

    }
}
