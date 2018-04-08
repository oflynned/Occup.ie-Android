package com.syzible.occupie.Tenant.CreateUserAccount.UserDetailsConfirmation;

import android.support.design.widget.Snackbar;

import com.syzible.occupie.Common.Helpers.DateHelpers;
import com.syzible.occupie.Common.Helpers.Encoding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class UserDetailsConfirmationPresenterImpl implements UserDetailsConfirmationPresenter {
    private UserDetailsConfirmationView view;
    private JSONObject profile;

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
    public void parsePayload(JSONObject profile) {
        this.profile = profile;

        String forename = null, surname = null, sex = null, dob = null;
        try {
            JSONObject details = profile.getJSONObject("details");
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

    @Override
    public void updateAccount() throws UnsupportedEncodingException {
        try {
            JSONObject details = profile.getJSONObject("details");
            details.put("forename", Encoding.encode(getNonNullableView().getForename()));
            details.put("surname", Encoding.encode(getNonNullableView().getSurname()));
            details.put("sex", getNonNullableView().getSex());
            details.put("profession", getNonNullableView().getProfession());
            details.put("dob", DateHelpers.getDateFromIso8601(getNonNullableView().getDob()));
            profile.put("details", details);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (getNonNullableView().isSectionCompleted()) {
            getNonNullableView().setNextFragment(profile);
        } else {
            Snackbar.make(getNonNullableView().getView(), "Please complete all fields", Snackbar.LENGTH_LONG).show();
        }
    }
}
