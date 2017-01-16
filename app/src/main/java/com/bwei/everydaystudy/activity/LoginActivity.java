package com.bwei.everydaystudy.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.bwei.everydaystudy.bean.LoginMessageBean;
import com.bwei.everydaystudy.utils.CommonUtils;
import com.bwei.everydaystudy.utils.LogUtils;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView sign_iv_back;
    private TextView my_sign_forget_pwd;
    private ImageView my_btn_qq_sign_up;
    private Button my_btn_sign_up;
    private ImageView my_btn_weibo_sign_up;
    private EditText my_et_sign_in_account;
    private EditText my_et_sign_in_pwd;
    private Button my_btn_sign_in;
    private TextView my_sign_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initView();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 初始化空间
     */
    private void initView() {
        //账号密码输入框
        my_et_sign_in_account = (EditText) findViewById(R.id.my_et_sign_in_account);
        my_et_sign_in_pwd = (EditText) findViewById(R.id.my_et_sign_in_pwd);
        //提示信息
        my_sign_toast = (TextView) findViewById(R.id.my_sign_toast);

        sign_iv_back = (ImageView) findViewById(R.id.sign_iv_back);
        sign_iv_back.setOnClickListener(this);
        my_sign_forget_pwd = (TextView) findViewById(R.id.my_sign_forget_pwd);
        my_sign_forget_pwd.setOnClickListener(this);
        //qq
        my_btn_qq_sign_up = (ImageView) findViewById(R.id.my_btn_qq_sign_up);
        my_btn_qq_sign_up.setOnClickListener(this);
        //微博
        my_btn_weibo_sign_up = (ImageView) findViewById(R.id.my_btn_weibo_sign_up);
        my_btn_weibo_sign_up.setOnClickListener(this);
        //立即注册
        my_btn_sign_up = (Button) findViewById(R.id.my_btn_sign_up);
        my_btn_sign_up.setOnClickListener(this);
        //登录
        my_btn_sign_in = (Button) findViewById(R.id.my_btn_sign_in);
        my_btn_sign_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_iv_back:
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
                break;
            case R.id.my_sign_forget_pwd://忘记密码
                Intent intent = new Intent(LoginActivity.this, AlterActivity.class);
                startActivity(intent);
                break;
            case R.id.my_btn_sign_up://立即注册
                Intent intent1 = new Intent(LoginActivity.this, EnrollActivity.class);
                startActivity(intent1);
                break;
            case R.id.my_btn_qq_sign_up://qq
                UMShareAPI mShareAPI = UMShareAPI.get(LoginActivity.this);
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.my_btn_weibo_sign_up:

                break;
            //登录
            case R.id.my_btn_sign_in:
                if (TextUtils.isEmpty(getUserNumber())){
                    toastMesage("账号不能为空");
                }else if (TextUtils.isEmpty(getUserPwd())){
                    toastMesage("密码不能为空");
                }else {
                    //登录
                    loginToServic();
                }
                break;
        }
    }

    /**
     * 登录服务器
     */
    private void loginToServic() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("userName",getUserNumber());
        hashMap.put("password",getUserPwd());
        hashMap.put("dosubmit","1");
        new BaseData() {
            @Override
            public void setResultData(String data) {
                Gson gson = new Gson();
                LoginMessageBean loginMessageBean = gson.fromJson(data, LoginMessageBean.class);
                LogUtils.i("loginMessageBean",loginMessageBean.toString());
                if (loginMessageBean.getStatus() == 200){
                    toastMesage("登录成功！");
                    finish();
                }else if (loginMessageBean.getStatus() == 201){
                    my_sign_toast.setVisibility(View.VISIBLE);
                    my_sign_toast.setText("密码不正确");
                }else if (loginMessageBean.getStatus() == 202){
                    my_sign_toast.setVisibility(View.VISIBLE);
                    my_sign_toast.setText("账号不存在");
                }
            }

            @Override
            public void setResulttError(int state) {

            }
        }.postData(false,true,"http://www.meirixue.com/","api.php?c=login&a=index",hashMap,BaseData.NOTIME);
    }

    /**
     * QQ登录监听
     */
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> data) {
            String screen_name = data.get("screen_name");
            String uid = data.get("uid");
            String profile_image_url = data.get("profile_image_url");

            Toast.makeText(getApplicationContext(), "screen_name" + screen_name + " uid" + uid + " profile_image_url" + profile_image_url, Toast.LENGTH_SHORT).show();
            Intent xinxi = new Intent();
            xinxi.putExtra("screen_name", screen_name);
            xinxi.putExtra("profile_image_url", profile_image_url);
            setResult(111, xinxi);
            finish();
        }
        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 获取账号
     * @return
     */
    public String getUserNumber(){
        return my_et_sign_in_account.getText().toString().trim();
    }

    /**
     * 获取用户密码
     * @return
     */
    public String getUserPwd(){
        return my_et_sign_in_pwd.getText().toString().trim();
    }
    /**
     * 吐司
     */
    public void toastMesage(String toastString){
        Toast.makeText(LoginActivity.this, toastString, Toast.LENGTH_SHORT).show();
    }
}
