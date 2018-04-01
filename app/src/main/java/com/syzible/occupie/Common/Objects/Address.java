package com.syzible.occupie.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Address {
    private String houseNumber, street, area, city, county, eircode;

    public Address(JSONObject o) throws JSONException {
        this.houseNumber = o.has("house_number") ? o.getString("house_number") : "";
        this.street = o.getString("street");
        this.area = o.getString("area");
        this.city = o.getString("city");
        this.county = o.getString("county");
        this.eircode = o.has("eircode") ? o.getString("eircode") : "";
    }

    public Address(String houseNumber, String street, String area, String city, String county, String eircode) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.area = area;
        this.city = city;
        this.county = county;
        this.eircode = eircode;
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

    public String getFullAddress() {
        if (houseNumber.equals("") || eircode.equals(""))
            return String.format("%s, %s, %s, %s", street, area, city, county);

        return String.format("%s, %s, %s, %s, %s, %s", houseNumber, street, area, city, county, eircode);
    }
}