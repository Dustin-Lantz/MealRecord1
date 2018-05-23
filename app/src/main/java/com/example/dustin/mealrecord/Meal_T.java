package com.example.dustin.mealrecord;

import android.provider.BaseColumns;

public class Meal_T implements BaseColumns {
    private Meal_T(){}
    public static final String TABLE_NAME = "meals";

    public static final String NAME = "meal_name";

    public static final String PREPARER = "meal_preparer";

    //THIS MAY BE AN ISSUE LATER, NEED TO RESEARCH SQLITE ANDROID WITH INTS, NO EXAMPLES RN
    //public static final int CALORIES = 0;
    public static final String CALORIES = "calories";

    public static final String DESCRIPTION = "description";

    //SQL Statements
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + _ID + " INTEGER PRIMARY KEY, "
                    + NAME + " TEXT NOT NULL UNIQUE, "
                    + PREPARER + " TEXT NOT NULL, "
                    + CALORIES + " INTEGER NOT NULL, "
                    + DESCRIPTION + " TEXT NOT NULL)";

    public static final String DELETE_TABLE =
            "DELETE TABLE IF EXISTS " + TABLE_NAME;

    public static final String GET_MEAL =
            "SELECT *"
            + " FROM " + TABLE_NAME
            + " WHERE " + NAME + "= ?";

    public static final String GET_PREPARER =
            "SELECT " + PREPARER
                    + " FROM " + TABLE_NAME
                    + " WHERE " + NAME + "= ?";

    public static final String GET_CALORIES =
            "SELECT " + CALORIES
                    + " FROM " + TABLE_NAME
                    + " WHERE " + NAME + "= ?";

    public static final String GET_DESCRIPTION =
            "SELECT " + DESCRIPTION
                    + " FROM " + TABLE_NAME
                    + " WHERE " + NAME + "= ?";
}
