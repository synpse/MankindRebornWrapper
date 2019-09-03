package com.mankind.reborn.wrapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    // Single variable for our web viewer
    private WebView myWebView;

    // On create method
    protected void onCreate(Bundle savedInstanceState) {
        // Create
        super.onCreate(savedInstanceState);

        // Get decor view
        View decorView = getWindow().getDecorView();
        // Hide the status bar
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        // Set our content view
        setContentView(R.layout.activity_main);

        // Find web viewer component
        myWebView = findViewById(R.id.webView);

        // Get our web settings
        WebSettings webSettings = myWebView.getSettings();

        // Enable javascript execution
        webSettings.setJavaScriptEnabled(true);

        // Hide scroll bars
        myWebView.setVerticalScrollBarEnabled(false);
        myWebView.setHorizontalScrollBarEnabled(false);

        // Disable horizontal scrolling
        myWebView.setOnTouchListener(new View.OnTouchListener() {
            float m_downX;
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getPointerCount() > 1) {
                    //Multi touch detected
                    return true;
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // save the x
                        m_downX = event.getX();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {
                        // set x so that it doesn't move
                        event.setLocation(m_downX, event.getY());
                        break;
                    }

                }
                return false;
            }
        });

        // Load MR website
        myWebView.loadUrl("https://mankindreborn.com/");
        myWebView.setWebViewClient(new WebViewClient());
    }

    // On back pressed method
    @Override
    public void onBackPressed() {
        // Allow use of back button
        // If we are able to go back
        if (myWebView.canGoBack()) {
            // Go back
            myWebView.goBack();
        }
        else {
            // Do nothing
            super.onBackPressed(); // This is not really needed but whatever
        }
    }
}
