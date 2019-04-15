package com.xj.cardroommanage.db;

import android.database.Cursor;

import com.xj.cardroommanage.db.Model.Classfiy;
import com.xj.mainframe.configer.APPLog;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ClassfiyOperate {
    /**
     * 获得所有分类数据
     * @return
     */
    public static List<Classfiy> getAllDatas(){
        return LitePal.findAll(Classfiy.class);
    }

    public static void saveData(Classfiy classfiy){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Classfiy cf=getClassfiy(classfiy.name);
                if (cf!=null){
                    cf.price=classfiy.price;
                    cf.update(cf.id);
                }else {
                    classfiy.save();
                }
            }
        }).start();

    }

    public static int size(){
        return LitePal.count(Classfiy.class);
    }

    public static Classfiy getClassfiy(String name){
        Cursor cursor=LitePal.findBySQL("select id,name,price from Classfiy where name='"+name+"'");
        Classfiy classfiy=null;
        if (cursor.moveToNext()) {
            classfiy=getModel(cursor);
        }
        if (cursor != null)
            cursor.close();
        return classfiy;
    }

    private static Classfiy getModel(Cursor cursor){
        long id = cursor.getLong(0);
        String name = cursor.getString(1);
        float price = cursor.getFloat(2);
        Classfiy classfiy=new Classfiy();
        classfiy.id=id;
        classfiy.name=name;
        classfiy.price=price;
        return classfiy;
    }
}
