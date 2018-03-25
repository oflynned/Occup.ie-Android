package com.syzible.occupie.Common.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListingDetails {
    private String dwelling, description;
    private int leaseLengthMonths;
    private List<String> targetTenant;

    public ListingDetails(JSONObject o) throws JSONException {
        this.dwelling = o.getString("dwelling");
        this.description = o.getString("description");
        this.leaseLengthMonths = o.getInt("lease_length_months");

        targetTenant = new ArrayList<>();
        JSONArray a = o.getJSONArray("target_tenant");
        for (int i = 0; i < a.length(); i++) targetTenant.add(a.getString(i));
    }

    public ListingDetails(String dwelling, String description, int leaseLengthMonths, List<String> targetTenant) {
        this.dwelling = dwelling;
        this.description = description;
        this.leaseLengthMonths = leaseLengthMonths;
        this.targetTenant = targetTenant;
    }

    public String getDwelling() {
        return dwelling;
    }

    public String getDescription() {
        return description;
    }

    public int getLeaseLengthMonths() {
        return leaseLengthMonths;
    }

    public List<String> getTargetTenant() {
        return targetTenant;
    }
}
