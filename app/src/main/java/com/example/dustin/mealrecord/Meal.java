package com.example.dustin.mealrecord;

import android.os.Parcel;
import android.os.Parcelable;



public class Meal {

    private String name;
    private String preparer;
    private String calories;
    private String description;

    public Meal(String nameinput, String preparerinput, String calorieinput, String descinput) {
        name = nameinput;
        preparer = preparerinput;
        calories = calorieinput;
        description = descinput;

    }

    //getters
    public String getName() {
        return name;
    }

    public String getPreparer() {return preparer;}

    public String getCalories() {return calories;}

    public String getDescription() { return description; }


    //Setters
    public void setName(String s){ name = s; }

    public void setPreparer(String s){ preparer = s;}

    public void setCalories(String i) {calories = i;}

    public void setDescription(String s){ description = s; }



    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(preparer);
        dest.writeString(calories);
        dest.writeString(description);

    }

    public static final Parcelable.Creator<Meal> CREATOR = new
            Parcelable.Creator<Meal>()
            {
                public Meal createFromParcel(Parcel in) { return new Meal(in); }

                public Meal[] newArray(int size){ return new Meal[size];}

            };

    private Meal(Parcel in) {
        name = in.readString();
        preparer = in.readString();
        calories = in.readString();
        description = in.readString();
    }
}


