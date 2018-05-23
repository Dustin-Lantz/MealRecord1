package com.example.dustin.mealrecord;

import android.os.Parcel;
import android.os.Parcelable;


public class ParClass implements Parcelable {
    private String field1;
    private String field2;
    private String field3;
    private String field4;

    /* Constructors, getters, setters, etc. that you’d normally have in your class */
    public ParClass(String s, String i, String g, String h) {
        field1 = s;
        field2 = i;
        field3 = g;
        field4 = h;
    }
    public String getField1() {
        return field1;
    }

    public String getField2(){
        return field2;
    }

    public String getField3() { return field3; }

    public String getField4() { return field4; }

    /* Code for the Parcelable interface */
    public int describeContents() {
        return 0;
    }

    /* Add the data in your class fields to the Parcel object dest */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(field1);
        dest.writeString(field2);
        dest.writeString(field3);
        dest.writeString(field4);

    }
    /* CREATOR creates an instance of com.example.dustin.choiceeats.ParClass from a Parcel */
    public static final Parcelable.Creator<ParClass> CREATOR = new
            Parcelable.Creator<ParClass>()
            {
                public ParClass createFromParcel(Parcel in) {
                    return new ParClass(in);
                }
                public ParClass[] newArray(int size) {
                    return new ParClass[size];
                }
            };
    /* A private constructor that creates an instance of com.example.dustin.choiceeats.ParClass from a Parcel */
    private ParClass(Parcel in) {
        /* Must be listed in same order as in writeToParcel() */
        field1 = in.readString();
        field2 = in.readString();
        field3 = in.readString();
        field4 = in.readString();
    }
}

