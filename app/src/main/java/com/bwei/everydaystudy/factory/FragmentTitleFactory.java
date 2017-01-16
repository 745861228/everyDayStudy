package com.bwei.everydaystudy.factory;

import android.support.v4.app.Fragment;

import com.bwei.everydaystudy.bean.HotTitlesBean;
import com.bwei.everydaystudy.fragment.HotTitlesFragment;

import java.util.HashMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by LiKe on 2016/11/28.
 */
public class FragmentTitleFactory {
    //创建集合
    private static HashMap<HotTitlesBean.DataBean, Fragment> fragmentHashMap = new HashMap<>();


    //创建静态方法
    public static Fragment getFragment(HotTitlesBean.DataBean dataBean) {

        Fragment fragment = fragmentHashMap.get(dataBean);
        if (fragment != null) {
            return fragment;
        }
        if (HotTitlesFragment.getTitleFragment(dataBean) == null) {
            fragment = new HotTitlesFragment();
        } else {
            fragment = HotTitlesFragment.getTitleFragment(dataBean);
        }

        fragmentHashMap.put(dataBean, fragment);
        return fragment;
    }
}
