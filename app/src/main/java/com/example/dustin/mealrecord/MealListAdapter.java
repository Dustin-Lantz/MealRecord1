package com.example.dustin.mealrecord;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MealListAdapter extends ArrayAdapter<Meal>{

    private Context context;
    private int resource;
    private ArrayList<Meal> meals;

    public MealListAdapter(Context ctx, int res, ArrayList<Meal> restrnts){

        super(ctx, res, restrnts);
        this.context = ctx;
        this.resource = res;
        this.meals = restrnts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            /* Create a new View object */
            LayoutInflater li = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.meal_view, null);
        }
        /*Populating the View Object*/
        Meal meal = meals.get(position);
        if (meal != null) {
            TextView v = (TextView) view.findViewById(R.id.meal_Name_TextView);
            v.setText(meal.getName() + ": ");

            v = (TextView) view.findViewById(R.id.meal_Preparer_TextView);
            v.setText(meal.getPreparer() + " ");

            v = (TextView) view.findViewById(R.id.meal_Calories_TextView);
            v.setText(meal.getCalories());
            //Integer.toString to accomodate putting int into TextView

            v = (TextView) view.findViewById(R.id.meal_Desc_TextView);
            v.setText(" " + meal.getDescription());
        }

        /* Return the View object */
        return view;
    }




}
