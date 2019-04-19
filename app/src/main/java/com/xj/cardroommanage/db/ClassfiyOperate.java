package com.xj.cardroommanage.db;

import android.database.Cursor;

import com.xj.cardroommanage.db.Model.Classfiy;
import com.xj.mainframe.configer.APPLog;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ClassfiyOperate {
    private static final String DBDATA="id,name,price,state";
    /**
     * 获得所有分类数据
     * @return
     */
    public static List<Classfiy> getAllDatas(){
        return LitePal.findAll(Classfiy.class);
    }

    /**
     * 获得机麻的类别
     * @return
     */
    public synchronized static List<Classfiy> getClassfiyGame(){
        Cursor cursor=LitePal.findBySQL("select "+DBDATA+" from Classfiy where state=1");
        List<Classfiy> classfiys=new ArrayList<>();
        while (cursor.moveToNext()) {
            classfiys.add(getModel(cursor));
        }
        if (cursor != null)
            cursor.close();
        return classfiys;
    }

    public static void saveData(Classfiy classfiy){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Classfiy cf=getClassfiy(classfiy.name);
                if (cf!=null){
                    cf.price=classfiy.price;
                    cf.state=classfiy.state;

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
        Cursor cursor=LitePal.findBySQL("select "+DBDATA+" from Classfiy where name='"+name+"'");
        Classfiy classfiy=null;
        if (cursor.moveToNext()) {
            classfiy=getModel(cursor);
        }
        if (cursor != null)
            cursor.close();
        return classfiy;
    }

    public static boolean isDeleteModel(Classfiy model){
        int size=model.delete();
        return size>0;
    }

    private static Classfiy getModel(Cursor cursor){
        long id = cursor.getLong(0);
        String name = cursor.getString(1);
        float price = cursor.getFloat(2);
        int state = cursor.getInt(3);
        Classfiy classfiy=new Classfiy();
        classfiy.id=id;
        classfiy.name=name;
        classfiy.price=price;
        classfiy.state=state;
        return classfiy;
    }
}
