package com.xj.cardroommanage.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.xj.cardroommanage.R;
import com.xj.cardroommanage.activity.ClassfiyActivity;
import com.xj.cardroommanage.activity.GameActivity;
import com.xj.cardroommanage.adapter.ClassfiyAdapter;
import com.xj.cardroommanage.adapter.GameAdapter;
import com.xj.cardroommanage.db.ClassfiyOperate;
import com.xj.cardroommanage.db.GameNumberOperate;
import com.xj.cardroommanage.db.Model.Classfiy;
import com.xj.cardroommanage.db.Model.GameNumber;
import com.xj.cardroommanage.utils.ConfigUtils;
import com.xj.mainframe.base.fragment.BaseFragment;
import com.xj.mainframe.eventBus.EventManger;
import com.xj.mainframe.eventBus.EventObserver;
import com.xj.mainframe.view.BaseView.XJTextView;

/**
 * 显示游戏麻将桌信息
 */
public class MaJiangFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private RecyclerView recyclerView;

    private GameAdapter adapter;
    private XJTextView addView;

    @Override
    public void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public void fragmentShowing(boolean is) {

    }

    @Override
    public void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle);
        addView = view.findViewById(R.id.add_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        EventManger.getInstance().registerObserver(ConfigUtils.GAME_REFURESH, observer);
        EventManger.getInstance().registerObserver(ConfigUtils.GAME_ONLY_REFURESH, observer);
        adapter = new GameAdapter(GameNumberOperate.getAllDatas(), R.layout.adapter_game, this);
        recyclerView.setAdapter(adapter);

        addView.setOnClickListener(clickListener);
    }

    EventObserver observer = new EventObserver() {
        @Override
        public void eventUpdate(int code, Object data) {
            if (adapter == null) {
                return;
            }
            if (code == ConfigUtils.GAME_REFURESH) {
                adapter.refresh(GameNumberOperate.getAllDatas());
            } else if (code == ConfigUtils.GAME_ONLY_REFURESH) {
                    GameNumber gn= (GameNumber) data;
                    adapter.notifiyModel(gn);
            }
        }
    };

    @Override
    public int getLayoutID() {
        return R.layout.fragment_game;
    }

    @Override
    public void onclickView(View view) {
        switch (view.getId()) {
            case R.id.add_view:
                ClassfiyActivity.startActivity(getContext(), "");
                break;
            default:
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GameNumber game = (GameNumber) adapter.getItem(position);
        GameActivity.startActivity(getContext(),game.name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManger.getInstance().removeObserver(ConfigUtils.GAME_REFURESH,ConfigUtils.GAME_ONLY_REFURESH);
    }
}
