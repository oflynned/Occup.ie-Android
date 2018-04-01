package com.syzible.occupie.Common.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Property {
    private String type, landlordUuid;
    private List<Bedroom> houseShareBedrooms;
    private List<String> bathrooms, rentalBedrooms;
    private Address address;
    private ListingDetails details;
    private Facilities facilities;
    private Listing listing;
    private List<String> images = new ArrayList<>();

    public Property(JSONObject o) throws Exception {
        this.type = o.getString("type");
        this.landlordUuid = o.getString("landlord_uuid");
        this.address = new Address(o.getJSONObject("address"));
        this.facilities = new Facilities(o.getJSONObject("facilities"));
        this.listing = new Listing(o.getJSONObject("listing"));

        rentalBedrooms = new ArrayList<>();
        houseShareBedrooms = new ArrayList<>();
        JSONArray bedroomList = o.getJSONArray("bedrooms");
        for (int i = 0; i < bedroomList.length(); i++) {
            if (bedroomList.get(i) instanceof String) {
                rentalBedrooms.add(bedroomList.getString(i));
            } else {
                Bedroom bedroom = new Bedroom(bedroomList.getJSONObject(i));
                houseShareBedrooms.add(bedroom);
            }
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

    public List<?> getBedrooms() {
        return type.equals("house_share") ? houseShareBedrooms : rentalBedrooms;
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
