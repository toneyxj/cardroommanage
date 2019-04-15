package com.xj.cardroommanage.adapter;

import android.graphics.Color;
import android.widget.AdapterView;
import android.widget.TextView;

import com.xj.cardroommanage.R;
import com.xj.cardroommanage.db.Model.Classfiy;
import com.xj.cardroommanage.db.Model.GameNumber;
import com.xj.mainframe.adapter.BaseRecyclerAdapter;
import com.xj.mainframe.adapter.SmartViewHolder;
import com.xj.mainframe.utils.StringUtils;
import com.xj.mainframe.utils.TimeUtils;

import java.util.Collection;
import java.util.List;

public class GameAdapter extends BaseRecyclerAdapter<GameNumber> {
    public GameAdapter(int layoutId) {
        super(layoutId);
    }

    public GameAdapter(Collection<GameNumber> collection, int layoutId) {
        super(collection, layoutId);
    }

    public GameAdapter(Collection<GameNumber> collection, int layoutId, AdapterView.OnItemClickListener listener) {
        super(collection, layoutId, listener);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, GameNumber model, int position) {
        String source="";
        try {
            TextView view= (TextView) holder.findViewById(R.id.title_describe);
            switch (model.state){
                case 0:
                    view.setTextColor(Color.RED);
                    holder.visibleGone(R.id.user_info,false);
                    break;
                case 1:
                    view.setTextColor(Color.GREEN);
                    source="用户来源："+model.source;
                    String value="";
                    if (!StringUtils.isNull(model.name)){
                        value+="玩家姓名：" +model.name;
                    }

                    if (!StringUtils.isNull(model.phone)){
                        value+=" 、玩家电话" +model.phone;
                    }
                    if (StringUtils.isNull(value)){
                        holder.visibleGone(R.id.user_info,false);
                    }else {
                        holder.visibleGone(R.id.user_info,true);
                        holder.text(R.id.user_info,value);
                    }

                    //时间显示
                    holder.visibleGone(R.id.time,false);
                    break;
                case 3:
                    view.setTextColor(Color.GRAY);
                    holder.visibleGone(R.id.user_info,false);
                    break;
                default:
                    break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.text(R.id.title_describe, "机麻编号："+model.name+"  、"+model.classfiy);
        holder.text(R.id.cur_state, "状态："+model.getState()+"  、"+source);

        holder.text(R.id.money,"单价：￥"+model.price);

        holder.visibleGone(R.id.time,model.startTime!=0);
        String start=TimeUtils.getTimeShowString(model.startTime,true);
        String end=TimeUtils.getTimeShowString(model.endTime,true);
        holder.text(R.id.time,"游戏时间："+start+"-"+end);
        holder.visible(R.id.game_over,model.state==1);
    }

    public void  notifiyModel(GameNumber model){
        List<GameNumber> list=getList();

        int positon=-1;
        for (int i = 0; i < list.size(); i++) {
            GameNumber gn=list.get(i);
            if (gn.equals(model.name)){
                positon=i;
                break;
            }
        }
        if (positon==-1){
            getList().add(model);
        }else {
            list.remove(positon);
            list.add(positon,model);
        }
        notifyDataSetChanged();
        notifyListDataSetChanged();
    }
}
