package com.bwei.everydaystudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bwei.everydaystudy.R;

public class EnrollActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView enroll_image;

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
    }
    @Override
    public void onClick(View v) {
         switch (v.getId())
         {
             case  R.id.enroll_image:
                 finish();
                 overridePendingTransition(R.anim.leftin,R.anim.leftout);
                 break;
         }
    }
}
