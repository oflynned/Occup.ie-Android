package com.syzible.occupie.Common.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Property {
    private String type, landlordUuid;
    private int bedrooms, bathrooms;
    private Address address;
    private ListingDetails details;
    private Facilities facilities;
    private Listing listing;
    private List<String> images = new ArrayList<>();

    public Property(JSONObject o) throws Exception {
        this.type = o.getString("type");
        this.landlordUuid = o.getString("landlord_uuid");
        this.bedrooms = o.getInt("bedrooms");
        this.bathrooms = o.getInt("bathrooms");
        this.address = new Address(o.getJSONObject("address"));
        this.facilities = new Facilities(o.getJSONObject("facilities"));
        this.listing = new Listing(o.getJSONObject("listing"));

        JSONObject details = o.getJSONObject("details");
        switch (type) {
            case "rental":
                this.details = new RentalDetails(details);
                break;
            case "house_share":
                this.details = new HouseShareDetails(details);
                break;
            default:
                throw new Exception("unknown_listing_type");
        }

        JSONArray urls = o.getJSONArray("images");
        for (int i = 0; i < urls.length(); i++) {
            images.add(urls.getString(i));
        }
    }

    public Property(String type, String landlordUuid, int bedrooms, int bathrooms, Address address,
                    ListingDetails details, Facilities facilities, Listing listing, List<String> images) {
        this.type = type;
        this.landlordUuid = landlordUuid;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.address = address;
        this.details = details;
        this.facilities = facilities;
        this.listing = listing;
        this.images = images;
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

    public ListingDetails getDetails() {
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
