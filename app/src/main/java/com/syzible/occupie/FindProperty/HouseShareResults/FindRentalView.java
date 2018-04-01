package com.syzible.occupie.FindProperty.HouseShareResults.RentalResults;

import com.syzible.occupie.Common.Mvp;
import com.syzible.occupie.Common.Objects.Rental;

import java.util.List;

public interface FindRentalView extends Mvp.IView {
    void showProperties(List<Rental> propertyList);

    void setProgressBarLoading(boolean isLoading);

    FindRentalFragment.PropertyType getPropertyType();
}
