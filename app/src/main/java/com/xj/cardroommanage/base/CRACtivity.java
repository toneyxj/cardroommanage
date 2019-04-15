package com.xj.cardroommanage.base;

import android.os.Bundle;
import android.view.View;

import com.xj.mainframe.base.activity.BaseActivity;
import com.xj.mainframe.utils.StatusBarUtil;

public abstract class CRACtivity extends BaseActivity {

    public void setStatusView(View view){
        StatusBarUtil.darkMode(this);
        StatusBarUtil.setPaddingSmart(this,view);
    }
    @Override
    public void initView() {

    }

}
