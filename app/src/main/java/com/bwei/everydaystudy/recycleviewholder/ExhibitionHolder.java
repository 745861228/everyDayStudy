package com.bwei.everydaystudy.recycleviewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseHolder;
import com.bwei.everydaystudy.bean.HomeBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwe on 2017/1/12.
 */

public class ExhibitionHolder extends BaseHolder<List<HomeBean.DataEntity.AdlistEntity>> {

    private final ImageView exhibition_iv_one;
    private final ImageView exhibition_iv_two;
    private final ImageView exhibition_iv_three;
    private List<HomeBean.DataEntity.AdlistEntity> myList = new ArrayList<>();

    public ExhibitionHolder(View itemView) {
        super(itemView);
        exhibition_iv_one = (ImageView) itemView.findViewById(R.id.exhibition_iv_one);
        exhibition_iv_two = (ImageView) itemView.findViewById(R.id.exhibition_iv_two);
        exhibition_iv_three = (ImageView) itemView.findViewById(R.id.exhibition_iv_three);
    }

    @Override
    public void setData(Context context, List<HomeBean.DataEntity.AdlistEntity> list) {
        myList.clear();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                myList.add(list.get(i));
            }
        }
        Glide.with(context).load(myList.get(0).getImg()).into(exhibition_iv_one);
        Glide.with(context).load(myList.get(1).getImg()).into(exhibition_iv_two);
        Glide.with(context).load(myList.get(2).getImg()).into(exhibition_iv_three);
    }


}
