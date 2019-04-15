package com.xj.cardroommanage.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.xj.cardroommanage.R;
import com.xj.cardroommanage.activity.ClassfiyActivity;
import com.xj.cardroommanage.adapter.ClassfiyAdapter;
import com.xj.cardroommanage.db.ClassfiyOperate;
import com.xj.cardroommanage.db.Model.Classfiy;
import com.xj.cardroommanage.utils.ConfigUtils;
import com.xj.mainframe.base.fragment.BaseFragment;
import com.xj.mainframe.configer.APPLog;
import com.xj.mainframe.eventBus.EventManger;
import com.xj.mainframe.eventBus.EventObserver;
import com.xj.mainframe.view.BaseView.XJTextView;

import java.util.List;

/**
 * 显示当前的分类信息
 */
public class ClassfiyFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private RecyclerView recyclerView;

    private ClassfiyAdapter adapter;
    private XJTextView addView;
    @Override
    public void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public void fragmentShowing(boolean is) {

    }

    @Override
    public void initView(View view) {
        recyclerView=view.findViewById(R.id.recycle);
        addView=view.findViewById(R.id.add_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        EventManger.getInstance().registerObserver(ConfigUtils.CLASSFIY_REFURESH,observer);
        adapter=new ClassfiyAdapter(ClassfiyOperate.getAllDatas(), R.layout.adapter_classfiy, this);
        recyclerView.setAdapter(adapter);

        addView.setOnClickListener(clickListener);
    }

    EventObserver observer=new EventObserver() {
        @Override
        public void eventUpdate(int code, Object data) {
            if (code==ConfigUtils.CLASSFIY_REFURESH){
                if (adapter==null){
                    return;
                }else {
                    adapter.refresh(ClassfiyOperate.getAllDatas());
                }
            }
        }
    };

    @Override
    public int getLayoutID() {
        return R.layout.fragment_classfiy;
    }

    @Override
    public void onclickView(View view) {
        switch (view.getId()){
            case R.id.add_view:
                ClassfiyActivity.startActivity(getContext(),"");
                break;
                default:
                    break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Classfiy classfiy=(Classfiy)adapter.getItem(position);
        ClassfiyActivity.startActivity(getContext(),classfiy.name);
    }
}
