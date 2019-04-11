package com.xj.cardroommanage.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xj.cardroommanage.R;
import com.xj.cardroommanage.base.CRACtivity;
import com.xj.cardroommanage.base.CRTitleActivity;
import com.xj.mainframe.view.otherView.TitleView;

public class MainActivity extends CRTitleActivity {
    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void onclickView(View view) {

    }

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    protected TitleView.TitleModel getTitleMode() {
        return new TitleView.TitleModel().setTitle("首页")
                .setTxtRight("保存")
                .setTitleClickListener(this);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }
}
