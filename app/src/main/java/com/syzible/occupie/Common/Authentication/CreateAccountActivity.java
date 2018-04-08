package com.syzible.occupie.Common.Authentication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.syzible.occupie.Common.Persistence.OAuthUtils;
import com.syzible.occupie.Common.Persistence.Target;
import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;
import com.syzible.occupie.Tenant.CreateUserAccount.UserOAuthLogin.TenantOAuthLoginFragment;

public class CreateAccountActivity extends AppCompatActivity {

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

        String target = getIntent().getStringExtra("target");
        if (target != null && target.equals(Target.user.name())) {
            setFragment(getFragmentManager(), TenantOAuthLoginFragment.getInstance());
        } else {
            setFragment(getFragmentManager(), SelectCreateAccountFragment.getInstance());
        }
    }

    @Override
    public void onBackPressed() {
        int fragmentSize = getFragmentManager().getBackStackEntryCount();
        if (fragmentSize == 1) {
            finish();
        } else {
            if (getFragmentManager().getBackStackEntryCount() > 1) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    public static void setFragment(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().replace(R.id.signup_holder, fragment).commit();
        }
    }

    public static void setFragmentBackstack(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.signup_holder, fragment)
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
