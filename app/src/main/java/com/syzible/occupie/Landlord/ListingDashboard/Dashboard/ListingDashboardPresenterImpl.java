package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import com.syzible.occupie.Common.Helpers.CallbackParameter;
import com.syzible.occupie.Common.Objects.Rental;

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
    public CallbackParameter<JSONArray> onRentalCallback() {
        return new CallbackParameter<JSONArray>() {
            @Override
            public void onSuccess(JSONArray a) {
                List<Rental> rentals = new ArrayList<>();
                for (int i = 0; i < a.length(); i++) {
                    try {
                        JSONObject o = a.getJSONObject(i);
                        rentals.add(new Rental(o));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                getNonNullableView().showRentalListings(rentals);
            }

            @Override
            public void onFailure() {

            }
        };
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
