package com.syzible.occupie.Tenant.FindProperty.Listings.HouseShareListing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.syzible.occupie.Common.Objects.HouseShare;
import com.syzible.occupie.R;
import com.syzible.occupie.Tenant.FindProperty.Common.ImageAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class ViewHouseShareFragment extends Fragment implements ViewHouseShareView, OnMapReadyCallback {

    private ViewHouseSharePresenter presenter;

    private HouseShare property;
    private TextView address, description, deposit, rent;

    public static final LatLng DUBLIN = new LatLng(53.347254, -6.259105);
    private static final float ZOOM = 14.0f;

    public ViewHouseShareFragment() {

    }

    public static ViewHouseShareFragment getInstance(HouseShare property) {
        return new ViewHouseShareFragment().setProperty(property);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing_rental, container, false);

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

        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.listing_map);
        mapFragment.getMapAsync(this);

        super.onStart();
    }

    @Override
    public void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void setListingDetails() {
        address.setText(property.getAddress().getTileAddress());
        description.setText(property.getDetails().getDescription());
        rent.setText(String.format("€%s", property.getListing().getRent()));
        deposit.setText(String.format("€%s", property.getListing().getDeposit()));
    }

    public ViewHouseShareFragment setProperty(HouseShare property) {
        this.property = property;
        return this;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DUBLIN, ZOOM));
        googleMap.addCircle(presenter.getUserCircle(DUBLIN));
    }
}
