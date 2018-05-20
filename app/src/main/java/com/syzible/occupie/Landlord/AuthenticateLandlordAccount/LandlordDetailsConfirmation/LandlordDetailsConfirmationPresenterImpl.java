package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordDetailsConfirmation;

import android.support.design.widget.Snackbar;

import com.syzible.occupie.Common.Helpers.DateHelpers;
import com.syzible.occupie.Common.Helpers.Encoding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LandlordDetailsConfirmationPresenterImpl implements LandlordDetailsConfirmationPresenter {
    private LandlordDetailsConfirmationView view;
    private JSONObject profile;

    @Override
    public void parsePayload(JSONObject profile) {
        this.profile = profile;

        String forename = null, surname = null, sex = null, dob = null, email = null;
        try {
            JSONObject details = profile.getJSONObject("details");
            forename = Encoding.decode(details.getString("forename"));
            surname = Encoding.decode(details.getString("surname"));
            sex = details.getString("sex");
            dob = DateHelpers.getBirthdayFormat(details.getString("dob"));
            email = details.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getNonNullableView().setForename(forename);
        getNonNullableView().setSurname(surname);
        getNonNullableView().setSex(sex);
        getNonNullableView().setDob(dob);
        getNonNullableView().setEmail(email);
    }

    @Override
    public void updateAccount() {
        try {
            JSONObject details = profile.getJSONObject("details");
            details.put("forename", Encoding.encode(getNonNullableView().getForename()));
            details.put("surname", Encoding.encode(getNonNullableView().getSurname()));
            details.put("sex", getNonNullableView().getSex());
            details.put("dob", DateHelpers.getDateFromIso8601(getNonNullableView().getDob()));
            details.put("email", getNonNullableView().getEmail());
            details.put("phone_number", "+353861231234"); // TODO add back in phone verification
            profile.put("details", details);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (getNonNullableView().isSectionCompleted()) {
            getNonNullableView().createAccount(profile);
        } else {
            Snackbar.make(getNonNullableView().getView(), "Please complete all fields", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void attach(LandlordDetailsConfirmationView landlordDetailsConfirmationView) {
        this.view = landlordDetailsConfirmationView;
    }

    @Override
    public LandlordDetailsConfirmationView getNonNullableView() throws IllegalStateException {
        if (view == null)
            throw new IllegalStateException();

        return view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
