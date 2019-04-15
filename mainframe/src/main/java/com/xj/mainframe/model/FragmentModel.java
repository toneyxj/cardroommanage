package com.xj.mainframe.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class FragmentModel<T> {
    /**fragment 显示的标题*/
    private String tilte="";
    private Fragment fragment;
    private Bundle bundle;

    public String getTilte() {
        return tilte;
    }

    public FragmentModel setTilte(String tilte) {
        this.tilte = tilte;
        return this;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public FragmentModel setFragment(Class<T> fragment) {
        try {
            this.fragment = (Fragment) fragment.newInstance();
            addArgs();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FragmentModel setBundle(Bundle bundle){
        this.bundle=bundle;
        addArgs();
        return this;
    }

    private void addArgs(){
        if (fragment!=null&&bundle!=null){
            fragment.setArguments(bundle);
        }
    }

    public static FragmentModel Buidler(){
        return new FragmentModel();
    }
}
