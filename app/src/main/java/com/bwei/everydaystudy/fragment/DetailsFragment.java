package com.bwei.everydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.bean.CourseDetailsBean;
import com.bwei.everydaystudy.bean.DetailsCatalogBean;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by qwe on 2017/1/16.
 */

public class DetailsFragment extends Fragment {

    private TextView tv_fragment;
    private AutoLinearLayout details_layout_details;
    private TextView details_tv_teacher;
    private TextView details_tv_scholl;
    private CourseDetailsBean bean;
    private TextView details_tv_highlights;
    private TextView details_tv_brief_introduction;
    private AutoLinearLayout details_layout_catalog;
    private ListView details_fragment_lv;
    private String cid;
    private TextView details_fragment_tv_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, null);
        // 详情
        details_layout_details = (AutoLinearLayout) view.findViewById(R.id.details_layout_details);
        details_tv_teacher = (TextView) view.findViewById(R.id.details_tv_teacher);
        details_tv_scholl = (TextView) view.findViewById(R.id.details_tv_scholl);
        details_tv_highlights = (TextView) view.findViewById(R.id.details_tv_Highlights);
        details_tv_brief_introduction = (TextView) view.findViewById(R.id.details_tv_brief_introduction);
        //目录
        details_layout_catalog = (AutoLinearLayout) view.findViewById(R.id.details_layout_catalog);
        details_fragment_lv = (ListView) view.findViewById(R.id.details_fragment_lv);
        details_fragment_tv_title = (TextView) view.findViewById(R.id.details_fragment_tv_title);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String type = getArguments().getString("type");
        bean = (CourseDetailsBean) getArguments().getSerializable("bean");
        cid = getArguments().getString("cid");
        initNetData();
        if(type.equals("详情")){
            details_layout_details.setVisibility(View.VISIBLE);
        } else if(type.equals("目录")){
            details_layout_catalog.setVisibility(View.VISIBLE);
        }
        details_tv_teacher.setText(bean.getData().getCourse_tname());
        details_tv_scholl.setText(bean.getData().getSchool_field());
        details_tv_highlights.setText(bean.getData().getCourse_tb_bright());
        details_tv_brief_introduction.setText(bean.getData().getCourse_tb_desc());


    }

    private void initNetData() {
        HashMap<String,String> map = new HashMap<>();
        map.put("courseid",cid);
        new BaseData(){

            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                DetailsCatalogBean detailsCatalogBean = gson.fromJson(data, DetailsCatalogBean.class);
                details_fragment_tv_title.setText(detailsCatalogBean.getData().get(0).getStep_name());
                final List<DetailsCatalogBean.DataEntity.NodesEntity> nodes = detailsCatalogBean.getData().get(0).getNodes();
                details_fragment_lv.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return nodes.size();
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
                            view = LayoutInflater.from(getActivity()).inflate(R.layout.details_fragment_lv_item,null);
                        }
                        TextView lv_tv_title = (TextView) view.findViewById(R.id.lv_tv_title);
                        TextView lv_tv_name = (TextView) view.findViewById(R.id.lv_tv_name);
                        TextView lv_tv_time = (TextView) view.findViewById(R.id.lv_tv_time);
                        lv_tv_title.setText(nodes.get(i).getSections_isfree()+"-"+nodes.get(i).getSections_sort());
                        lv_tv_name.setText(nodes.get(i).getSections_name());
                        return view;
                    }
                });
                details_fragment_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
            }
            @Override
            public void setResulttError(int state) {

            }
        }.postData(false,false,"http://www.meirixue.com/","api.php?c=course&a=getCourseStep",map,BaseData.NOTIME);
    }

    public static Fragment getFragment(String type, CourseDetailsBean bean,String cid) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putSerializable("bean", (Serializable) bean);
        bundle.putString("cid",cid);
        fragment.setArguments(bundle);
        return fragment;
    }
}
