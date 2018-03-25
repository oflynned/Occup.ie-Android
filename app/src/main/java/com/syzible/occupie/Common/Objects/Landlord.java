package com.syzible.occupie.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Landlord {
    private String guid, googleToken, firebaseToken;
    private String forename, surname, dob, gender, email, phoneNumber;
    private boolean isIdVerified, isPhoneVerified;
    private Date accountCreationTimestamp;
    private int tosAccepted, privacyPolicyAccepted;

    public Landlord(JSONObject o) throws JSONException {
        this.guid = o.getString("_id");
        this.googleToken = o.getString("google_token");
        this.firebaseToken = o.getString("firebase_token");
        this.forename = o.getString("forename");
        this.surname = o.getString("surname");
    }

    public Landlord(String guid, String googleToken, String firebaseToken, String forename,
                    String surname, String dob, String gender, String email, String phoneNumber,
                    boolean isIdVerified, boolean isPhoneVerified, Date accountCreationTimestamp,
                    int tosAccepted, int privacyPolicyAccepted) {
        this.guid = guid;
        this.googleToken = googleToken;
        this.firebaseToken = firebaseToken;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isIdVerified = isIdVerified;
        this.isPhoneVerified = isPhoneVerified;
        this.accountCreationTimestamp = accountCreationTimestamp;
        this.tosAccepted = tosAccepted;
        this.privacyPolicyAccepted = privacyPolicyAccepted;
    }

    public String getGuid() {
        return guid;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isIdVerified() {
        return isIdVerified;
    }

    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }

    public Date getAccountCreationTimestamp() {
        return accountCreationTimestamp;
    }

    public int getTosAccepted() {
        return tosAccepted;
    }

    public int getPrivacyPolicyAccepted() {
        return privacyPolicyAccepted;
    }
}
