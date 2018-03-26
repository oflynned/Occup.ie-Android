package com.syzible.occupie.Authentication.CreateUserAccount.UserOAuthLogin;

import com.syzible.occupie.Common.Mvp;

public interface FragmentOAuthLoginPresenter extends Mvp.IPresenter<FragmentOAuthLoginView> {
    void onFacebookCallback();

    void generatePayload();
}
