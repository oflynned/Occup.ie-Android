package com.syzible.rentapp.Common.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Property {
    private String type, landlordUuid;
    private int bedrooms, bathrooms;
    private Address address;
    private Details details;
    private Facilities facilities;
    private Listing listing;
    private List<String> images = new ArrayList<>();

    public Property(JSONObject o) throws JSONException {
        this.type = o.getString("type");
        this.landlordUuid = o.getString("landlord_uuid");
        this.bedrooms = o.getInt("bedrooms");
        this.bathrooms = o.getInt("bathrooms");
        this.address = new Address(o.getJSONObject("address"));
        this.details = new Details(o.getJSONObject("details"));
        this.facilities = new Facilities(o.getJSONObject("facilities"));
        this.listing = new Listing(o.getJSONObject("listing"));

        JSONArray urls = o.getJSONArray("images");
        for (int i = 0; i < urls.length(); i++) {
            images.add(urls.getString(i));
        }
    }

    public Property(String type, String landlordUuid, int bedrooms, int bathrooms, List<String> images,
                    Address address, Details details, Facilities facilities, Listing listing) {
        this.type = type;
        this.landlordUuid = landlordUuid;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.images = images;
        this.address = address;
        this.details = details;
        this.facilities = facilities;
        this.listing = listing;
    }

    public String getType() {
        return type;
    }

    public String getLandlordUuid() {
        return landlordUuid;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public Address getAddress() {
        return address;
    }

    public Details getDetails() {
        return details;
    }

    public Facilities getFacilities() {
        return facilities;
    }

    public Listing getListing() {
        return listing;
    }

    public List<String> getImages() {
        return images;
    }
}
