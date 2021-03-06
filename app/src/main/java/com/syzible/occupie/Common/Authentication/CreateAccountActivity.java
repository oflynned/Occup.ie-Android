package com.syzible.occupie.Common.Authentication;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.syzible.occupie.Common.FeatureFlags.FeatureFlag;
import com.syzible.occupie.Common.FeatureFlags.FeatureFlagDatabaseHelper;
import com.syzible.occupie.Common.FeatureFlags.FeatureFlagNotPresentException;
import com.syzible.occupie.Common.FeatureFlags.FeatureFlagUtils;
import com.syzible.occupie.Common.FeatureFlags.Flags;
import com.syzible.occupie.Common.Persistence.LocalPrefs;
import com.syzible.occupie.Common.Persistence.OAuthUtils;
import com.syzible.occupie.Common.Persistence.Target;
import com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordOAuthLogin.LandlordOAuthLoginFragment;
import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;
import com.syzible.occupie.Tenant.AuthenticateUserAccount.UserOAuthLogin.TenantOAuthLoginFragment;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_holder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupActivity();
    }

    private void setupActivity() {
        FeatureFlag featureFlag = null;
        try {
            featureFlag = FeatureFlagDatabaseHelper.getFeatureFlag(this, Flags.app_killswitch);
        } catch (FeatureFlagNotPresentException e) {
            e.printStackTrace();
        }

        if (featureFlag != null) {
            if (featureFlag.isEnabled()) {
                if (featureFlag.shouldKickSession())
                    for (LocalPrefs.Pref p : LocalPrefs.Pref.values())
                        LocalPrefs.purgePref(p, this);

                new AlertDialog.Builder(CreateAccountActivity.this)
                        .setTitle(featureFlag.getDialogTitle())
                        .setMessage(featureFlag.getDialogBody())
                        .setPositiveButton("OK", (dialog, which) -> {
                            CreateAccountActivity.this.finish();
                            System.exit(0);
                        })
                        .setCancelable(false)
                        .show();
            } else {
                if (OAuthUtils.hasExistingToken(this, Target.user)) {
                    finish();
                    startActivity(new Intent(this, MainActivity.class));
                } else if (OAuthUtils.hasExistingToken(this, Target.landlord)) {
                    finish();
                    startActivity(new Intent(this, MainActivity.class));
                }

                String target = getIntent().getStringExtra("target");
                if (target != null) {
                    if (target.equals(Target.user.name())) {
                        setFragmentBackstack(getFragmentManager(), TenantOAuthLoginFragment.getInstance());
                    } else if (target.equals(Target.landlord.name())) {
                        setFragmentBackstack(getFragmentManager(), LandlordOAuthLoginFragment.getInstance());
                    }
                } else {
                    setFragmentBackstack(getFragmentManager(), SelectCreateAccountFragment.getInstance());
                }
            }
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
