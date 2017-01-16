package com.bwei.everydaystudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bwei.everydaystudy.R;


public class TicklingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView my_feedback_item;

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
    }

    @Override
    public void onClick(View v) {
           switch (v.getId())
           {
               case R.id.my_feedback_item:
                   finish();
                   overridePendingTransition(R.anim.leftin,R.anim.leftout);
                   break;
           }
    }
}
