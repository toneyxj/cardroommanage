package com.xj.cardroommanage.adapter;

import android.widget.AdapterView;

import com.xj.cardroommanage.R;
import com.xj.cardroommanage.db.Model.Classfiy;
import com.xj.mainframe.adapter.BaseRecyclerAdapter;
import com.xj.mainframe.adapter.SmartViewHolder;
import com.xj.mainframe.configer.APPLog;

import java.util.Collection;

public class ClassfiyAdapter extends BaseRecyclerAdapter<Classfiy> {
    public ClassfiyAdapter(int layoutId) {
        super(layoutId);
    }

    public ClassfiyAdapter(Collection<Classfiy> collection, int layoutId) {
        super(collection, layoutId);
    }

    public ClassfiyAdapter(Collection<Classfiy> collection, int layoutId, AdapterView.OnItemClickListener listener) {
        super(collection, layoutId, listener);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, Classfiy model, int position) {
        holder.text(R.id.name, model.name);
        holder.text(R.id.price, "￥"+model.price);
        holder.visible(R.id.line,position!=(getCount()-1));
    }
}
