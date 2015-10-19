package com.jasonjohn.resume;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jtjohn on 10/19/2015.
 */
public class WorkExpObject implements Parcelable{
    private String title, company;
    private int bgResId;

    public WorkExpObject(Parcel p) {
        setTitle(p.readString());
        setCompany(p.readString());
        setBgResId(p.readInt());
    }

    public WorkExpObject(String title, String company, int bgResId) {
        setTitle(title);
        setCompany(company);
        setBgResId(bgResId);

    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getBgResId() {
        return bgResId;
    }

    public void setBgResId(int bgResId) {
        this.bgResId = bgResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getTitle());
        parcel.writeString(getCompany());
        parcel.writeInt(getBgResId());
    }

    public static final Parcelable.Creator<WorkExpObject> CREATOR = new Parcelable.Creator<WorkExpObject>() {

        @Override
        public WorkExpObject createFromParcel(Parcel parcel) {
            return new WorkExpObject(parcel);
        }

        @Override
        public WorkExpObject[] newArray(int i) {
            return new WorkExpObject[i];
        }
    };


}
