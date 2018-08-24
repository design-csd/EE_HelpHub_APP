package com.csdav.eehelphub;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        username =findViewById(R.id.login_username);
        password =findViewById(R.id.login_password);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void authenticateLogin(View view) {
        if (username.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "EXITING APP",
                    Toast.LENGTH_SHORT).show();
            finish();
            finishAffinity();
            System.exit(0);

        }

        else {
            Toast.makeText(getApplicationContext(), "Seems like you're not an admin!",
                    Toast.LENGTH_SHORT).show();

        }
    }


    public void cancelPressed(View view){
        Intent cancel=new Intent(Login.this,MainActivity.class);
        startActivity(cancel);
    }


    @Override
    public void onBackPressed() {
        //Simply Do noting!
    }

    private final List mBlockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN,
            KeyEvent.KEYCODE_VOLUME_UP));

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mBlockedKeys.contains(event.getKeyCode())) {
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }
}