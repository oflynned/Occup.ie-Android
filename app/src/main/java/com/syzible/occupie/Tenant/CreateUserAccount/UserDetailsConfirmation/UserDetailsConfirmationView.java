package com.syzible.occupie.Tenant.CreateUserAccount.UserDetailsConfirmation;

import com.syzible.occupie.Common.Mvp;

import org.json.JSONObject;

public interface UserDetailsConfirmationView extends Mvp.IView {
    void setForename(String forename);

    void setSurname(String surname);

    void setDob(String dob);

    void setEmail(String email);

    void setSex(String gender);

    void setProfession(String profession);

    String getForename();

    String getSurname();

    String getSex();

    String getDob();

    String getEmail();

    String getProfession();

    boolean isSectionCompleted();

    void setNextFragment(JSONObject profile);
}
