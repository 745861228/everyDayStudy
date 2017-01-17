package com.bwei.everydaystudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.everydaystudy.R;


public class TicklingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView my_feedback_item;
    private TextView my_feedback_send;
    private EditText my_feedback_edittext_content;
    private EditText my_feedback_edittext_phone;
    private Button my_feedback_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickling);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        my_feedback_item = (ImageView) findViewById(R.id.my_feedback_item);
        my_feedback_item.setOnClickListener(this);
        //发送
        my_feedback_send = (TextView) findViewById(R.id.my_feedback_send);
        my_feedback_send.setOnClickListener(this);
        //输入框
        my_feedback_edittext_content = (EditText) findViewById(R.id.my_feedback_edittext_content);
        my_feedback_edittext_content.setOnClickListener(this);
        my_feedback_edittext_phone = (EditText) findViewById(R.id.my_feedback_edittext_phone);
        my_feedback_edittext_phone.setOnClickListener(this);
        //提交
        my_feedback_bt = (Button) findViewById(R.id.my_feedback_bt);
        my_feedback_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
           switch (v.getId())
           {
               case R.id.my_feedback_item:
                   finish();
                   overridePendingTransition(R.anim.leftin,R.anim.leftout);
                   break;
               case R.id.my_feedback_bt://提交
                   break;
               case R.id.my_feedback_edittext_content://输入框
                   break;
               case R.id.my_feedback_edittext_phone:
                   break;
               case R.id.my_feedback_send://发送
                   break;
           }
    }
}
