package com.bwei.everydaystudy.factory;

import android.support.v4.app.Fragment;

import com.bwei.everydaystudy.fragment.CategoryFragment;
import com.bwei.everydaystudy.fragment.CircleAttentionFragment;
import com.bwei.everydaystudy.fragment.CircleFragment;
import com.bwei.everydaystudy.fragment.CircleHotFragment;
import com.bwei.everydaystudy.fragment.CircleTopicFragment;
import com.bwei.everydaystudy.fragment.HomeFragment;
import com.bwei.everydaystudy.fragment.HotTitlesFragment;
import com.bwei.everydaystudy.fragment.MineFragment;

import java.util.HashMap;

/**
 * Created by LiKe on 2016/11/28.
 */
public class FragmentFactory {
    //创建集合
    private static HashMap<String, Fragment> fragmentHashMap = new HashMap<>();

    //创建静态方法
    public static Fragment getFragment(String title) {
        Fragment fragment = fragmentHashMap.get(title);
        if (fragment != null) {
            return fragment;
        }

        switch (title) {
            case "首页":
                fragment = new HomeFragment();
                break;

            case "课程分类":
                fragment = new CategoryFragment();
                break;

            case "圈子":
                fragment = new CircleFragment();
                break;

            case "我的":
                fragment = new MineFragment();
                break;

            case "话题":
                fragment = new CircleTopicFragment();
                break;

            case "热门":
                fragment = new CircleHotFragment();
                break;

            case "关注":
                fragment = new CircleAttentionFragment();
                break;
        }

    //    fragment = HotTitlesFragment.getTitleFragment(title);

        fragmentHashMap.put(title, fragment);
        return fragment;
    }
}
