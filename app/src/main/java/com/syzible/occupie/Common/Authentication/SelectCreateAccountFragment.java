package com.syzible.occupie.Common.Authentication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.occupie.Common.FeatureFlags.FeatureFlag;
import com.syzible.occupie.Common.FeatureFlags.FeatureFlagDatabaseHelper;
import com.syzible.occupie.Common.FeatureFlags.FeatureFlagNotPresentException;
import com.syzible.occupie.Common.FeatureFlags.FeatureFlagUtils;
import com.syzible.occupie.Common.FeatureFlags.Flags;
import com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordOAuthLogin.LandlordOAuthLoginFragment;
import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;
import com.syzible.occupie.Tenant.AuthenticateUserAccount.UserOAuthLogin.TenantOAuthLoginFragment;

import mehdi.sakout.fancybuttons.FancyButton;

public class SelectCreateAccountFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_create_account, container, false);

        FancyButton createLandlordAccount = view.findViewById(R.id.create_landlord_account);
        createLandlordAccount.setOnClickListener(v -> {
            try {
                FeatureFlag featureFlag = FeatureFlagDatabaseHelper.getFeatureFlag(getActivity(), Flags.landlord_login);
                if (featureFlag.isEnabled()) {
                    CreateAccountActivity.setFragmentBackstack(getFragmentManager(), LandlordOAuthLoginFragment.getInstance());
                } else {
                    Snackbar.make(view, featureFlag.getDialogBody(), Snackbar.LENGTH_LONG).show();
                }
            } catch (FeatureFlagNotPresentException e) {
                e.printStackTrace();
            }
        });

        FancyButton createTenantAccount = view.findViewById(R.id.create_user_account);
        createTenantAccount.setOnClickListener(v -> {
            try {
                FeatureFlag featureFlag = FeatureFlagDatabaseHelper.getFeatureFlag(getActivity(), Flags.tenant_login);
                if (FeatureFlagUtils.isFlagEnabled(getActivity(), Flags.tenant_login)) {
                    CreateAccountActivity.setFragmentBackstack(getFragmentManager(), TenantOAuthLoginFragment.getInstance());
                } else {
                    Snackbar.make(view, featureFlag.getDialogBody(), Snackbar.LENGTH_LONG).show();
                }
            } catch (FeatureFlagNotPresentException e) {
                e.printStackTrace();
            }
        });

        TextView continueWithoutLogin = view.findViewById(R.id.continue_without_account);
        continueWithoutLogin.setOnClickListener(v -> {
            getActivity().finish();
            startActivity(new Intent(getActivity(), MainActivity.class));
        });

        return view;
    }

    public static SelectCreateAccountFragment getInstance() {
        return new SelectCreateAccountFragment();
    }
}
