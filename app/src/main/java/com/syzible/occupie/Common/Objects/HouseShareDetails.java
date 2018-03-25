package com.syzible.occupie.Common.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HouseShareDetails extends ListingDetails {
    private int minTargetAge, maxTargetAge;
    private List<String> targetProfession;

    public HouseShareDetails(JSONObject o) throws JSONException {
        super(o);
        this.minTargetAge = o.getInt("min_target_age");
        this.maxTargetAge = o.getInt("max_target_age");

        targetProfession = new ArrayList<>();
        JSONArray professionsSoughtAfter = o.getJSONArray("target_tenant");
        for (int i = 0; i < professionsSoughtAfter.length(); i++)
            targetProfession.add(professionsSoughtAfter.getString(i));
    }

    public HouseShareDetails(String dwelling, String description, int leaseLengthMonths, List<String> targetTenant, int minTargetAge, int maxTargetAge, List<String> targetProfession) {
        super(dwelling, description, leaseLengthMonths, targetTenant);
        this.minTargetAge = minTargetAge;
        this.maxTargetAge = maxTargetAge;
        this.targetProfession = targetProfession;
    }

    public int getMinTargetAge() {
        return minTargetAge;
    }

    public int getMaxTargetAge() {
        return maxTargetAge;
    }

    public List<String> getTargetProfession() {
        return targetProfession;
    }
}