package com.xj.cardroommanage;

import android.os.Debug;

import com.xj.mainframe.BaseApplication;
import com.xj.mainframe.base.InitializeService;
import com.xj.mainframe.configer.ToastUtils;

public class Myapplication extends BaseApplication {
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    public void onCreate() {
//我就是改了哈哈
        Debug.startMethodTracing("ObjApp");
        super.onCreate();
//注册提示
        InitializeService.start(this,MyInitService.class);
        ToastUtils.getInstance().initToast(this);
        Debug.stopMethodTracing();
    }
}
