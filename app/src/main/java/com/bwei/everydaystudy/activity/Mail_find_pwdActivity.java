package com.bwei.everydaystudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwei.everydaystudy.R;


public class Mail_find_pwdActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView my_mail_iv_back;
    private EditText my_mail_et;
    private Button my_mail_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_find_pwd);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        my_mail_iv_back = (ImageView) findViewById(R.id.my_mail_iv_back);
        my_mail_iv_back.setOnClickListener(this);
        //请输入注册邮箱
        my_mail_et = (EditText) findViewById(R.id.my_mail_et);
        //提交
        my_mail_submit = (Button) findViewById(R.id.my_mail_submit);
        my_mail_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId())
          {
              case R.id.my_mail_iv_back:
                  finish();
                  overridePendingTransition(R.anim.leftin,R.anim.leftout);
                  break;
              case R.id.my_mail_submit:/*提交*/
                  break;

          }
    }
}
