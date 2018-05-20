package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordDetailsConfirmation;

import com.syzible.occupie.Common.Mvp;

import org.json.JSONObject;

public interface LandlordDetailsConfirmationView extends Mvp.IView {
    void setProfile(JSONObject profile);

    void setForename(String forename);

    void setSurname(String surname);

    void setDob(String dob);

    void setSex(String sex);

    void setEmail(String email);

    void setPhoneNumber(String phoneNumber);

    String getForename();

    String getSurname();

    String getDob();

    String getSex();

    String getEmail();

    String getPhoneNumber();

    boolean isSectionCompleted();

    void setNextFragment(JSONObject profile);
}
