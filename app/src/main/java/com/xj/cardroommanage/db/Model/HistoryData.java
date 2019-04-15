package com.xj.cardroommanage.db.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class HistoryData extends LitePalSupport implements Parcelable {
    @Column(unique = true)
    public long id;
    /**
     * 用户来源
     */
    public String source;
    /**
     * 桌数编号
     */
    public String gameNumber;
    /**
     * 用户手机号码
     */
    public String phone;

    public long startTime;

    public long endTime;
    /**
     * 描述用户当前整体消费情况
     */
    public String describe;

    public String totalPrice;
    /**
     * 游戏桌描述，如玩的是什么麻将
     */
    public String gameName;
    /**
     * 玩游戏消费
     */
    public String gamePrice;
    /**
     * 当天打折描述
     */
    public String discountDescibe;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.source);
        dest.writeString(this.gameNumber);
        dest.writeString(this.phone);
        dest.writeLong(this.startTime);
        dest.writeLong(this.endTime);
        dest.writeString(this.describe);
        dest.writeString(this.totalPrice);
        dest.writeString(this.gameName);
        dest.writeString(this.gamePrice);
        dest.writeString(this.discountDescibe);
    }

    public HistoryData() {
    }

    protected HistoryData(Parcel in) {
        this.id = in.readLong();
        this.source = in.readString();
        this.gameNumber = in.readString();
        this.phone = in.readString();
        this.startTime = in.readLong();
        this.endTime = in.readLong();
        this.describe = in.readString();
        this.totalPrice = in.readString();
        this.gameName = in.readString();
        this.gamePrice = in.readString();
        this.discountDescibe = in.readString();
    }

    public static final Parcelable.Creator<HistoryData> CREATOR = new Parcelable.Creator<HistoryData>() {
        @Override
        public HistoryData createFromParcel(Parcel source) {
            return new HistoryData(source);
        }

        @Override
        public HistoryData[] newArray(int size) {
            return new HistoryData[size];
        }
    };
}
