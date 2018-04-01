package com.syzible.occupie.Tenant.FindProperty.Listings.HouseShareListing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.occupie.Common.Objects.HouseShare;
import com.syzible.occupie.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

public class ViewHouseShareFragment extends Fragment implements ViewHouseShareView {

    private ViewHouseSharePresenter presenter;
    private FloatingActionButton favouriteFab;
    private boolean isFavourite = false;

    private DiscreteScrollView similarHomesCarousel;
    private HouseShare property;

    private TextView streetAddress, areaAddress, cityAddress, countyAddress, eircode;
    private TextView description, leaseLength, numBedrooms, numBathrooms, ber;
    private TextView deposit, rent;

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

        similarHomesCarousel = view.findViewById(R.id.property_listing_similar_homes);

        streetAddress = view.findViewById(R.id.street_address);
        areaAddress = view.findViewById(R.id.area_address);
        cityAddress = view.findViewById(R.id.city_address);
        countyAddress = view.findViewById(R.id.county_address);
        eircode = view.findViewById(R.id.eircode);

        description = view.findViewById(R.id.property_listing_description);
        ber = view.findViewById(R.id.property_listing_ber);
        leaseLength = view.findViewById(R.id.property_listing_lease_length);
        numBedrooms = view.findViewById(R.id.property_listing_bedroom_count);
        numBathrooms = view.findViewById(R.id.property_listing_bathroom_count);

        rent = view.findViewById(R.id.rent_status_bar);
        deposit = view.findViewById(R.id.deposit_status_bar);

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
        String streetLine = property.getAddress().getHouseNumber().equals("") ?
                property.getAddress().getStreet() :
                String.format("%s %s,", property.getAddress().getHouseNumber(), property.getAddress().getStreet());
        streetAddress.setText(streetLine);
        areaAddress.setText(property.getAddress().getArea());
        cityAddress.setText(property.getAddress().getCity());
        countyAddress.setText(property.getAddress().getCounty());
        eircode.setText(property.getAddress().getEircode());

        ber.setText(property.getListing().getBer());
        description.setText(property.getDetails().getDescription());
        leaseLength.setText(String.format("%s months", property.getDetails().getLeaseLengthMonths()));
        numBedrooms.setText(String.format("%s bedrooms", property.getBedrooms().size()));
        numBathrooms.setText(String.format("%s bathrooms", property.getBathrooms().size()));

        rent.setText(String.format("€%s", property.getBedrooms().get(0).getRent()));
        deposit.setText(String.format("€%s", property.getBedrooms().get(0).getDeposit()));
    }

    public ViewHouseShareFragment setProperty(HouseShare property) {
        this.property = property;
        return this;
    }
}
