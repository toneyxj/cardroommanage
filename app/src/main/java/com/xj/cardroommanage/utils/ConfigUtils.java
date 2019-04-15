package com.xj.cardroommanage.utils;

public class ConfigUtils {

    public final static String[] SOURCE_PLATFORM=new String[]{
            "大众点评","美团","百度","口碑","其他平台","朋友介绍","回头客","自己找来","其余来源"
    };
    public final static String[] MJ_STATE=new String[]{
       "空置","使用中","已定","维修中"
    };

    /**
     * 早上时间定义
     */
    public final static float[] PM=new float[]{9f,1f};
    /**
     * 下午时间定义
     */
    public final static float[] AM=new float[]{1f,20f};
    /**
     * 凌晨时间，除去上午和下午都算晚上时间
     */
    public final static float[] NIGHT=new float[]{20f,24f};
    /**
     * 刷新首页
     */
    public final static int SOUYE_REFURESH=10;
    /**
     * 分类刷新
     */
    public final static int CLASSFIY_REFURESH=11;
    /**
     * 游戏页面刷新
     */
    public final static int GAME_REFURESH=12;
    /**
     * 游戏页面单个刷新
     */
    public final static int GAME_ONLY_REFURESH=13;

}
