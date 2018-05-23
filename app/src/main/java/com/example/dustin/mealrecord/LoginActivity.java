package com.example.dustin.mealrecord;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        */
    }
    public void goToMain(View view) {

        SharedPreferences mPrefs = getSharedPreferences("label", 0);
        String currentPassword = mPrefs.getString("tag", "password"); //"tag", "default_value_if_variable_not_found

        //int loginAttempt = passwordCheck();

        EditText toPassField = (EditText) findViewById(R.id.login_Password_Textfield);
        String attemptedPassword = toPassField.getText().toString();

        if (currentPassword.equals(attemptedPassword) ) {

            Toast.makeText(this, "password matched!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
        } else {
            //MAKE SURE TO DELETE TOAST ONCE TESTING IS DONE
            //Toast.makeText(this, "password did not match: " + currentPassword + " yours was: " + attemptedPassword, Toast.LENGTH_LONG).show();
            EditText toPasswordField = (EditText) findViewById(R.id.login_Password_Textfield);
            toPasswordField.setError(getString(R.string.password_error));
            toPasswordField.requestFocus();

        }
    }
}


