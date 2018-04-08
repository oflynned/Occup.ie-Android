package com.syzible.occupie.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Address {
    private String apartmentNumber, houseNumber, street, area, city, county, eircode;

    public Address(JSONObject o) throws JSONException {
        this.apartmentNumber = (o.has("apartment_number") && !o.isNull("apartment_number")) ? o.getString("apartment_number") : null;
        this.houseNumber = o.has("house_number") ? o.getString("house_number") : "";
        this.eircode = o.has("eircode") ? o.getString("eircode") : "";
        this.street = o.getString("street");
        this.area = o.getString("area");
        this.city = o.getString("city");
        this.county = o.getString("county");
    }

    public Address(String apartmentNumber, String houseNumber, String street, String area, String city, String county, String eircode) {
        this.apartmentNumber = apartmentNumber;
        this.houseNumber = houseNumber;
        this.street = street;
        this.area = area;
        this.city = city;
        this.county = county;
        this.eircode = eircode;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getEircode() {
        return eircode;
    }

    public String getTileAddress() {
        return String.format("%s, %s, %s", street, area, city);
    }
}