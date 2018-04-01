package com.syzible.occupie.Common.Authentication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.syzible.occupie.Tenant.CreateUserAccount.UserOAuthLogin.TenantOAuthLoginFragment;
import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;

public class SelectCreateAccountFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_create_account, container, false);

        Button createLandlordAccount = view.findViewById(R.id.create_landlord_account);
        createLandlordAccount.setOnClickListener(v -> CreateAccountActivity.setFragment(getFragmentManager(), null));

        Button createTenantAccount = view.findViewById(R.id.create_user_account);
        createTenantAccount.setOnClickListener(v -> CreateAccountActivity.setFragment(getFragmentManager(), TenantOAuthLoginFragment.getInstance()));

        Button continueWithoutLogin = view.findViewById(R.id.continue_without_account);
        continueWithoutLogin.setOnClickListener(v -> startActivity(new Intent(getActivity(), MainActivity.class)));

        return view;
    }
}
