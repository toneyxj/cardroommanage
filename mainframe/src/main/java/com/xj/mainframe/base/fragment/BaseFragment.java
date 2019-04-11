package com.xj.mainframe.base.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xj.mainframe.base.BaseUtils;
import com.xj.mainframe.configer.APPLog;
import com.xj.mainframe.listener.FragmentInterface;
import com.xj.mainframe.listener.HandlerMessageInterface;
import com.xj.mainframe.listener.XJOnClickListener;

/**
 * Created by xj on 2018/11/7.
 */

public abstract class BaseFragment extends Fragment implements FragmentInterface, HandlerMessageInterface {

    private BaseUtils.XJHander hander = new BaseUtils.XJHander(this);
    private View rootView;
    private boolean isFinish;
    private boolean isShow;
    private int i=0;

    @Override
    public View getRootView() {
        return rootView;
    }

    public boolean isFinish() {
        return isFinish;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getRootView() == null) return;
        this.isShow = isVisibleToUser;
        fragmentShowing(isShow);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        APPLog.e("onCreateView"+"i="+i++);
        rootView = inflater.inflate(getLayoutID(), container, false);
        initView(rootView);
        return rootView;
    }


    /**
     * 点击事件处理
     */
    public XJOnClickListener clickListener = new XJOnClickListener() {
        @Override
        public void onclickView(View view) {
            BaseFragment.this.onclickView(view);
        }
    };

    @Override
    public void onDestroyView() {
        hander.removeCallbacksAndMessages(null);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hander.removeCallbacksAndMessages(null);
        isFinish = true;
    }

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void onclickView(View view) {

    }

    @Override
    public void requestData() {

    }

    @Override
    public Handler getHandler() {
        return hander;
    }

    @Override
    public boolean isShowFragment() {
        return this.isShow;
    }
}
