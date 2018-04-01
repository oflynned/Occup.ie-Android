package com.syzible.occupie.Tenant.Favourites;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.occupie.R;

public class FavouritesFragment extends Fragment {

    public FavouritesFragment() {
    }

    public static FavouritesFragment getInstance() {
        return new FavouritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }
}
