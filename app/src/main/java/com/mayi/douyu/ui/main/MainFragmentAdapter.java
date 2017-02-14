package com.mayi.douyu.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.mayi.douyu.mvpbase.MVPBaseFragment;

import java.util.ArrayList;

/**
 * 作者 by yugai 时间 16/11/3.
 * ＊ 邮箱 784787081@qq.com
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<MVPBaseFragment> fragments = new ArrayList<>();
    private MVPBaseFragment currentFragment;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments.clear();
        fragments.add(com.mayi.douyu.ui.main.douyu.LiveFragment.newInstance());
        fragments.add(com.mayi.douyu.ui.main.panda.LiveFragment.newInstance());
        fragments.add(com.mayi.douyu.ui.main.huya.LiveFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((MVPBaseFragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public MVPBaseFragment getCurrentFragment() {
        return currentFragment;
    }
}
