package com.bwei.everydaystudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bwei.everydaystudy.R;


public class CourseActivity extends AppCompatActivity implements View.OnClickListener {
    private  ImageView course_back;
    private RadioButton rb_mycourse_studying;
    private RadioButton rb_mycourse_studied;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initView();
    }

    private void initView() {
         course_back= (ImageView) findViewById(R.id.course_back);
         course_back.setOnClickListener(this);
        //RadioButton
        rb_mycourse_studying = (RadioButton) findViewById(R.id.rb_mycourse_studying);
        rb_mycourse_studied = (RadioButton) findViewById(R.id.rb_mycourse_studied);


    }
    @Override
    public void onClick(View v) {
          switch (v.getId())
          {
              case  R.id.course_back:
                  finish();
                  overridePendingTransition(R.anim.leftin,R.anim.leftout);
                  break;
          }
    }
}
