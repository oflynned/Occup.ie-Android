
package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordOAuthLogin;

import com.syzible.occupie.Common.Mvp;

import org.json.JSONObject;

public interface LandlordOAuthLoginView extends Mvp.IView {
    void onContinueToMain();

    void onTosClick();

    void onContinueAccountCreation(JSONObject oauthProfile);
}
