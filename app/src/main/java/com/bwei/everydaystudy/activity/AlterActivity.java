package com.bwei.everydaystudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.everydaystudy.R;


public class AlterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView alter_image;
    private TextView my_forget_pwd_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);
        //初始化控件
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        alter_image = (ImageView) findViewById(R.id.alter_image);
        alter_image.setOnClickListener(this);
        //邮箱
        my_forget_pwd_mail = (TextView) findViewById(R.id.my_forget_pwd_mail);
        my_forget_pwd_mail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
           switch (v.getId())
           {
               case R.id.alter_image:
                   finish();
                   overridePendingTransition(R.anim.leftin,R.anim.leftout);
                   break;
               case R.id.my_forget_pwd_mail:
                   Intent intent=new Intent(AlterActivity.this,Mail_find_pwdActivity.class);
                   startActivity(intent);
                   break;

           }
    }
}
