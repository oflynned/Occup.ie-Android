package com.syzible.rentapp.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Property {
    private String type, landlordUuid;
    private int bedrooms, bathrooms;
    private Address address;
    private Details details;
    private Facilities facilities;
    private Listing listing;

    public Property(JSONObject o) throws JSONException {
        this.type = o.getString("type");
        this.landlordUuid = o.getString("landlord_uuid");
        this.bedrooms = o.getInt("bedrooms");
        this.bathrooms = o.getInt("bathrooms");
        this.address = new Address(o.getJSONObject("address"));
        this.details = new Details(o.getJSONObject("details"));
        this.facilities = new Facilities(o.getJSONObject("facilities"));
        this.listing = new Listing(o.getJSONObject("listing"));
    }

    public Property(String type, String landlordUuid, int bedrooms, int bathrooms, Address address, Details details, Facilities facilities, Listing listing) {
        this.type = type;
        this.landlordUuid = landlordUuid;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
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
}
