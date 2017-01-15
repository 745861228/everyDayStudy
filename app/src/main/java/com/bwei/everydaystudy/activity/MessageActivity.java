package com.bwei.everydaystudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bwei.everydaystudy.R;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
    }
    /**
     * 初始化控件
     */
    private void initView() {
        message = (ImageView) findViewById(R.id.message);
        message.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
           switch (v.getId())
           {
               case R.id.message:
                   finish();
                   overridePendingTransition(R.anim.leftin,R.anim.leftout);
                   break;
           }
    }
}
