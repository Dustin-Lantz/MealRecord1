package com.example.dustin.mealrecord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startObjectList(View view) {
        Intent i = new Intent(this, ObjectList.class);
        startActivity(i);
    }

    public void startMealCreator(View view) {
        Intent i = new Intent(this, MealCreator.class);
        startActivity(i);
    }

    public void startSettings(View view) {
        Intent i = new Intent(this, TrueSettings.class);
        startActivity(i);
    }

    public void startAboutApp(View view) {
        Intent i = new Intent(this, AboutApp.class);
        startActivity(i);
    }
}
