package com.syzible;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.syzible.occupie.R;

/**
 * Poll feature flags and other stuff behind the scenes so that the oauth activity
 * doesn't appear for any amount of time when polling feature flags or other meta data
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }
}
