package com.xj.cardroommanage.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xj.cardroommanage.R;
import com.xj.cardroommanage.base.CRTitleActivity;
import com.xj.cardroommanage.db.ClassfiyOperate;
import com.xj.cardroommanage.db.GameNumberOperate;
import com.xj.cardroommanage.db.Model.Classfiy;
import com.xj.cardroommanage.db.Model.GameNumber;
import com.xj.cardroommanage.utils.ConfigUtils;
import com.xj.mainframe.configer.ToastUtils;
import com.xj.mainframe.dialog.ListDialog;
import com.xj.mainframe.utils.StringUtils;
import com.xj.mainframe.view.BaseView.XJEditeView;
import com.xj.mainframe.view.BaseView.XJTextView;
import com.xj.mainframe.view.otherView.TitleView;

import java.util.List;

public class GameActivity extends CRTitleActivity {

    public static void startActivity(Context context, String name) {
        Intent intent = new Intent(context, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected TitleView.TitleModel getTitleMode() {
        return new TitleView.TitleModel().setTitle("机麻编辑").setTxtRight("保存").setTitleClickListener(this);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_game;
    }

    private String modelName;
    private GameNumber gameNumber;

    private XJTextView state;
    private XJTextView source;
    private XJTextView item_style;

    private XJEditeView game_name;
    private XJEditeView money;
    private XJEditeView time;
    private XJEditeView user_name;
    private XJEditeView user_phone;

    private ListDialog listDialog;
    /**
     * 机麻分类
     */
    private String[] styles;
    @Override
    public void initActivity(Bundle savedInstanceState) {
        super.initActivity(savedInstanceState);

        state = findViewById(R.id.state);
        source = findViewById(R.id.source);
        item_style = findViewById(R.id.item_style);
        game_name = findViewById(R.id.game_name);
        money = findViewById(R.id.money);
        time = findViewById(R.id.time);
        user_name = findViewById(R.id.user_name);
        user_phone = findViewById(R.id.user_phone);

        state.setOnClickListener(clickListener);
        source.setOnClickListener(clickListener);
        item_style.setOnClickListener(clickListener);

        if (savedInstanceState == null) {
            savedInstanceState = getIntent().getExtras();
        }
        modelName = savedInstanceState.getString("name");
        if (!StringUtils.isNull(modelName)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    gameNumber = GameNumberOperate.getGameNumber(modelName);
                  
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            initModel();
                        }
                    });
                    setStyles(ClassfiyOperate.getClassfiyGame());
                }
            }).start();
        }
    }

    public synchronized  void setStyles(List<Classfiy> list){
        styles=new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            styles[i] =list.get(i).name;
        }
    }

    private void initModel() {
        if (gameNumber == null)return;


        state.setText("当前状态：" + gameNumber.getState());
        if (gameNumber.source == null) gameNumber.source = "";
        state.setText("来源：" + gameNumber.source);
        if (gameNumber.classfiy == null) gameNumber.classfiy = "";
        item_style.setText("项目类型：" + gameNumber.classfiy);

        game_name.setEditeText(gameNumber.name);
        game_name.setEnabled(false);

        money.setText(String.valueOf(gameNumber.price));

        if (gameNumber.startTime == 0 || gameNumber.endTime == 0) {
            time.setEditeText("1.0");
        } else {
            float times = (Math.abs(gameNumber.endTime - gameNumber.startTime) / 3600000f);
            time.setEditeText(String.valueOf(times));
        }
        user_name.setEditeText(gameNumber.userName);
        user_phone.setEditeText(gameNumber.phone);

        findViewById(R.id.delete).setOnClickListener(clickListener);
        findViewById(R.id.delete).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", modelName);
    }

    @Override
    public void onclickView(View view) {
        String[] values=null;
        switch (view.getId()) {
            case R.id.state:
                values=ConfigUtils.MJ_STATE;

                break;
            case R.id.source:
                values=ConfigUtils.SOURCE_PLATFORM;
                break;
            case R.id.item_style:
                if (styles==null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            setStyles(ClassfiyOperate.getClassfiyGame());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (styles.length==0){
                                        ClassfiyActivity.startActivity(GameActivity.this,"");
                                        ToastUtils.getInstance().showToastShort("请添加机麻类别！！");
                                        return;
                                    }
                                    listDialog = ListDialog.getdialog(GameActivity.this, "选择", itemListener, styles);
                                    listDialog.setTag(view.getId());
                                }
                            });
                        }
                    }).start();
                }else {
                    values=styles;
                }
                break;
            case R.id.delete://删除

                break;
            default:
                break;
        }
        if (values!=null){
            listDialog=ListDialog.getdialog(GameActivity.this, "选择", itemListener,values);
            listDialog.setTag(view.getId());
        }
    }

    ListDialog.ClickListItemListener itemListener = new ListDialog.ClickListItemListener() {
        @Override
        public void onClickItem(Object tag, int position) {
            switch ((Integer) tag){
                case R.id.state:
                    state.setTextV(ConfigUtils.MJ_STATE[position]);
                    break;
                case R.id.source:
                    source.setTextV(ConfigUtils.SOURCE_PLATFORM[position]);
                    break;
                case R.id.item_style:
                    item_style.setTextV(styles[position]);
                    break;
                    default:
                        break;
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (listDialog!=null&&listDialog.isShowing()){
            listDialog.dismiss();
        }
    }

    @Override
    public void onClickRTxt() {
    }

    @Override
    public void handleMessage(Message msg) {

    }
}
