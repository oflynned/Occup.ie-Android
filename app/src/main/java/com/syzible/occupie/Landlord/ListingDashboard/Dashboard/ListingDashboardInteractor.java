package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import android.content.Context;

import com.syzible.occupie.Common.Helpers.CallbackParameter;
import com.syzible.occupie.Common.Objects.HouseShare;
import com.syzible.occupie.Common.Objects.Rental;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public interface ListingDashboardInteractor {
    void fetchListings(Context context, String endpoint, CallbackParameter<JSONObject> callback);
}
