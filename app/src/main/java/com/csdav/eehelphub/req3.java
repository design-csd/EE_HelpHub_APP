package com.csdav.eehelphub;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class req3 extends AppCompatActivity {

    ImageButton back;
    ImageButton refresh;
    ImageButton home;
    WebView webview;
    Handler handler;
    Runnable r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req3);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        handler = new Handler();
        r = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent timer = new Intent(req3.this,MainActivity.class);
                webview.destroy();
                webview.clearHistory();
                webview.clearFormData();
                webview.clearCache(true);
                webview.clearMatches();
                finish();
                stopHandler();
                startActivity(timer);
            }
        };
        //startHandler();

        back = (ImageButton) findViewById(R.id.backButton);
        home = (ImageButton) findViewById(R.id.homeButton);
        refresh = (ImageButton) findViewById(R.id.resetButton);
        webview = (WebView) findViewById(R.id.webv3);

        startWebView("https://ee.co.uk/help/help-new/phones-and-devices?intcam=ON_EE_R_Help_Hub_phones-and-devices-2018-08-16");


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backInt = new Intent(req3.this,menuPage.class);
                webview.destroy();
                webview.clearHistory();
                webview.clearFormData();
                webview.clearCache(true);
                stopHandler();
                startActivity(backInt);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()) {
                    webview.goBack();
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.reload();
                webview.setWebViewClient(new WebViewClient() {
                    ProgressDialog progressDialog;
                    public void onLoadResource (WebView view, String url) {
                        if (progressDialog == null) {
                            // in standard case YourActivity.this
                            progressDialog = new ProgressDialog(req3.this);
                            progressDialog.setMessage("Refreshing...");
                            progressDialog.show();
                            progressDialog.getProgress();
                        }
                    }
                    public void onPageFinished(WebView view, String url) {
                        try{
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                                progressDialog.cancel();
                            }
                        }catch(Exception exception){
                            exception.printStackTrace();
                        }
                    }
                });
            }
        });

    }



    @SuppressLint("SetJavaScriptEnabled")
    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        webview.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(req3.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.show();
                        progressDialog.dismiss();
                        //progressDialog=null;
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        });

        // Javascript enabled on webview
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
//Do Code Here
// If want to block just return false
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_MENU) {
//Do Code Here
// If want to block just return false
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_HOME) {
//Do Code Here
// If want to block just return false
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
//Do Code Here
// If want to block just return false
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_SETTINGS) {
//Do Code Here
// If want to block just return false
            return false;
        }
        return super.onKeyDown(keyCode, event);
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
