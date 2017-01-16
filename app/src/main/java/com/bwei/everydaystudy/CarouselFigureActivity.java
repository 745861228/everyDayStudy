package com.bwei.everydaystudy;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.base.BaseShowingActivity;
import com.bwei.everydaystudy.bean.CarouselFigureBean;
import com.bwei.everydaystudy.utils.CommonUtils;
import com.bwei.everydaystudy.utils.LogUtils;
import com.bwei.everydaystudy.view.ShowingPager;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.HashMap;
import java.util.List;

public class CarouselFigureActivity extends BaseShowingActivity {


    private ImageView carousel_iv;
    private ListView carousel_lv;
    private TextView carousel_tv_content;
    private TextView carousel_tv_title;
    private SpringView carousel_springview;

    @Override
    public View setBaseSuccessView() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_carousel_figure, null);
        carousel_iv = (ImageView) view.findViewById(R.id.carousel_iv);
        carousel_lv = (ListView) view.findViewById(R.id.carousel_lv);
        carousel_tv_content = (TextView) view.findViewById(R.id.carousel_tv_content);
        carousel_tv_title = (TextView) view.findViewById(R.id.carousel_tv_title);
        carousel_springview = (SpringView) view.findViewById(R.id.carousel_springview);
//        carousel_springview.setHeader(new MeituanHeader(this));
//        carousel_springview.setFooter(new AliFooter(this));
//        carousel_springview.setType(SpringView.Type.FOLLOW);
        return view;
    }

    @Override
    public void setBaseTitleView(View titleView) {
        ImageView title_home = (ImageView) titleView.findViewById(R.id.title_home);
        ImageView title_search = (ImageView) titleView.findViewById(R.id.title_search);
        ImageView title_back = (ImageView) titleView.findViewById(R.id.title_back);
        title_home.setVisibility(View.VISIBLE);
        title_search.setVisibility(View.VISIBLE);
        title_back.setVisibility(View.VISIBLE);
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onLoad() {
        String cid = getIntent().getStringExtra("cid");
        HashMap<String, String> map = new HashMap<>();
        map.put("aid", cid);
        new BaseData() {
            @Override
            public void setResultData(String data) {
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                CarouselFigureBean bean = gson.fromJson(data, CarouselFigureBean.class);
                initData(bean);
            }

            @Override
            public void setResulttError(int state) {
                showingPager.setCurrentState(ShowingPager.StateType.STATE_LOAD_ERROR);
            }
        }.postData(false, false, "http://www.meirixue.com/", "api.php?c=activity&a=getActivityBak", map, BaseData.NORMALTIME);

    }

    private void initData(final CarouselFigureBean bean) {
        Glide.with(CarouselFigureActivity.this).load(bean.getToppic()).into(carousel_iv);
        carousel_tv_content.setText(bean.getDesc());
        carousel_tv_title.setText(bean.getDataList().get(0).getTitle());
        final List<CarouselFigureBean.DataListEntity.ListEntity> listview = bean.getDataList().get(0).getList();
        carousel_lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return bean.getDataList().get(0).getList().size();
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
                    view = LayoutInflater.from(CarouselFigureActivity.this).inflate(R.layout.recommend_lv_item,null);
                }
                ImageView recommend_iv = (ImageView) view.findViewById(R.id.recommend_iv);
                Glide.with(CarouselFigureActivity.this).load(listview.get(i).getCourse_pic()).into(recommend_iv);
                TextView recommend_tv_titleName = (TextView) view.findViewById(R.id.recommend_tv_titleName);
                TextView recommend_tv_schollname = (TextView) view.findViewById(R.id.recommend_tv_schollname);
                TextView recommend_tv_price = (TextView) view.findViewById(R.id.recommend_tv_price);
                TextView recommend_tv_usercount = (TextView) view.findViewById(R.id.recommend_tv_usercount);
                recommend_tv_titleName.setText(listview.get(i).getCourse_name());
                recommend_tv_schollname.setText(listview.get(i).getSchool_name());
                recommend_tv_usercount.setText(listview.get(i).getCourse_paycount()+"人在学");
                if(!listview.get(i).getCourse_price().equals("0.00")){
                    recommend_tv_price.setText("￥ "+listview.get(i).getCourse_price());
                    recommend_tv_price.setTextColor(Color.RED);
                }
                return view;
            }
        });
    }
}
