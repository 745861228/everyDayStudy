package com.bwei.everydaystudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.everydaystudy.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView sign_iv_back;
    private TextView my_sign_forget_pwd;
    private ImageView my_btn_qq_sign_up;
    private Button my_btn_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initView();

    }
    /**
     * 初始化空间
     */
    private void initView() {
        sign_iv_back = (ImageView) findViewById(R.id.sign_iv_back);
        sign_iv_back.setOnClickListener(this);
        my_sign_forget_pwd = (TextView) findViewById(R.id.my_sign_forget_pwd);
        my_sign_forget_pwd.setOnClickListener(this);
        //qq
        my_btn_qq_sign_up = (ImageView) findViewById(R.id.my_btn_qq_sign_up);
        my_btn_qq_sign_up.setOnClickListener(this);
        //立即注册
        my_btn_sign_up = (Button) findViewById(R.id.my_btn_sign_up);
        my_btn_sign_up.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
           switch (v.getId())
           {
               case  R.id.sign_iv_back:
                   finish();
                   overridePendingTransition(R.anim.leftin,R.anim.leftout);
                   break;
               case  R.id.my_sign_forget_pwd://忘记密码
                   Intent intent=new Intent(LoginActivity.this,AlterActivity.class);
                   startActivity(intent);
                   break;
               case R.id.my_btn_sign_up://立即注册
                   Intent intent1=new Intent(LoginActivity.this,EnrollActivity.class);
                   startActivity(intent1);
                   break;
               case R.id.my_btn_qq_sign_up://qq
                   break;
           }
    }

}
