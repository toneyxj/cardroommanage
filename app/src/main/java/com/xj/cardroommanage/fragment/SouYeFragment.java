package com.xj.cardroommanage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.xj.cardroommanage.R;
import com.xj.cardroommanage.activity.ClassfiyActivity;
import com.xj.cardroommanage.db.ClassfiyOperate;
import com.xj.cardroommanage.utils.ConfigUtils;
import com.xj.mainframe.base.fragment.BaseFragment;
import com.xj.mainframe.eventBus.EventManger;
import com.xj.mainframe.eventBus.EventObserver;
import com.xj.mainframe.view.BaseView.XJTextView;

public class SouYeFragment extends BaseFragment {

    private LinearLayout list_view;
    @Override
    public void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public void fragmentShowing(boolean is) {

    }

    @Override
    public void initView(View view) {
        list_view=view.findViewById(R.id.list_view);
        observer.eventUpdate(ConfigUtils.SOUYE_REFURESH,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventManger.getInstance().registerObserver(ConfigUtils.SOUYE_REFURESH,observer);
    }

    private EventObserver observer=new EventObserver() {
        @Override
        public void eventUpdate(int code, Object data) {
            if (code==ConfigUtils.SOUYE_REFURESH){
                //更新首页数据显示
                list_view.removeAllViews();
                if (ClassfiyOperate.size()==0){
                    ClassfiyActivity.startActivity(getContext(),"");
                    return;
                }


            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManger.getInstance().removeObserver(ConfigUtils.SOUYE_REFURESH);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_souye;
    }
}
