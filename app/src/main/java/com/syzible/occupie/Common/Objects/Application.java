package com.syzible.occupie.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Application {

    private String userId, landlordId, listingId, status;
    private Date creationTime, lastUpdated;

    public Application(JSONObject o) throws JSONException {
        this.userId = o.getString("user_id");
        this.landlordId = o.getString("landlord_id");
        this.listingId = o.getString("listing_id");
        this.status = o.getString("status");
        this.creationTime = new Date(o.getString("creation_time"));
        this.lastUpdated = new Date(o.getString("last_updated"));
    }

    public Application(String userId, String landlordId, String listingId) {
        this.userId = userId;
        this.landlordId = landlordId;
        this.listingId = listingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getLandlordId() {
        return landlordId;
    }

    public String getListingId() {
        return listingId;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public JSONObject getPayload() throws JSONException {
        JSONObject o = new JSONObject();
        o.put("user_id", userId);
        o.put("landlord_id", landlordId);
        o.put("listing_id", listingId);
        return o;
    }
}
