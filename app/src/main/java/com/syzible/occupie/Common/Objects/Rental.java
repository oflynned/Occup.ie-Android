package com.syzible.occupie.Common.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Rental {
    private String type, landlordUuid;
    private List<String> bathrooms, bedrooms;
    private Address address;
    private ListingDetails details;
    private Facilities facilities;
    private Listing listing;
    private List<String> images = new ArrayList<>();
    private int rent, deposit;

    public Rental(JSONObject o) throws Exception {
        this.type = o.getString("type");
        this.landlordUuid = o.getString("landlord_uuid");
        this.address = new Address(o.getJSONObject("address"));
        this.facilities = new Facilities(o.getJSONObject("facilities"));
        this.listing = new Listing(o.getJSONObject("listing"));
        this.rent = o.getJSONObject("listing").getInt("rent");
        this.deposit = o.getJSONObject("listing").getInt("deposit");

        bedrooms = new ArrayList<>();
        JSONArray bedroomList = o.getJSONArray("bedrooms");
        for (int i = 0; i < bedroomList.length(); i++) {
            bedrooms.add(bedroomList.getString(i));
        }

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

    public List<String> getBedrooms() {
        return bedrooms;
    }

    public List<String> getRentalBedrooms() {
        return bedrooms;
    }

    public int getRent() {
        return rent;
    }

    public int getDeposit() {
        return deposit;
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
