package com.example.dustin.mealrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class LocalDB {
    // ERROR LOGGING TAG
    private static final String TAG = "LocalDB";
    // DB INFO
    private static final String DATABASE_NAME = "meals";
    private static final int DATABASE_VERSION = 1;
    // DB OBJECTS
    private static DatabaseHelper dBHelper = null;
    private static SQLiteDatabase db = null;

    // RETURN CODES FOR USER METHODS
    public static final int FAILURE = -1;
    public static final int SUCCESS = 0;
    public static final int NAME_ALREADY_EXISTS = 1;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(Meal_T.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version "
                    + oldVersion + " to " + newVersion
                    + ", which will destroy all old data!");
            _db.execSQL(Meal_T.DELETE_TABLE);
            onCreate(_db);
        }


    }

    public static void deleteAllEntries(){
        db.delete(Meal_T.TABLE_NAME, null, null);

    }

    public static void openDB(Context c)
    {
        if (db == null) {
            dBHelper = new DatabaseHelper(c);
            db = dBHelper.getWritableDatabase();
            db.execSQL(Meal_T.CREATE_TABLE);
        }
    }
    public static void closeDB()
    {
        db = null;
        dBHelper.close();
    }


    public static int addMeal(Meal meal)
    {
        //THIS WILL NEED TO BE REMOVED LATER, BUT IS HELPFUL RIGHT NOW
        assert(db != null);
        if (nameExists(meal.getName()))
            return NAME_ALREADY_EXISTS;

        //Need code to handle null values either here or in the Add Java
        ContentValues values = new ContentValues(4);
        values.put(Meal_T.NAME, meal.getName());
        values.put(Meal_T.PREPARER, meal.getPreparer());
        values.put(Meal_T.CALORIES, meal.getCalories());
        values.put(Meal_T.DESCRIPTION, meal.getDescription());

        long results = db.insert(Meal_T.TABLE_NAME, null, values);

        if(results == -1)
            return FAILURE;
        else
            return SUCCESS;
    }


    public static int deleteMeal(String name)
    {
        assert(db != null);

        Log.d(TAG, "Deleting: " + name);
        String[] whereArgs = new String[] { String.valueOf(name) };
        int res = db.delete(Meal_T.TABLE_NAME, Meal_T.NAME + " =?", whereArgs);  //used to be null

        if (res == 0) {
            Log.d(TAG, "no rows deleted from meal table");
        }
        return res;

    }

    public static Meal getMeal(String name)
    {
        assert (db != null);
        String query = Meal_T.GET_MEAL;

        String[] data = {name};

        Cursor c = db.rawQuery(query, data);
        if (c == null || c.getCount()== 0){
            return null;
        }
        c.moveToFirst();

        String mealName = c.getString(c.getColumnIndex(Meal_T.NAME));
        String preparer = c.getString(c.getColumnIndex(Meal_T.PREPARER));
        //int calories = c.getInt(Meal_T.CALORIES);
        String calories = c.getString(c.getColumnIndex(Meal_T.CALORIES));
        String description = c.getString(c.getColumnIndex(Meal_T.DESCRIPTION));
        c.close();

        return new Meal(mealName, preparer, calories, description);
    }

    //Used to populate list all at once
    public static ArrayList<Meal> getMealID(){

        String query = "SELECT * FROM " + Meal_T.TABLE_NAME;

        Log.d(TAG, "Getting Meals !!!!!!!!!!!!!!!!!!!");

        Cursor c =db.rawQuery(query, null);

        if(c==null){
            Log.d(TAG, "Cursor is NULLLLLLLLLL");
            return null;
        }else if (c.getCount() == 0){
            Log.d(TAG, "cursonr count is 00000000000");
            c.close();
            return null;
        }

        int len = c.getCount();
        int count = 0;

        ArrayList<Meal> restList = new ArrayList<Meal>();

        c.moveToFirst();
        while(count < len){
            String mealName = c.getString(c.getColumnIndex(Meal_T.NAME));
            String preparer = c.getString(c.getColumnIndex(Meal_T.PREPARER));
            //int calories = c.getInt(Meal_T.CALORIES);
            String calories = c.getString(c.getColumnIndex(Meal_T.CALORIES));
            String description = c.getString(c.getColumnIndex(Meal_T.DESCRIPTION));
            Meal r = new Meal(mealName, preparer, calories, description);
            if (r == null) {
                Log.d(TAG, "Restaurant is NULLLLLLLL");
            } else {
                restList.add(r);
            }
            c.moveToNext();
            count++;
        }
        c.close();
        return restList;
    }


    public static boolean nameExists(String name)
    {
        assert(db != null);
        String query = Meal_T.GET_MEAL;
        String[] data = {name};
        Cursor c = db.rawQuery(query, data);
        if (c == null || c.getCount() == 0) {
            return false;
        }
        else {
            c.close();
            return true;
        }

    }






}
