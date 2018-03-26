package com.syzible.occupie.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Landlord {

    private String guid, forename, surname, sex, email, profilePicture, phoneNumber, creationTime;
    private boolean identityVerified, phoneVerified;
    private int tosVersionAccepted, privacyVersionAccepted;
    private Date dob;

    public Landlord(JSONObject o) throws JSONException {
        this.guid = o.getString("_id");

        JSONObject details = o.getJSONObject("details");
        this.forename = details.getString("forename");
        this.surname = details.getString("surname");
        this.sex = details.getString("sex");
        this.dob = new Date(details.getString("dob"));
        this.email = details.getString("email");
        this.profilePicture = details.getString("profile_picture");
        this.phoneNumber = details.getString("phone_number");

        JSONObject meta = o.getJSONObject("meta");
        this.identityVerified = meta.getBoolean("identity_verified");
        this.phoneVerified = meta.getBoolean("phone_verified");
        this.creationTime = meta.getString("creation_time");
        this.tosVersionAccepted = meta.getInt("tos_version_accepted");
        this.privacyVersionAccepted = meta.getInt("privacy_version_accepted");
    }


}