package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import com.syzible.occupie.Common.Helpers.CallbackParameter;
import com.syzible.occupie.Common.Objects.Property;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListingDashboardPresenterImpl implements ListingDashboardPresenter {

    private ListingDashboardView view;

    @Override
    public void attach(ListingDashboardView listingDashboardView) {
        this.view = listingDashboardView;
    }

    @Override
    public ListingDashboardView getNonNullableView() throws IllegalStateException {
        if (view == null)
            throw new IllegalStateException();

        return view;
    }

    @Override
    public CallbackParameter<JSONObject> onListingCallback() {
        return new CallbackParameter<JSONObject>() {
            @Override
            public void onSuccess(JSONObject o) throws JSONException {
                JSONArray activeListings = o.getJSONArray("active");
                JSONArray pausedListings = o.getJSONArray("paused");
                JSONArray expiredListings = o.getJSONArray("expired");

                if (activeListings.length() + pausedListings.length() + expiredListings.length() == 0) {
                    getNonNullableView().setEmptyLayout();
                    return;
                }

                List<Property> activeProperties = castPropertyObject(activeListings);
                List<Property> pausedProperties = castPropertyObject(pausedListings);
                List<Property> expiredProperties = castPropertyObject(expiredListings);
                getNonNullableView().showPropertyListings(activeProperties, pausedProperties, expiredProperties);
            }

            @Override
            public void onFailure() {
                getNonNullableView().setErrorLayout();
            }
        };
    }

    @Override
    public void detach() {
        this.view = null;
    }


    private List<Property> castPropertyObject(JSONArray a) {
        List<Property> properties = new ArrayList<>();

        for (int i = 0; i < a.length(); i++) {
            try {
                JSONObject property = a.getJSONObject(i);
                properties.add(new Property(property));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return properties;
    }
}
