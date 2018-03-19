package com.syzible.rentapp.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Details {
    private String dwelling, description, caveats;
    private int leaseLength, minTargetAge, maxTargetAge;
    private ArrayList<String> targetSex, targetProfession;

    public Details(JSONObject o) throws JSONException {
        this.dwelling = o.getString("dwelling");
        this.description = o.getString("description");
        this.caveats = o.getString("stayCaveats");
        this.leaseLength = o.getInt("lease_length_months");
        this.minTargetAge = o.getInt("min_target_age");
        this.maxTargetAge = o.getInt("max_target_age");

    }

    public Details(String dwelling, String description, String caveats, int leastLength, int minTargetAge, int maxTargetAge) {
        this.dwelling = dwelling;
        this.description = description;
        this.caveats = caveats;
        this.leaseLength = leastLength;
        this.minTargetAge = minTargetAge;
        this.maxTargetAge = maxTargetAge;
    }

    public String getDwelling() {
        return dwelling;
    }

    public String getDescription() {
        return description;
    }

    public String getCaveats() {
        return caveats;
    }

    public int getLeaseLength() {
        return leaseLength;
    }

    public int getMinTargetAge() {
        return minTargetAge;
    }

    public int getMaxTargetAge() {
        return maxTargetAge;
    }
}
