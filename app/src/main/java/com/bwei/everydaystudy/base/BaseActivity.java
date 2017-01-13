package com.bwei.everydaystudy.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * author by LiKe on 2017/1/10.
 */

public class BaseActivity extends AutoLayoutActivity {
    private long mExitTime = 0;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    /**
     * 一键退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return true;
    }
}
