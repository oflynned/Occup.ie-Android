package com.syzible.occupie.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Bedroom {
    private String size;
    private int rent, deposit;

    public Bedroom(JSONObject o) throws JSONException {
        this.size = o.getString("size");
        this.rent = o.has("rent") ? o.getInt("rent") : -1;
        this.deposit = o.has("deposit") ? o.getInt("deposit") : -1;
    }

    public Bedroom(String size, int rent, int deposit) {
        this.size = size;
        this.rent = rent;
        this.deposit = deposit;
    }

    public int getRent() {
        return rent;
    }

    public int getDeposit() {
        return deposit;
    }

    public String getSize() {
        return size;
    }
}
