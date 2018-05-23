package com.example.dustin.mealrecord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.dustin.mealrecord.LocalDB.addMeal;
import static com.example.dustin.mealrecord.LocalDB.closeDB;
import static com.example.dustin.mealrecord.LocalDB.openDB;

public class MealCreator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_creator);
    }


    public void returnToParentActivity(View view) {

        EditText toNameField = (EditText) findViewById(R.id.add_Name_EditText);
        String mealNameData = toNameField.getText().toString();
        if(mealNameData.equals(null))
            return;

        EditText toPreparerField = (EditText) findViewById(R.id.add_Preparer_EditText);
        String mealPreparerData = toPreparerField.getText().toString();

        EditText toCaloriesField = (EditText) findViewById(R.id.add_Calories_EditText);
        String mealCaloriesData = toCaloriesField.getText().toString();
        //The following was supposed to make the default calorie value 0. It doesn't work, but the database seems to work even if its blank, its yet to be seen if I can force an
        //error by calling a meal when this field is left Null. Will work on functionality of this if such a situation arises.
        if((EditText) findViewById(R.id.add_Calories_EditText)== null){
             mealCaloriesData = "0";
        }
        else{
             mealCaloriesData = toCaloriesField.getText().toString();
        }


        EditText toDescField = (EditText) findViewById(R.id.add_Desc_EditText);
        String mealDescData = toDescField.getText().toString();

        Meal newMeal = new Meal(mealNameData, mealPreparerData, mealCaloriesData, mealDescData);
        openDB(this);

        if(addMeal(newMeal) == 1) {
            Toast.makeText(this, "there is already a meal with this name", Toast.LENGTH_LONG).show();
            toNameField.requestFocus();
            return;
        }
        closeDB();

        finish();
    }

}
