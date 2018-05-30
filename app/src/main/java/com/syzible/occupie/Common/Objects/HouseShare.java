package com.syzible.occupie.Common.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HouseShare {
    private String type, landlordUuid, bedrooms, propertyUuid;
    private List<String> bathrooms;
    private Address address;
    private ListingDetails details;
    private Facilities facilities;
    private Listing listing;
    private List<String> images = new ArrayList<>();

    public HouseShare(JSONObject o) throws Exception {
        this.type = o.getString("type");
        this.propertyUuid = o.getString("_id");
        this.landlordUuid = o.getString("landlord_uuid");
        this.address = new Address(o.getJSONObject("address"));
        this.facilities = new Facilities(o.getJSONObject("facilities"));
        this.listing = new Listing(o.getJSONObject("listing"));
        this.bedrooms = o.getString("bedrooms");

        bathrooms = new ArrayList<>();
        JSONArray bathroomList = o.getJSONArray("bathrooms");
        for (int i = 0; i < bathroomList.length(); i++)
            bathrooms.add(bathroomList.getString(i));

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

    public String getPropertyUuid() {
        return propertyUuid;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public String getType() {
        return type;
    }

    public String getLandlordUuid() {
        return landlordUuid;
    }

    public List<String> getBathrooms() {
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
