package com.xj.mainframe.view.listener;

/**
 * 标题栏点击监听
 * Created by xj on 2018/10/26.
 */
public interface TitleInterface {
    /**
     * 返回按钮
     */
    void onClickBack();

    /**
     * 点击标题
     */
    void onClickTitle();

    /**
     * 点击右侧文字
     */
    void onClickRTxt();

    /**
     * 点击右侧图标
     */
    void onClickImg();
}
