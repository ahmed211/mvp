package com.darorman.gm3yaorman.api.model.orman_activities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Ahmed Mostafa on 9/3/2018.
 */
public class Activities implements Parcelable{
    private String ActivityDec;

    private String Activitiesid;

    private List<GalleryAct> GalleryAct;

    private String ActivityTitel;

    private String ActivityImgUrl;

    protected Activities(Parcel in) {
        ActivityDec = in.readString();
        Activitiesid = in.readString();
        ActivityTitel = in.readString();
        ActivityImgUrl = in.readString();
    }

    public static final Creator<Activities> CREATOR = new Creator<Activities>() {
        @Override
        public Activities createFromParcel(Parcel in) {
            return new Activities(in);
        }

        @Override
        public Activities[] newArray(int size) {
            return new Activities[size];
        }
    };

    public String getActivityDec ()
    {
        return ActivityDec;
    }

    public void setActivityDec (String ActivityDec)
    {
        this.ActivityDec = ActivityDec;
    }

    public String getActivitiesid ()
    {
        return Activitiesid;
    }

    public void setActivitiesid (String Activitiesid)
    {
        this.Activitiesid = Activitiesid;
    }

    public List<GalleryAct> getGalleryAct() {
        return GalleryAct;
    }

    public String getActivityTitel ()
    {
        return ActivityTitel;
    }

    public void setActivityTitel (String ActivityTitel)
    {
        this.ActivityTitel = ActivityTitel;
    }

    public String getActivityImgUrl ()
    {
        return ActivityImgUrl;
    }

    public void setActivityImgUrl (String ActivityImgUrl)
    {
        this.ActivityImgUrl = ActivityImgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ActivityDec);
        parcel.writeString(Activitiesid);
        parcel.writeString(ActivityTitel);
        parcel.writeString(ActivityImgUrl);
    }
}
