package com.example.dustin.mealrecord;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.dustin.mealrecord.LocalDB.closeDB;
import static com.example.dustin.mealrecord.LocalDB.deleteAllEntries;
import static com.example.dustin.mealrecord.LocalDB.deleteMeal;
import static com.example.dustin.mealrecord.LocalDB.openDB;

public class TrueSettings extends AppCompatActivity {
    boolean deletion = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_settings);
        deletion = false;
    }

    public void confirm(View view){

        SharedPreferences mPrefs = getSharedPreferences("label", 0);

        EditText toPassField = (EditText) findViewById(R.id.settings_Password);
        String newPassword = toPassField.getText().toString();

        EditText toConfPassField = (EditText) findViewById(R.id.settings_Confirm_Pass);
        String newConfPassword = toConfPassField.getText().toString();

        if(newConfPassword.equals(newPassword)){
            SharedPreferences.Editor mEditor = mPrefs.edit();
            mEditor.putString("tag", newConfPassword).commit();
            Toast.makeText(this, "Password Changed", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, HomeActivity.class);
            finish();
            startActivity(i);

        }
        else{
            //Toast.makeText(this, "password did not match", Toast.LENGTH_LONG).show();
            EditText PassField = (EditText) findViewById(R.id.settings_Password);
            PassField.setError(getString(R.string.settings_error));
            PassField.requestFocus();


        }

    }


    public void deleteAll(View view){

        openDB(this);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Total Deletion");
        builder.setMessage("Warning: Are you sure you want to delete all meals? This action cannot be undone.");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deleteAllEntries();
                closeDB();
                deletion = true;

                goHome();
                //finish();

                //startActivity(getIntent());

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
        /*
        The following code was meant to force the screen to return to home with a toast explaining it was successful. However, this doesn't work.
        if(deletion){

            Toast.makeText(this, "All entries deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, HomeActivity.class);
            finish();
            startActivity(i);

        }
        */


    }

    public void goHome(){
        Toast.makeText(this, "All entries deleted", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, HomeActivity.class);
        finish();
        startActivity(i);
    }
}
