package com.syzible.rentapp.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Listing {
    private int rent;
    private Date created, expires;
    private String plan, status, ber;
    private boolean isOwnerOccupied, isFurnished;

    public Listing(JSONObject o) throws JSONException {
        this.rent = o.getInt("rent");
        this.created = new Date();
        this.expires = new Date();
        this.plan = o.getString("plan");
        this.status = o.getString("status");
        this.ber = o.getString("ber");
        this.isOwnerOccupied = o.getBoolean("owner_occupied");
        this.isFurnished = o.getBoolean("furnished");
    }

    public Listing(Date created, Date expires, String plan, String status, String ber, boolean isOwnerOccupied, boolean isFurnished) {
        this.created = created;
        this.expires = expires;
        this.plan = plan;
        this.status = status;
        this.ber = ber;
        this.isOwnerOccupied = isOwnerOccupied;
        this.isFurnished = isFurnished;
    }

    public int getRent() {
        return rent;
    }

    public Date getCreated() {
        return created;
    }

    public Date getExpires() {
        return expires;
    }

    public String getPlan() {
        return plan;
    }

    public String getStatus() {
        return status;
    }

    public String getBer() {
        return ber;
    }

    public boolean isOwnerOccupied() {
        return isOwnerOccupied;
    }

    public boolean isFurnished() {
        return isFurnished;
    }
}
