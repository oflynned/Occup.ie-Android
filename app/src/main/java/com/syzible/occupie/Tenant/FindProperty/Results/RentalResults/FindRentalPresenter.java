package com.syzible.occupie.FindProperty.Results.RentalResults;

import com.syzible.occupie.Common.Mvp;

public interface FindRentalPresenter extends Mvp.IPresenter<FindRentalView> {
    void getProperties();
}
