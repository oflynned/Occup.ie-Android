package com.syzible.occupie.Authentication.CreateUserAccount.UserOAuthLogin;

import com.syzible.occupie.Common.Mvp;

public interface OAuthLoginView extends Mvp.IView {
    void onContinueClick();

    void onTosClick();
}
