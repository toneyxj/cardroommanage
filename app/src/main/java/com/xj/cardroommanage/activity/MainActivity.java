package com.xj.cardroommanage.activity;

import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.xj.cardroommanage.R;
import com.xj.cardroommanage.adapter.viewPage.MainPagerFragment;
import com.xj.cardroommanage.base.CRACtivity;
import com.xj.cardroommanage.fragment.ClassfiyFragment;
import com.xj.cardroommanage.fragment.MaJiangFragment;
import com.xj.cardroommanage.fragment.SouYeFragment;
import com.xj.mainframe.model.FragmentModel;
import com.xj.mainframe.utils.StatusBarUtil;


public class MainActivity extends CRACtivity {

//    @BindView(R.id.tablayout)
    private TabLayout tablayout;
//    @BindView(R.id.view_page)
    private ViewPager view_page;

    MainPagerFragment pagerFragment;

    @Override
    public void initActivity(Bundle savedInstanceState) {
        tablayout=findViewById(R.id.tablayout);
        view_page=findViewById(R.id.view_page);

        StatusBarUtil.darkMode(this);
        StatusBarUtil.setPaddingSmart(this,tablayout);
        pagerFragment=new MainPagerFragment(getFM());
        pagerFragment.setFragmentModels(new FragmentModel[]{
                new FragmentModel().setFragment(SouYeFragment.class).setTilte("首页"),
                new FragmentModel().setFragment(MaJiangFragment.class).setTilte("麻将列表"),
                new FragmentModel().setFragment(ClassfiyFragment.class).setTilte("服务分类")

        });
        view_page.setAdapter(pagerFragment);
        tablayout.setupWithViewPager(view_page);

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void onclickView(View view) {

    }

    @Override
    public void handleMessage(Message msg) {

    }
}
