package com.example.chenhao.simpleapp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by chenhao on 17-3-22.
 * ViewPager的一个简单的适配器
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    /**
     * The M fragments.
     */
    List<Fragment> mFragments;

    /**
     * Instantiates a new View pager adapter.
     *
     * @param fm        the fm
     * @param fragments the fragments
     */
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
