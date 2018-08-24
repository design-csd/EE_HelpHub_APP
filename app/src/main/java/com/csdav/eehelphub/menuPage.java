package com.csdav.eehelphub;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class menuPage extends AppCompatActivity {

    VideoView bgvideo;
    ImageView req1, req2, req3, req4, req5, req6;
    ImageView login;
    Handler handler;
    Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_menu_page);

        handler = new Handler();
        r = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent timer = new Intent(menuPage.this,MainActivity.class);
                stopHandler();
                startActivity(timer);
            }
        };

        bgvideo = findViewById(R.id.videobg2);
        req1 = findViewById(R.id.button1);
        req2 = findViewById(R.id.button2);
        req3 = findViewById(R.id.button3);
        req4 = findViewById(R.id.button4);
        req5 = findViewById(R.id.button5);
        req6 = findViewById(R.id.button6);
        login = findViewById(R.id.title);

        Uri uri = Uri.parse("android.resource://com.csdav.eehelphub/"+R.raw.video_bg2);
        bgvideo.setVideoURI(uri);
        bgvideo.requestFocus();
        bgvideo.start();

        bgvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        login.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent log = new Intent(menuPage.this,Login.class);
                // TODO Auto-generated method stub
                startActivity(log);
                return true;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
        bgvideo.pause();
    }
    @Override
    public void onResume() {
        super.onResume();
        bgvideo.start(); //Or use resume() if it doesn't work. I'm not sure
    }



    public void blink1(View view){
        ImageView image = (ImageView)findViewById(R.id.button1);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
        Intent req1int = new Intent(menuPage.this,req1.class);
        startActivity(req1int);
    }

    public void blink2(View view){
        ImageView image = (ImageView)findViewById(R.id.button2);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
        Intent req1int = new Intent(menuPage.this,req2.class);
        startActivity(req1int);
    }

    public void blink3(View view){
        ImageView image = (ImageView)findViewById(R.id.button3);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
        Intent req1int = new Intent(menuPage.this,req3.class);
        startActivity(req1int);
    }

    public void blink4(View view){
        ImageView image = (ImageView)findViewById(R.id.button4);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
        Intent req1int = new Intent(menuPage.this,req4.class);
        startActivity(req1int);
    }

    public void blink5(View view){
        ImageView image = (ImageView)findViewById(R.id.button5);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
        Intent req1int = new Intent(menuPage.this,req5.class);
        startActivity(req1int);
    }

    public void blink6(View view){
        ImageView image = (ImageView)findViewById(R.id.button6);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
        Intent req1int = new Intent(menuPage.this,req6.class);
        startActivity(req1int);
    }



    @Override
    public void onUserInteraction() {
        // TODO Auto-generated method stub
        super.onUserInteraction();
        stopHandler();//stop first and then start
        startHandler();
    }
    public void stopHandler() {
        handler.removeCallbacks(r);
    }
    public void startHandler() {
        handler.postDelayed(r, 60000);
    }



    @Override
    public void onBackPressed() {
        Intent back=new Intent(menuPage.this,MainActivity.class);
        startActivity(back);
    }

    private final List<Integer> mBlockedKeys = new ArrayList<>(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN,
            KeyEvent.KEYCODE_VOLUME_UP));

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mBlockedKeys.contains(event.getKeyCode())) {
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }



}
