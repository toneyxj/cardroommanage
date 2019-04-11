package com.xj.mainframe.base.MVP;

import android.os.Handler;
import android.os.Message;

import com.xj.mainframe.base.BaseUtils;
import com.xj.mainframe.listener.HandlerMessageInterface;

/**
 *控制器基本类
 */
public  class BasePresenter implements HandlerMessageInterface {
    private boolean isFinish=false;
    private Handler handler=new BaseUtils.XJHander(this);
    public Handler getHandler(){
        return handler;
    }
    public boolean isFinish() {
        return isFinish;
    }

    public void onReStart(){
        isFinish=false;
    }

    public void onDestory(){
        isFinish=true;
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void handleMessage(Message msg) {

    }
}
