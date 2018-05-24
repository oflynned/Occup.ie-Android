package com.syzible.occupie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.syzible.occupie.Common.Authentication.CreateAccountActivity;
import com.syzible.occupie.Common.FeatureFlags.FeatureFlagUtils;

/**
 * Poll feature flags and other stuff behind the scenes so that the oauth activity
 * doesn't appear for any amount of time when polling feature flags or other meta data
 */

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private static final int TRANSITION_TIME = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        FeatureFlagUtils.getRemoteFeatureFlags(this, this::onServerQueried);
    }

    private void onServerQueried() {
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.removeCallbacks(this);
                SplashActivity.this.finish();
                startActivity(new Intent(SplashActivity.this, CreateAccountActivity.class));
            }
        };

        handler.postDelayed(runnable, TRANSITION_TIME);
    }
}
