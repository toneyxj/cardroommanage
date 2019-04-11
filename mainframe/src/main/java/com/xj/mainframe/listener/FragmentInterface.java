package com.xj.mainframe.listener;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

/**
 * 基本fragment控制接口
 * Created by xj on 2018/11/7.
 */

public interface FragmentInterface {

    /**
     * 执行activity.oncreate,数据初始化
     * @param savedInstanceState
     */
    void initFragment(Bundle savedInstanceState);

    /**
     * fragment正在显示
     */
    void fragmentShowing(boolean is);
    /**
     *初始化view控件initActivity后执行
     */
    void initView(View view);
    /**
     * 获得布局的id
     * @return
     */
    int getLayoutID();
    /**
     * view点击
     * @param view
     */
    void onclickView(View view);
    /**
     * 主要数据请求
     */
    void requestData();

    /**
     * 获得fragment根view
     * @return
     */
    View getRootView();

    /**
     * 获得handler对象
     * @return
     */
    Handler getHandler();

    /**
     * 是否显示frament
     * @return
     */
    boolean isShowFragment();

}
