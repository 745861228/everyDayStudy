package com.bwei.everydaystudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.base.BaseData;
import com.bwei.everydaystudy.bean.RegisterBean;
import com.bwei.everydaystudy.bean.SendCodeBean;
import com.bwei.everydaystudy.utils.UrlUtils;
import com.google.gson.Gson;

import java.util.HashMap;



public class EnrollActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView enroll_image;
    private TextView my_sign_up_get_captcha;
    private Button my_sign_up_register;
    private EditText my_sign_up_phone_num;
    private EditText my_sign_up_enter_captcha;
    private EditText my_sign_up_pwd;
    private TextView my_sigup_toast;
    private int time = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (msg.what == 0) {
                my_sign_up_get_captcha.setText(time + "");
                if (time <= 0) {
                    my_sign_up_get_captcha.setText("获取验证码");
                    handler.removeCallbacksAndMessages(null);
                }
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };


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
        //手机账号，验证码，密码输入框
        my_sign_up_phone_num = (EditText) findViewById(R.id.my_sign_up_phone_num);
        my_sign_up_enter_captcha = (EditText) findViewById(R.id.my_sign_up_enter_captcha);
        my_sign_up_pwd = (EditText) findViewById(R.id.my_sign_up_pwd);
        //提示信息
        my_sigup_toast = (TextView) findViewById(R.id.my_sigup_toast);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enroll_image:
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
                break;
            case R.id.my_sign_up_get_captcha://发送验证码
                if (TextUtils.isEmpty(getEditTextString(my_sign_up_phone_num))) {
                    my_sigup_toast.setVisibility(View.VISIBLE);
                    my_sigup_toast.setText("手机号不能为空");
                } else if (getEditTextString(my_sign_up_phone_num).length() != 11) {
                    my_sigup_toast.setVisibility(View.VISIBLE);
                    my_sigup_toast.setText("手机格式错误");
                } else {
                    sendCode();
                }
                break;
            case R.id.my_sign_up_register://提交
                commitMessage();
                break;
        }
    }

    /**
     * 提交用户 信息
     */
    private void commitMessage() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("code", getEditTextString(my_sign_up_enter_captcha));
        hashMap.put("account", getEditTextString(my_sign_up_phone_num));
        hashMap.put("password", getEditTextString(my_sign_up_pwd));
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                RegisterBean registerBean = gson.fromJson(data, RegisterBean.class);
                if (registerBean.status == 200) {
                    toastMesage("注册成功！");
                } else {
                    toastMesage("验证码错误");
                }
            }

            @Override
            public void setResulttError(int state) {

            }
        }.postData(false, false, UrlUtils.baseUrl, "api.php?c=register&a=verify", hashMap, BaseData.NOTIME);
    }

    /**
     * 发送短信验证码
     */
    private void sendCode() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("dosubmit", "1");
        hashMap.put("account", getEditTextString(my_sign_up_phone_num));
        hashMap.put("type", "1");
        hashMap.put("from", "hiapk");
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                SendCodeBean sendCodeBean = gson.fromJson(data, SendCodeBean.class);
                if (sendCodeBean.status == 200) {
                    toastMesage("获取验证码成功！");
                    handler.sendEmptyMessageDelayed(0, 1000);
                } else if (sendCodeBean.status == 506) {
                    my_sigup_toast.setVisibility(View.VISIBLE);
                    my_sigup_toast.setText("用户已经存在");
                }
            }

            @Override
            public void setResulttError(int state) {

            }
        }.postData(false, false, UrlUtils.baseUrl, "api.php?c=register&a=doregister", hashMap, BaseData.NOTIME);
    }


    /**
     * 获取账号
     *
     * @return
     */
    public String getEditTextString(EditText view) {
        return view.getText().toString().trim();
    }

    /**
     * 吐司
     */
    public void toastMesage(String toastString) {
        Toast.makeText(EnrollActivity.this, toastString, Toast.LENGTH_SHORT).show();
    }
}
