package com.xj.cardroommanage.db.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.xj.cardroommanage.utils.ConfigUtils;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * 麻将桌数以及相应的状态
 */
public class GameNumber extends LitePalSupport implements Parcelable {
    @Column(unique = true)
    public long id;
    /**
     * 用户来源
     */
    public String source;
    /**
     * 玩家电话
     */
    public String phone;
    /**
     * 玩家姓名
     */
    public String userName;
    /**
     * 游戏分类名
     */
    public String classfiy;
    /**
     * 游戏桌命名
     */
    @Column(unique = true)
    public String name;

    public float price = 0.0f;
    /**
     * 游戏桌状态，0：空置，1：使用中,2：已定，3：维修中。
     */
    public int state = 0;
    /**
     * 开始游戏时间
     */
    public long startTime=0;
    /**
     * 游戏预计结束时间，方便团购用户时间到时提示，自来设置为默认设置时间
     */
    public long endTime=0;

    public String getState() {
        String value = "";
        try {
            value=ConfigUtils.MJ_STATE[state];
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.source);
        dest.writeString(this.phone);
        dest.writeString(this.userName);
        dest.writeString(this.classfiy);
        dest.writeString(this.name);
        dest.writeFloat(this.price);
        dest.writeInt(this.state);
        dest.writeLong(this.startTime);
        dest.writeLong(this.endTime);
    }

    public GameNumber() {
    }

    protected GameNumber(Parcel in) {
        this.id = in.readLong();
        this.source = in.readString();
        this.phone = in.readString();
        this.userName = in.readString();
        this.classfiy = in.readString();
        this.name = in.readString();
        this.price = in.readFloat();
        this.state = in.readInt();
        this.startTime = in.readLong();
        this.endTime = in.readLong();
    }

    public static final Creator<GameNumber> CREATOR = new Creator<GameNumber>() {
        @Override
        public GameNumber createFromParcel(Parcel source) {
            return new GameNumber(source);
        }

        @Override
        public GameNumber[] newArray(int size) {
            return new GameNumber[size];
        }
    };
}
