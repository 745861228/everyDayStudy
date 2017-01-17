package com.bwei.everydaystudy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.bwei.everydaystudy.R;

import java.util.List;

/**
 * Created by Administrator on 2017/1/15 0015.
 */
public class CategoryGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    public CategoryGridAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.category_grid_item,null);
        RadioButton rb=(RadioButton)view.findViewById(R.id.rb);
        rb.setText(list.get(position));
        return view;
    }
}
