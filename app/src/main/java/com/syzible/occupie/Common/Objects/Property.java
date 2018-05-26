package com.syzible.occupie.Common.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * all types of property (house share, rentals, etc) should extend property type for collation on the landlord dashboard
 * also would be nice to dry some model code
 */

public class Property {
    private String id, type;
    private Address address;
    private Listing listing;
    private List<String> images = new ArrayList<>();

    public Property(JSONObject o) throws JSONException {
        this.id = o.getString("_id");
        this.type = o.getString("type");
        this.address = new Address(o.getJSONObject("address"));
        this.listing = new Listing(o.getJSONObject("listing"));

        JSONArray urls = o.getJSONArray("images");
        for (int i = 0; i < urls.length(); i++) {
            images.add(urls.getString(i));
        }
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getFormattedType() {
        return type.replace("_", " ");
    }

    public Address getAddress() {
        return address;
    }

    public Listing getListing() {
        return listing;
    }

    public List<String> getImages() {
        return images;
    }
}
