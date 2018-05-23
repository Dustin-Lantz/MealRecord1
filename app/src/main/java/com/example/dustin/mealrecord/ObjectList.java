package com.example.dustin.mealrecord;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.dustin.mealrecord.LocalDB.closeDB;
import static com.example.dustin.mealrecord.LocalDB.deleteMeal;
import static com.example.dustin.mealrecord.LocalDB.getMealID;
import static com.example.dustin.mealrecord.LocalDB.openDB;
import static com.example.dustin.mealrecord.R.id.object_List_Textfield;



public class ObjectList extends AppCompatActivity {

    Meal sender;
    ArrayList<Meal> mealList1 = new ArrayList<>();
    String passName;
    String passPreparer;
    String passCalories;
    String passDest;
    private final String TAG = " MEAL OBJECT LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_list);

    /*
    Meal ex1 = new Meal("example 1", "chef 1", "1", "bad, but not super bad, like pretty unbearably bad but still not the worst thing in the universe, you know?");
    Meal ex2 = new Meal("example 2", "chef 2", "2", "good");


    mealList1.add(ex1);
    mealList1.add(ex2);
    */
    ListView listView = (ListView) findViewById(object_List_Textfield);
    openDB(this);


        final ArrayList<Meal> list = LocalDB.getMealID();
    if (list == null) {
        Log.d(TAG, "Caught Error: List is Null");
        closeDB();
        return;

    }

    Log.d(TAG, "Starting List Adapter");
    MealListAdapter adapter = new MealListAdapter(this, android.R.layout.simple_list_item_1, LocalDB.getMealID());
    //MealListAdapter adapter = new MealListAdapter(this, android.R.layout.simple_list_item_1, mealList1);//hoping to get GetMealID working here, but its being a pain as always

         listView.setAdapter(adapter);

        AdapterView.OnItemClickListener handler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                sender = list.get(position);
            }
        };

        listView.setOnItemClickListener(handler);
        closeDB();
    }


    public void startEditor(View view) {
        if(sender != null) {
            openDB(this);
            LocalDB.getMeal(sender.getName());
            closeDB();
            passName = sender.getName();
            passPreparer = sender.getPreparer();
            passCalories = sender.getCalories();
            passDest = sender.getDescription();
            ParClass pc = new ParClass(passName, passPreparer, passCalories, passDest);
            Intent i = new Intent(this, Edit.class);
            i.putExtra("rest", pc);
            finish();
            startActivity(i);
        }
    }

    public void delete(View view){
        if(sender != null) {
            openDB(this);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Deletion");
            builder.setMessage("Are you sure you want to delete this meal?");
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String target = sender.getName();
                            deleteMeal(target);
                            closeDB();
                            finish();
                            startActivity(getIntent());
                       }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    closeDB();
                }
            });

        AlertDialog dialog = builder.create();
        dialog.show();



        }
    }
    /*      String target = sender.getName();
            openDB(this);
            deleteMeal(target);
            closeDB();
            finish();
            startActivity(getIntent());
    */

}
