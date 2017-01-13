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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwe on 2017/1/12.
 */

public class ClassificationHolder extends BaseHolder<List<HomeBean.DataEntity.HotcategoryEntity>> {

    private final GridView classification_gv;
    private List<HomeBean.DataEntity.HotcategoryEntity> myList = new ArrayList<>();

    public ClassificationHolder(View itemView) {
        super(itemView);
        classification_gv = (GridView) itemView.findViewById(R.id.classification_gv);
    }

    @Override
    public void setData(final Context context, List<HomeBean.DataEntity.HotcategoryEntity> list) {
        myList.clear();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                myList.add(list.get(i));
            }
        }
        classification_gv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return myList.size();
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
                    view = LayoutInflater.from(context).inflate(R.layout.classification_item, null);
                }
                ImageView classification_iv = (ImageView) view.findViewById(R.id.classification_iv);
                TextView classification_tv = (TextView) view.findViewById(R.id.classification_tv);
                Glide.with(context).load(myList.get(i).getImg()).into(classification_iv);
                classification_tv.setText(myList.get(i).getCname());
                return view;
            }
        });

    }
}
