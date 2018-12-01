package com.bof.devfest18.danaevent.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragments;
    private final List<String> mFragmentTitles;

    public TabAdapter(FragmentManager fm, List<Fragment> fragments, List<String> mFragmentTitles) {
        super(fm);
        this.mFragments = fragments;
        this.mFragmentTitles = mFragmentTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
