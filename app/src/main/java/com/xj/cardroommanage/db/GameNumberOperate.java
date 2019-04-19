package com.xj.cardroommanage.db;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.xj.cardroommanage.db.Model.Classfiy;
import com.xj.cardroommanage.db.Model.GameNumber;
import com.xj.cardroommanage.inter.BackOperate;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class GameNumberOperate {

    /**
     * 获得所有分类数据
     *
     * @return
     */
    public static List<GameNumber> getAllDatas() {
        return LitePal.findAll(GameNumber.class);
    }

    public static void saveData(GameNumber gn) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameNumber cf = getGameNumber(gn.name);
                if (cf != null) {
                    cf.source=gn.source;
                    cf.phone=gn.phone;
                    cf.userName=gn.userName;
                    cf.price=gn.price;
                    cf.classfiy=gn.classfiy;
                    cf.state=gn.state;
                    cf.state=gn.state;
                    cf.startTime=gn.startTime;
                    cf.endTime=gn.endTime;
                    cf.update(cf.id);
                } else {
                    gn.save();
                }
            }
        }).start();
    }

    /**
     * 重置麻将桌为空闲状态
     *
     * @param gameNumber
     */
    public static void restGame(@NonNull GameNumber gameNumber) {
        gameNumber.endTime = 0;
        gameNumber.startTime = 0;
        gameNumber.state = 0;
        gameNumber.price = ClassfiyOperate.getClassfiy(gameNumber.classfiy).price;
        gameNumber.phone = "";
        gameNumber.userName = "";
        gameNumber.source = "";
        gameNumber.update(gameNumber.id);
    }

    public static int size() {
        return LitePal.count(Classfiy.class);
    }

    public static GameNumber getGameNumber(String name) {
        Cursor cursor = LitePal.findBySQL("select id,source,phone,userName,GameNumber,name,price,state,startTime,endTime from GameNumber where name='" + name + "'");
        GameNumber gn = null;
        if (cursor.moveToNext()) {
            gn = getModel(cursor);
        }
        if (cursor != null)
            cursor.close();
        return gn;
    }

    private static GameNumber getModel(Cursor cursor) {
        long id = cursor.getLong(0);
        String source = cursor.getString(1);
        String phone = cursor.getString(2);
        String userName = cursor.getString(3);
        String classfiy = cursor.getString(4);
        String name = cursor.getString(5);
        float price = cursor.getFloat(6);
        int state = cursor.getInt(7);
        long startTime = cursor.getLong(8);
        long endTime = cursor.getLong(9);
        GameNumber gn = new GameNumber();
        gn.id = id;
        gn.source = source;
        gn.phone = phone;
        gn.userName = userName;
        gn.classfiy = classfiy;
        gn.name = name;
        gn.price = price;
        gn.state = state;
        gn.startTime = startTime;
        gn.endTime = endTime;
        return gn;
    }
}
