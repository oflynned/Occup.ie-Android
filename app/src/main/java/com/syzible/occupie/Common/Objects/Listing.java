package com.syzible.occupie.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Listing {
    private Date created, expires;
    private String plan, status;
    private int rent, deposit;

    public Listing(JSONObject o) throws JSONException {
        this.created = new Date();
        this.expires = new Date();
        this.plan = o.getString("plan");
        this.status = o.getString("status");
        this.rent = o.getInt("rent");
        this.deposit = o.getInt("deposit");
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

    public int getRent() {
        return rent;
    }

    public int getDeposit() {
        return deposit;
    }
}
