package com.syzible.occupie.FindProperty.HouseShareResults.RentalResults;

import com.syzible.occupie.Common.Mvp;

public interface FindRentalPresenter extends Mvp.IPresenter<FindRentalView> {
    void getProperties();
}
