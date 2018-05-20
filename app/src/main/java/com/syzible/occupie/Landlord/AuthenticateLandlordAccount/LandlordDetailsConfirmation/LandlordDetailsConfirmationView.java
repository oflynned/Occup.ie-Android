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

    String getForename();

    String getSurname();

    String getDob();

    String getSex();

    String getEmail();

    boolean isSectionCompleted();

    void createAccount(JSONObject profile);

    void onProfileCompleted();
}
