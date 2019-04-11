package com.xj.mainframe.base.activity;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.xj.mainframe.R;
import com.xj.mainframe.view.listener.TitleInterface;
import com.xj.mainframe.view.otherView.TitleView;

public abstract class BaseTitleActivity extends BaseActivity implements TitleInterface {

    private TitleView title_view;

    @Override
    public void initActivity(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_base_title;
    }

    @Override
    public void initView() {
        title_view = findViewById(R.id.title_view);
        title_view.setTitleModel(getTitleMode());
        View.inflate(this, getContentLayout(), (ViewGroup) findViewById(R.id.title_layout));
    }

    /**
     * 获得标题栏的设置参数
     *
     * @return
     */
    protected abstract TitleView.TitleModel getTitleMode();

    public void setTitleModel(TitleView.TitleModel model) {
        title_view.setTitleModel(getTitleMode());
    }

    /**
     * 填充的布局view
     *
     * @return
     */
    protected abstract int getContentLayout();

    @Override
    public void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onClickTitle() {
    }

    @Override
    public void onClickRTxt() {
    }

    @Override
    public void onClickImg() {
    }
}
