package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordDetailsConfirmation;

import android.content.Context;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface LandlordDetailsConfirmationInteractor {
    void createAccount(Context context, JSONObject profile) throws UnsupportedEncodingException;
}
