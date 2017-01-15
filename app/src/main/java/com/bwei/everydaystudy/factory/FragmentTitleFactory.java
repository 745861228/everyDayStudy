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

import static android.R.attr.fragment;

/**
 * Created by LiKe on 2016/11/28.
 */
public class FragmentTitleFactory {
    //创建集合
    private static HashMap<String, Fragment> fragmentHashMap = new HashMap<>();


    //创建静态方法
    public static Fragment getFragment(String title) {

        Fragment fragment = fragmentHashMap.get(title);
        if (fragment != null) {
            return fragment;
        }
        fragment = HotTitlesFragment.getTitleFragment(title);

        fragmentHashMap.put(title, fragment);
        return fragment;
    }
}
