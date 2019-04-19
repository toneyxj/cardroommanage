package com.xj.mainframe.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xj.mainframe.BaseApplication;
import com.xj.mainframe.R;
import com.xj.mainframe.utils.DensityUtil;
import com.xj.mainframe.view.BaseView.XJTextView;
import com.xj.mainframe.view.extendView.AlphaTextview;
import com.xj.refuresh.util.DesignUtil;

/**
 * Created by Administrator on 2019/3/1.
 */

public class ListDialog extends Dialog implements View.OnClickListener {
    private TextView titleview;// dialog显示文字控件
    private LinearLayout list_item;// dialog显示文字控件
    private ScrollView scroll_view;
    private Button qiut;// 确定
    private String title;// 标题
    private String[] hints;// 提示内容
    private ClickListItemListener listener;
    private Object tag=null;

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public ListDialog(Context context, int theme, String title,
                      ClickListItemListener listener, String... hints) {
        super(context, theme);
        this.hints = hints;
        this.title = title;
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_list);
        scroll_view = (ScrollView) findViewById(R.id.scroll_view);
        list_item = (LinearLayout) findViewById(R.id.list_item);
        titleview = (TextView) findViewById(R.id.title);
        qiut = (Button) findViewById(R.id.qiut);

        titleview.setText(title);

        qiut.setOnClickListener(this);
        addTexts(getContext());
    }
    private void addTexts(Context context){
        list_item.removeAllViews();
        int SHeight= DensityUtil.getScreenH(context);
        int height=SHeight/20;
        int pading=DensityUtil.dip2px(getContext(),12);
        int i=0;

        if (hints.length>5){
            scroll_view.getLayoutParams().height=height*5;
        }
        for (String txt:hints) {
            AlphaTextview textView = new AlphaTextview(context);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            textView.setLayoutParams(params);
            textView.setTextSize(height/6);
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER);
            textView.setTag(i);
            textView.setText(txt);
            textView.setOnClickListener(this);
            list_item.addView(textView);
            i++;
        }
    }


    /**
     * 显示输入提示框
     *
     * @param context   上下文
     * @param title     标题
     * @param hints     显示内容
     * @param listeners 监听
     */
    public static ListDialog getdialog(Context context, String title,
                                       ClickListItemListener listeners, String... hints) {
        if (hints.length<1)return null;

        ListDialog dialog = null;
        try {
            dialog = new ListDialog(context, R.style.dialog, title,
                    listeners, hints);
            dialog.setCanceledOnTouchOutside(true);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);

            int ScreenWidth=DensityUtil.getScreenW(context);

            window.getDecorView().setPadding(ScreenWidth / 6, 0, ScreenWidth / 6, 0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.qiut) {
            this.dismiss();
        }  else {
            try {
                int index= (int) v.getTag();
                if (listener!=null){
                    listener.onClickItem(tag,index);
                }
                this.dismiss();
            }catch (Exception e){
                e.printStackTrace();
                dismiss();
            }
        }
    }

    @Override
    protected void onStop() {
        list_item.removeAllViews();
        super.onStop();
    }

    public interface ClickListItemListener {
        void onClickItem(Object tag,int position);
    }
}

