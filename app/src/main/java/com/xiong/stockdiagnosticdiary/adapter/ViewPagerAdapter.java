package com.xiong.stockdiagnosticdiary.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;
    private String[] titles;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm, @NonNull Fragment[] fragments ) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFragments=fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments!=null?mFragments[position]:null;
    }

    @Override
    public int getCount() {
        return mFragments!=null?mFragments.length:0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles != null && titles.length > position ? titles[position] : null;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setmFragments(Fragment[] mFragments) {
        this.mFragments = mFragments;
        notifyDataSetChanged();
    }

}
