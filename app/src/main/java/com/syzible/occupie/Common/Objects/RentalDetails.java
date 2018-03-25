package com.syzible.occupie.Common.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RentalDetails extends ListingDetails{
    public RentalDetails(JSONObject o) throws JSONException {
        super(o);
    }

    public RentalDetails(String dwelling, String description, int leaseLengthMonths, List<String> targetTenant) {
        super(dwelling, description, leaseLengthMonths, targetTenant);
    }
}