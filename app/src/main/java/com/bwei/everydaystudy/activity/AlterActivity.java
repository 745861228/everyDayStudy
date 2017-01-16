package com.bwei.everydaystudy.activity;

import android.content.Intent;
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


public class AlterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView alter_image;
    private TextView my_forget_pwd_mail;
    private EditText my_forget_pwd_phone_num;
    private EditText my_forget_pwd_enter_captcha;
    private EditText my_forget_pwd;
    private TextView my_forget_pwd_get_captcha;
    private Button my_forget_pwd_submit;
    private TextView my_forget_pwd_toast;
    private int time = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (msg.what == 0) {
                my_forget_pwd_get_captcha.setText(time + "");
                if (time <= 0) {
                    my_forget_pwd_get_captcha.setText("获取验证码");
                    handler.removeCallbacksAndMessages(null);
                }
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

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
        //找回密码
        my_forget_pwd_phone_num = (EditText) findViewById(R.id.my_forget_pwd_phone_num);
        my_forget_pwd_enter_captcha = (EditText) findViewById(R.id.my_forget_pwd_enter_captcha);
        //设置密码
        my_forget_pwd = (EditText) findViewById(R.id.my_forget_pwd);
        //发送验证码
        my_forget_pwd_get_captcha = (TextView) findViewById(R.id.my_forget_pwd_get_captcha);
        my_forget_pwd_enter_captcha.setOnClickListener(this);
        //提交
        my_forget_pwd_submit = (Button) findViewById(R.id.my_forget_pwd_submit);
        my_forget_pwd_submit.setOnClickListener(this);
        //狀態碼
        my_forget_pwd_toast = (TextView) findViewById(R.id.my_forget_pwd_toast);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alter_image:
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
                break;
            case R.id.my_forget_pwd_mail:/*邮箱注册 ？点击找回*/
                Intent intent = new Intent(AlterActivity.this, Mail_find_pwdActivity.class);
                startActivity(intent);
                break;
            case R.id.my_forget_pwd_get_captcha://发送验证码
                if (TextUtils.isEmpty(getEditTextString(my_forget_pwd_phone_num))) {
                    my_forget_pwd_toast.setVisibility(View.VISIBLE);
                    my_forget_pwd_toast.setText("手機號碼錯誤");
                } else {
                    sendCode();
                }
                break;
            case R.id.my_forget_pwd_submit://提交
                commitMessage();
                break;
        }
    }

    /**
     * 提交用戶 信息
     */
    private void commitMessage() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("code", getEditTextString(my_forget_pwd_enter_captcha));
        hashMap.put("account", getEditTextString(my_forget_pwd_phone_num));
        hashMap.put("password", getEditTextString(my_forget_pwd));
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                RegisterBean registerBean = gson.fromJson(data, RegisterBean.class);
                if (registerBean.status == 200) {
                    toastMesage("成功");
                } else {
                    toastMesage("驗證碼錯誤");
                }
            }

            @Override
            public void setResulttError(int state) {

            }
        }.postData(false, false, UrlUtils.baseUrl, "api.php?c=register&a=verify", hashMap, BaseData.NOTIME);

    }

    /**
     * 發送短信驗證碼
     */
    private void sendCode() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("dosubmit", "1");
        hashMap.put("account", getEditTextString(my_forget_pwd_phone_num));
        hashMap.put("type", "1");
        hashMap.put("from", "hiapk");
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                SendCodeBean sendCodeBean = gson.fromJson(data, SendCodeBean.class);
                if (sendCodeBean.status == 200) {
                    toastMesage("獲取驗證成功");
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }

            @Override
            public void setResulttError(int state) {

            }
        }.postData(false, false, UrlUtils.baseUrl, "api.php?c=register&a=doregister", hashMap, BaseData.NOTIME);
    }

    /**
     * 获取账号
     */
    public String getEditTextString(EditText view) {
        return view.getText().toString().trim();
    }

    /**
     * 吐司
     */
    public void toastMesage(String toastString) {
        Toast.makeText(AlterActivity.this, toastString, Toast.LENGTH_SHORT).show();
    }

}
