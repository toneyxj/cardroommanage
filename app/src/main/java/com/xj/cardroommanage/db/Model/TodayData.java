package com.xj.cardroommanage.db.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * 保存用户单笔消费
 */
public class TodayData extends LitePalSupport implements Parcelable {
    @Column(unique = true)
    public long id;
    /**
     * 桌数编号
     */
    public String GameNumber;
    /**
     * 当前时间-显示日期 、年月日
     */
    public String time;
    /**
     * 分类名
     */
    public String classfiyName;
    /**
     * 消费金额
     */
    public String shopMoney;
    /**
     * 是否已付钱
     */
    public boolean paid=false;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.GameNumber);
        dest.writeString(this.time);
        dest.writeString(this.classfiyName);
        dest.writeString(this.shopMoney);
        dest.writeByte(this.paid ? (byte) 1 : (byte) 0);
    }

    public TodayData() {
    }

    protected TodayData(Parcel in) {
        this.id = in.readLong();
        this.GameNumber = in.readString();
        this.time = in.readString();
        this.classfiyName = in.readString();
        this.shopMoney = in.readString();
        this.paid = in.readByte() != 0;
    }

    public static final Parcelable.Creator<TodayData> CREATOR = new Parcelable.Creator<TodayData>() {
        @Override
        public TodayData createFromParcel(Parcel source) {
            return new TodayData(source);
        }

        @Override
        public TodayData[] newArray(int size) {
            return new TodayData[size];
        }
    };
}
