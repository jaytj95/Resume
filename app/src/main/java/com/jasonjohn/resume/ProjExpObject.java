package com.jasonjohn.resume;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jtjohn on 10/20/2015.
 */
public class ProjExpObject implements Parcelable {

    private String title, date;
    private int bgResId;
    private int about;

    public int[] getPics() {
        return pics;
    }

    public void setPics(int[] pics) {
        this.pics = pics;
    }

    private int[] pics;

    public ProjExpObject(String title, String company, int bgResId, int about, int[] pics) {
        setTitle(title);
        setDate(company);
        setBgResId(bgResId);
        setAbout(about);
        setPics(pics);
    }

    public ProjExpObject(Parcel p) {
        setTitle(p.readString());
        setDate(p.readString());
        setBgResId(p.readInt());
        setAbout(p.readInt());
        setPics(p.createIntArray());
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBgResId() {
        return bgResId;
    }

    public void setBgResId(int bgResId) {
        this.bgResId = bgResId;
    }

    public int getAbout() {
        return about;
    }

    public void setAbout(int about) {
        this.about = about;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getTitle());
        parcel.writeString(getDate());
        parcel.writeInt(getBgResId());
        parcel.writeInt(getAbout());
        parcel.writeIntArray(getPics());
    }

    public static final Parcelable.Creator<ProjExpObject> CREATOR = new Parcelable.Creator<ProjExpObject>() {

        @Override
        public ProjExpObject createFromParcel(Parcel parcel) {
            return new ProjExpObject(parcel);
        }

        @Override
        public ProjExpObject[] newArray(int i) {
            return new ProjExpObject[i];
        }
    };
}
