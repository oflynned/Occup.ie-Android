package com.syzible.occupie.Tenant.Applications;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.occupie.R;

public class NoApplicationsFragment extends Fragment {

    public NoApplicationsFragment() {
    }

    public static NoApplicationsFragment getInstance() {
        return new NoApplicationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_no_applications, container, false);
    }
}
