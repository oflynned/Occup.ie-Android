package com.syzible.occupie.Tenant.FindProperty.Results.HouseShareResults;

import com.syzible.occupie.Common.Mvp;

public interface FindHouseSharePresenter extends Mvp.IPresenter<FindHouseShareView> {
    void getProperties();
}
