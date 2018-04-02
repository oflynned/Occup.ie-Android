package com.syzible.occupie.Tenant.CreateUserAccount.UserDetailsConfirmation;

import com.syzible.occupie.Common.Helpers.DateHelpers;
import com.syzible.occupie.Common.Helpers.Encoding;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDetailsConfirmationPresenterImpl implements UserDetailsConfirmationPresenter {
    private UserDetailsConfirmationView view;

    @Override
    public void attach(UserDetailsConfirmationView view) {
        this.view = view;
    }

    @Override
    public UserDetailsConfirmationView getNonNullableView() throws IllegalStateException {
        if (view == null)
            throw new IllegalStateException();

        return view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void parsePayload(JSONObject oauth) {
        String forename = null, surname = null, sex = null, dob = null;

        try {
            JSONObject details = oauth.getJSONObject("details");
            forename = Encoding.decode(details.getString("forename"));
            surname = Encoding.decode(details.getString("surname"));
            sex = details.getString("sex");
            dob = DateHelpers.getBirthdayFormat(details.getString("dob"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getNonNullableView().setForename(forename);
        getNonNullableView().setSurname(surname);
        getNonNullableView().setSex(sex);
        getNonNullableView().setDob(dob);
    }
}
