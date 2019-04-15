package com.xj.cardroommanage.been;

import com.xj.mainframe.utils.TimeUtils;

/**
 * 其余的设置包括折扣设置
 */
public class OtherSettingModel {

    private static OtherSettingModel instance;

    public static OtherSettingModel getInstance() {
        if (instance == null) {
            synchronized (OtherSettingModel.class) {
                if (instance == null) {
                    instance = new OtherSettingModel();
                    instance.setDiscountWithTime();
                }
            }
        }
        return instance;
    }

    /**
     * 早上折扣
     */
    public float AM_discount = 0.8f;
    /**
     * 下午折扣
     */
    public float PM_discount = 1f;
    /**
     * 晚上折扣
     */
    public float NIGHT_discount = 1f;

    public int getDiscountNumber() {
        return 3;
    }

    /**
     * 折扣方案有三个
     *
     * @param style
     */
    public void setDiscount(int style) {
        switch (style) {
            case 0://无折扣
                AM_discount=1f;
                PM_discount=1f;
                NIGHT_discount=1f;
                break;
            case 1://一级折扣
                AM_discount=0.8f;
                PM_discount=1f;
                NIGHT_discount=1f;
                break;
            default://其余折扣方案
                AM_discount=0.7f;
                PM_discount=0.9f;
                NIGHT_discount=0.9f;
                break;

        }
    }

    /**
     * 根据日期进行折扣自动设置
     */
    public void setDiscountWithTime() {
        int week = TimeUtils.getWeekof();
        switch (week) {//周末折扣方案
            case 0:
            case 6:
                setDiscount(1);
                break;
            default://1-周五的折扣方案
                setDiscount(2);
                break;
        }
    }


}
