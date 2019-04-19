package com.xj.cardroommanage.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.xj.cardroommanage.R;
import com.xj.cardroommanage.base.CRTitleActivity;
import com.xj.cardroommanage.db.ClassfiyOperate;
import com.xj.cardroommanage.db.Model.Classfiy;
import com.xj.cardroommanage.utils.ConfigUtils;
import com.xj.mainframe.base.activity.BaseTitleActivity;
import com.xj.mainframe.configer.ToastUtils;
import com.xj.mainframe.eventBus.EventManger;
import com.xj.mainframe.eventBus.EventObserver;
import com.xj.mainframe.utils.StatusBarUtil;
import com.xj.mainframe.utils.StringUtils;
import com.xj.mainframe.view.BaseView.XJEditeView;
import com.xj.mainframe.view.otherView.TitleView;

public class ClassfiyActivity extends CRTitleActivity {

    public static void startActivity(Context context, String name) {
        Intent intent = new Intent(context, ClassfiyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected TitleView.TitleModel getTitleMode() {
        return new TitleView.TitleModel().setTitle("服务分类").setTxtRight("保存").setTitleClickListener(this);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_classfiy;
    }

    private XJEditeView name;
    private XJEditeView price;
    private String modelName;
    private Classfiy CurClassfiy;
    private RadioButton is_game;

    @Override
    public void initActivity(Bundle savedInstanceState) {
        super.initActivity(savedInstanceState);

        is_game = findViewById(R.id.is_game);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);

        if (savedInstanceState == null) {
            savedInstanceState = getIntent().getExtras();
        }
        modelName = savedInstanceState.getString("name");

        if (!StringUtils.isNull(modelName)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CurClassfiy = ClassfiyOperate.getClassfiy(modelName);
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            if (isFinishing()) return;
                            if (CurClassfiy!=null) {
                                name.setText(CurClassfiy.name);
                                price.setText(String.valueOf(CurClassfiy.price));
                                is_game.setChecked(CurClassfiy.state == 1);

                                name.setEnabled(false);

                                findViewById(R.id.delete).setOnClickListener(clickListener);
                                findViewById(R.id.delete).setVisibility(View.VISIBLE);
                            }

                        }
                    });
                }
            }).start();

        }
    }

    @Override
    public void onclickView(View view) {
        switch (view.getId()) {
            case R.id.delete:
                if (CurClassfiy!=null){
                   boolean is= ClassfiyOperate.isDeleteModel(CurClassfiy);
                   if (!is) {
                       ToastUtils.getInstance().showToastShort("删除失败");
                   }else {
                       EventManger.getInstance().notifiyCode(ConfigUtils.CLASSFIY_REFURESH,null);
                       onBackPressed();
                   }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", modelName);
    }

    @Override
    public void onClickRTxt() {
        String na = name.getText().toString().trim();
        if (StringUtils.isNull(na)) {
            ToastUtils.getInstance().showToastShort("请输入分类名");
            return;
        }
        float pri = 0f;
        try {
            pri = Float.parseFloat(price.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.getInstance().showToastShort("请输入分类产品对应的价格");
            return;
        }

        if (pri < 0) {
            ToastUtils.getInstance().showToastShort("价格不能低于0");
            return;
        }
        Classfiy classfiy = new Classfiy();
        classfiy.name = na;
        classfiy.price = pri;
        classfiy.state = is_game.isChecked() ? 1 : 0;

        ClassfiyOperate.saveData(classfiy);
        ToastUtils.getInstance().showToastShort("添加成功");
        if (CurClassfiy!=null){
            onBackPressed();
        }
        EventManger.getInstance().notifiyCode(ConfigUtils.CLASSFIY_REFURESH,null);
    }

    @Override
    public void handleMessage(Message msg) {

    }
}
