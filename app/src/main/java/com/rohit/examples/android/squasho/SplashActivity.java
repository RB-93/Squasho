package com.rohit.examples.android.squasho;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    // #params TIMEOUT for Transition delay to be used Handler method
    private static final int TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Thread Handler method definition to synchronize thread with Activity to Activity transition.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcomeIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(welcomeIntent);

                /*
                    FADE_IN & FADE_OUT animation transition while going from one Activity to another Activity
                */
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                finish();       // Finished the current activity
            }
        }, TIMEOUT);
    }
}