package com.syzible.occupie.Tenant.CreateUserAccount.UserOAuthLogin;

import com.syzible.occupie.Common.Mvp;

public interface TenantOAuthLoginView extends Mvp.IView {
    void onContinueClick();

    void onTosClick();
}
