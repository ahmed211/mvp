package com.darorman.gm3yaorman.api.model.SignUP;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ahmed Mostafa on 9/13/2018.
 */
public class SignUpData1 implements Parcelable{
    private String person_name;
    private String ParentPlace_ID;
    private String City_ID;
    private String Address;
    private String Mobile;
    private String PhoneHome;
    private String depID;

    public String getPerson_name() {
        return person_name;
    }

    public String getParentPlace_ID() {
        return ParentPlace_ID;
    }

    public String getCity_ID() {
        return City_ID;
    }

    public String getAddress() {
        return Address;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPhoneHome() {
        return PhoneHome;
    }

    public String getDepID() {
        return depID;
    }

    public SignUpData1(String person_name, String ParentPlace_ID, String City_ID,
                       String Address, String Mobile, String PhoneHome, String depID) {
        this.person_name = person_name;
        this.ParentPlace_ID = ParentPlace_ID;
        this.City_ID = City_ID;
        this.Address = Address;
        this.Mobile = Mobile;
        this.PhoneHome = PhoneHome;
        this.depID = depID;
    }

    protected SignUpData1(Parcel in) {
        person_name = in.readString();
        ParentPlace_ID = in.readString();
        City_ID = in.readString();
        Address = in.readString();
        Mobile = in.readString();
        PhoneHome = in.readString();
        depID = in.readString();
    }

    public static final Creator<SignUpData1> CREATOR = new Creator<SignUpData1>() {
        @Override
        public SignUpData1 createFromParcel(Parcel in) {
            return new SignUpData1(in);
        }

        @Override
        public SignUpData1[] newArray(int size) {
            return new SignUpData1[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(person_name);
        parcel.writeString(ParentPlace_ID);
        parcel.writeString(City_ID);
        parcel.writeString(Address);
        parcel.writeString(Mobile);
        parcel.writeString(PhoneHome);
        parcel.writeString(depID);
    }
}
