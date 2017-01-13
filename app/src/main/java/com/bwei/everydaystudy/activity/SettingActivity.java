package com.bwei.everydaystudy.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.utils.CleanManager;
import com.bwei.everydaystudy.view.SelfDialog;

import java.io.File;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView setting_back;
    private RelativeLayout setting_clear;
    private TextView setting_textview_cachesize;
    private SelfDialog selfDialog;
    private RelativeLayout setting_update;
    private RelativeLayout setting_about;
    private boolean isLatest = false;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setting_back = (ImageView) findViewById(R.id.setting_back);
        setting_back.setOnClickListener(this);
        //缓存
        setting_clear = (RelativeLayout) findViewById(R.id.setting_clear);
        setting_clear.setOnClickListener(this);
        setting_textview_cachesize = (TextView) findViewById(R.id.setting_textview_cachesize);
        //检查更新
        setting_update = (RelativeLayout) findViewById(R.id.setting_update);
        setting_update.setOnClickListener(this);
        //关于我们
        setting_about = (RelativeLayout) findViewById(R.id.setting_about);
        setting_about.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.setting_back:
                finish();
                overridePendingTransition(R.anim.leftin,R.anim.leftout);
                break;
            case R.id.setting_clear://清楚缓存
                 initsetting_clear();
                break;
            case R.id.setting_update://检查更新
                //判断服务器上版本信息
                if (isLatest) {          //已经是最新版本
                    Toast.makeText(SettingActivity.this, "您已经是最新版本！", Toast.LENGTH_SHORT).show();
                } else {                 //不是最新版本
                    showWindow();
                }
                break;
            case R.id.setting_about://关于我们
                Intent intent=new Intent(SettingActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
        }
     }
    /**
     * 清楚緩存
     */
    private void initsetting_clear() {
        final File cacheDir = this.getCacheDir();
        String cacheSize = null;
        try {
            cacheSize = CleanManager.getCacheSize(cacheDir);
            setting_textview_cachesize.setText("" + cacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        //    设置Title的内容
        builder.setTitle("提示");
        //    设置Content来显示一个信息
        builder.setMessage("是否要清理缓存？");
        //    设置一个NegativeButton
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String cachesize= CleanManager.getCacheSize(cacheDir);
                    Toast.makeText(SettingActivity.this, "成功清除緩存"+cachesize, Toast.LENGTH_SHORT).show();
                    setting_textview_cachesize.setText("0Mb");
                } catch (Exception e) {
                }
            }
        });
        //    设置一个PositiveButton
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * 更新弹出弹出框
     */
    private void showWindow() {
        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);

        //    设置Title的内容
        builder.setTitle("软件更新");
        //    设置Content来显示一个信息
        builder.setMessage("是否更新？");
        //    设置一个NegativeButton
        builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        //    设置一个PositiveButton
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downNewVersion();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
    private void downNewVersion() {

    }
}