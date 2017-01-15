package com.bwei.everydaystudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.everydaystudy.R;

public class EnrollActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView enroll_image;
    private TextView my_sign_up_get_captcha;
    private Button my_sign_up_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        enroll_image = (ImageView) findViewById(R.id.enroll_image);
        enroll_image.setOnClickListener(this);
        //发送验证码
        my_sign_up_get_captcha = (TextView) findViewById(R.id.my_sign_up_get_captcha);
        my_sign_up_get_captcha.setOnClickListener(this);
        //提交
        my_sign_up_register = (Button) findViewById(R.id.my_sign_up_register);
        my_sign_up_register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
         switch (v.getId())
         {
             case  R.id.enroll_image:
                 finish();
                 overridePendingTransition(R.anim.leftin,R.anim.leftout);
                 break;
             case R.id.my_sign_up_get_captcha://发送验证码

                 break;
             case R.id.my_sign_up_register://提交

                 break;


         }
    }
}
