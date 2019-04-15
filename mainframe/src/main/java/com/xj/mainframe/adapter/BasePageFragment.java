package com.xj.mainframe.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xj.mainframe.model.FragmentModel;

import java.util.List;

public abstract class BasePageFragment extends FragmentPagerAdapter {
    private FragmentModel[] models = null;

    public BasePageFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int positon) {
        if (getListFragment() != null)
            return getListFragment()[positon].getFragment();
        return null;
    }

    @Override
    public int getCount() {
        if (getListFragment() == null) return 0;
        return getListFragment().length;
    }

    public FragmentModel[] getListFragment() {
        return models;
    }

    public void setFragmentModels(FragmentModel[] models) {
        this.models = models;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (getListFragment() == null) return "";
        return getListFragment()[position].getTilte();
    }
}
