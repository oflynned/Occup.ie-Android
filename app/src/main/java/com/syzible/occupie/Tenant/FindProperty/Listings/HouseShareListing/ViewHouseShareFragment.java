package com.syzible.occupie.Tenant.FindProperty.Listings.HouseShareListing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.occupie.Common.Objects.HouseShare;
import com.syzible.occupie.R;
import com.syzible.occupie.Tenant.FindProperty.Common.ImageAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class ViewHouseShareFragment extends Fragment implements ViewHouseShareView {

    private ViewHouseSharePresenter presenter;
    private FloatingActionButton favouriteFab;
    private boolean isFavourite = false;

    private HouseShare property;
    private TextView address, description, deposit, rent;

    public ViewHouseShareFragment() {

    }

    public static ViewHouseShareFragment getInstance(HouseShare property) {
        return new ViewHouseShareFragment().setProperty(property);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing_rental, container, false);

        favouriteFab = view.findViewById(R.id.view_listing_favourite_fab);
        favouriteFab.setOnClickListener((v) -> onFavouriteClick());

        address = view.findViewById(R.id.address);
        description = view.findViewById(R.id.property_listing_description);

        rent = view.findViewById(R.id.rent_status_bar);
        deposit = view.findViewById(R.id.deposit_status_bar);

        ImageAdapter adapter = new ImageAdapter(getContext(), property.getImages());
        ViewPager viewPager = view.findViewById(R.id.property_image);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        CirclePageIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        return view;
    }

    @Override
    public void onStart() {
        if (presenter == null)
            presenter = new ViewHouseSharePresenterImpl();

        presenter.attach(this);
        presenter.displayListingDetails();
        super.onStart();
    }

    @Override
    public void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void onFavouriteClick() {
        Log.d(getClass().getSimpleName(), String.valueOf(isFavourite));
        isFavourite = !isFavourite;
        int icon = isFavourite ? R.drawable.heart_filled : R.drawable.heart_outline;
        favouriteFab.setImageDrawable(ContextCompat.getDrawable(getContext(), icon));
    }

    @Override
    public void setListingDetails() {
        address.setText(property.getAddress().getTileAddress());
        description.setText(property.getDetails().getDescription());
        rent.setText(String.format("€%s", property.getBedrooms().get(0).getRent()));
        deposit.setText(String.format("€%s", property.getBedrooms().get(0).getDeposit()));
    }

    public ViewHouseShareFragment setProperty(HouseShare property) {
        this.property = property;
        return this;
    }
}
