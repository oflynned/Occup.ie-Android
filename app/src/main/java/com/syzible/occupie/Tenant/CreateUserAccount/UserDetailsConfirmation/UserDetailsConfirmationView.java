package com.syzible.occupie.Tenant.CreateUserAccount.UserDetailsConfirmation;

import com.syzible.occupie.Common.Mvp;

import java.util.Date;

public interface UserDetailsConfirmationView extends Mvp.IView {
    void setForename(String forename);

    void setSurname(String surname);

    void setDob(String dob);

    void setSex(String gender);

    void setProfession(String profession);
}
