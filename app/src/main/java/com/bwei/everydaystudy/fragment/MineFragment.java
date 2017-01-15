package com.bwei.everydaystudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.activity.CourseActivity;
import com.bwei.everydaystudy.activity.LoginActivity;
import com.bwei.everydaystudy.activity.MessageActivity;
import com.bwei.everydaystudy.activity.SettingActivity;
import com.bwei.everydaystudy.activity.TicklingActivity;
import com.bwei.everydaystudy.utils.CommonUtils;

/**
 * author by LiKe on 2017/1/10.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private ImageView my_image;
    private Button my_top_log;
    private TextView account;
    private TextView my_unlog_tv;
    private RelativeLayout my_myclass;
    private RelativeLayout my_collect;
    private RelativeLayout my_youhui;
    private RelativeLayout my_msg;
    private RelativeLayout my_feedback;
    private RelativeLayout my_setting;
    private  RelativeLayout my_log_ll_show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.mine_layout,null);
        my_image = (ImageView) view.findViewById(R.id.my_image); //头像
        my_image.setOnClickListener(this);
        my_top_log = (Button) view.findViewById(R.id.My_top_log); //立即登录
        my_top_log.setOnClickListener(this);
        account = (TextView) view.findViewById(R.id.account);    //登陆后可云同步课程
        account.setOnClickListener(this);
        my_myclass = (RelativeLayout) view.findViewById(R.id.my_myclass); //我的课程
        my_myclass.setOnClickListener(this);
        my_collect = (RelativeLayout) view.findViewById(R.id.my_collect);   //收藏
        my_collect.setOnClickListener(this);
        my_youhui = (RelativeLayout) view.findViewById(R.id.my_youhui); //优惠券
        my_youhui.setOnClickListener(this);
        my_msg = (RelativeLayout) view.findViewById(R.id.my_msg); //我的消息
        my_msg.setOnClickListener(this);
        my_feedback = (RelativeLayout) view.findViewById(R.id.my_feedback);//意见反馈
        my_feedback.setOnClickListener(this);
        my_setting = (RelativeLayout) view.findViewById(R.id.my_setting);//设置
        my_setting.setOnClickListener(this);
        my_log_ll_show = (RelativeLayout) view.findViewById(R.id.my_log_ll_show);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123 && resultCode == 111) {
            String screen_name = data.getStringExtra("screen_name");
            String profile_image_url = data.getStringExtra("profile_image_url");
            QQLogin(screen_name, profile_image_url);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void QQLogin(String screen_name, String profile_image_url) {
        if (!TextUtils.isEmpty(profile_image_url)) {
            my_log_ll_show.setVisibility(View.VISIBLE);
            CommonUtils.saveSp("profile_image_url", profile_image_url);
            CommonUtils.saveSp("screen_name", screen_name);
        } else {
            my_log_ll_show.setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.my_image:
                break;
            case  R.id.My_top_log://立即登录
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.my_myclass:  /*我的课程*/
                Intent intent1=new Intent(getActivity(), CourseActivity.class);
                startActivity(intent1);
                break;
            case R.id.my_msg:/*我的消息*/
                Intent intent4=new Intent(getActivity(), MessageActivity.class);
                startActivity(intent4);
                break;
            case R.id.my_feedback: /*意见反馈*/
                Intent intent2 = new Intent(getActivity(), TicklingActivity.class);
                startActivity(intent2);
                break;
            case R.id.my_setting:/*设置*/
                Intent intent3 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent3);
                break;

        }
    }
}
