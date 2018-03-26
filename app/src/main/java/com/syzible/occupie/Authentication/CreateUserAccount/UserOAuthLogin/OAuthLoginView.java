package com.syzible.occupie.Authentication.CreateUserAccount.UserOAuthLogin;

import com.syzible.occupie.Common.Mvp;

public interface FragmentOAuthLoginView extends Mvp.IView{
    void onFacebookClick();

    void onContinueClick();

    void onTosClick();
}
