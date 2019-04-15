package com.xj.cardroommanage.db.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Classfiy extends LitePalSupport implements Parcelable {
    @Column(unique = true)
    public long id;

    @Column(nullable = false,unique = true)
    /**
     * 数据分类名字如，精品机麻，普通机麻，小吃，烧烤，等数据描述
     */
    public String name;
    /**
     * 价格，价格一小时的收费价格
     */
    public float price=0.0f;

    /**
     * 列外
     */
    public String outside;
    /**
     * 0为机麻 ，1为其他
     */
    public int state;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeFloat(this.price);
        dest.writeString(this.outside);
        dest.writeInt(this.state);
    }

    public Classfiy() {
    }

    protected Classfiy(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.price = in.readFloat();
        this.outside = in.readString();
        this.state = in.readInt();
    }

    public static final Creator<Classfiy> CREATOR = new Creator<Classfiy>() {
        @Override
        public Classfiy createFromParcel(Parcel source) {
            return new Classfiy(source);
        }

        @Override
        public Classfiy[] newArray(int size) {
            return new Classfiy[size];
        }
    };
}
