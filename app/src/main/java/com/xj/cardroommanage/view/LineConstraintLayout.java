package com.xj.cardroommanage.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xj.cardroommanage.R;
import com.xj.mainframe.view.BaseView.XJTextView;

public class LineConstraintLayout extends ConstraintLayout {
    public LineConstraintLayout(Context context) {
        this(context,null);
    }

    public LineConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private XJTextView start;
    private XJTextView end;
    private void init(){
        inflate(getContext(), R.layout.view_line_layout,this);

        start=findViewById(R.id.start);
        end=findViewById(R.id.end);
    }

    public TextView getStart() {
        return start;
    }

    public TextView getEnd() {
        return end;
    }

    public void setData(String start,String end){
        this.start.setTextV(start);
        this.end.setTextV(end);
    }
}
