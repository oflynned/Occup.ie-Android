package com.syzible.occupie.Authentication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.syzible.occupie.Authentication.CreateUserAccount.UserOAuthLogin.OAuthLoginFragment;
import com.syzible.occupie.Common.Persistence.OAuthUtils;
import com.syzible.occupie.Common.Persistence.Target;
import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;

public class CreateAccountActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 3;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_holder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (OAuthUtils.hasExistingToken(this, Target.user)) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        if (getSupportActionBar() != null) {
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setFragment(getFragmentManager(), new OAuthLoginFragment());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public static void setFragment(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null)
            fragmentManager.beginTransaction()
                    .replace(R.id.signup_holder, fragment)
                    .addToBackStack(null)
                    .commit();
    }


    public static void setFragmentBackstack(FragmentManager fragmentManager, Fragment fragment) {

        if (fragmentManager != null)
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
    }
}
