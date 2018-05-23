package com.example.dustin.mealrecord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.dustin.mealrecord.LocalDB.addMeal;
import static com.example.dustin.mealrecord.LocalDB.deleteMeal;
import static com.example.dustin.mealrecord.LocalDB.closeDB;
import static com.example.dustin.mealrecord.LocalDB.nameExists;
import static com.example.dustin.mealrecord.LocalDB.openDB;

public class Edit extends AppCompatActivity {

    String oldName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //WORTH NOTING: the below says that alot of these values are impossible to be null, yet still warns they may throw Null point Exceptions, investigate further

        if(getIntent().hasExtra("rest")){
            ParClass obj = getIntent().getExtras().getParcelable("rest");
            EditText objectName = (EditText) findViewById(R.id.edit_Name_EditText);
            if((obj.getField1().equals(null)));
            else
                objectName.setText(obj.getField1());
                oldName = obj.getField1();

            EditText objectPrep = (EditText) findViewById(R.id.edit_Preparer_EditText);
            if((obj.getField2().equals(null))) {
            }
            else
                objectPrep.setText(obj.getField2());

            EditText objectCal = (EditText) findViewById(R.id.edit_Calories_EditText);
            if((obj.getField3().equals(null))) {
            }
            else
                objectCal.setText(obj.getField3());

            EditText objectDesc = (EditText) findViewById(R.id.edit_Desc_EditText);
            if((obj.getField4().equals(null))) {
            }
            else
                objectDesc.setText(obj.getField4());
        }
    }


    public void returnToViewActivity(View view) {
        openDB(this);
        EditText toNameField = (EditText) findViewById(R.id.edit_Name_EditText);
        String mealNameData = toNameField.getText().toString();
        if(mealNameData.equals(null))
            return;

        if(!oldName.equals(mealNameData)) {
            if (nameExists(mealNameData)) {
                Toast.makeText(this, "there is already a meal with this name", Toast.LENGTH_LONG).show();
                toNameField.requestFocus();
                closeDB();
                return;
            }
        }

        EditText toPreparerField = (EditText) findViewById(R.id.edit_Preparer_EditText);
        String mealPreparerData = toPreparerField.getText().toString();

        EditText toCaloriesField = (EditText) findViewById(R.id.edit_Calories_EditText);
        String mealCaloriesData = toCaloriesField.getText().toString();
        //The following was supposed to make the default calorie value 0. It doesn't work, but the database seems to work even if its blank, its yet to be seen if I can force an
        //error by calling a meal when this field is left Null. Will work on functionality of this if such a situation arises.
        if((EditText) findViewById(R.id.edit_Calories_EditText)== null){
            mealCaloriesData = "0";
        }
        else{
            mealCaloriesData = toCaloriesField.getText().toString();
        }


        EditText toDescField = (EditText) findViewById(R.id.edit_Desc_EditText);
        String mealDescData = toDescField.getText().toString();

        Meal newMeal = new Meal(mealNameData, mealPreparerData, mealCaloriesData, mealDescData);


        deleteMeal(oldName); //Deletion must come first, to ensure that adding a duplicate name doesn't fail, and then the original is deleted

        if(addMeal(newMeal) == 1) {
            Toast.makeText(this, "there is already a meal with this name", Toast.LENGTH_LONG).show();
            toNameField.requestFocus();
            closeDB();
            return;
        }

        closeDB();
        Intent i = new Intent(this, ObjectList.class);
        finish();
        startActivity(i);
    }

}
