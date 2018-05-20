package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordDetailsConfirmation;

import com.syzible.occupie.Common.Mvp;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface LandlordDetailsConfirmationPresenter extends Mvp.IPresenter<LandlordDetailsConfirmationView>{
    void parsePayload(JSONObject oauth);

    void updateAccount() throws UnsupportedEncodingException;
}
