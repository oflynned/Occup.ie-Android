package com.syzible.occupie.Tenant.FindProperty.Results.HouseShareResults;

import com.syzible.occupie.Common.Mvp;
import com.syzible.occupie.Common.Objects.HouseShare;
import com.syzible.occupie.Tenant.FindProperty.Common.PropertyType;

import java.util.List;

public interface FindHouseShareView extends Mvp.IView {
    void showProperties(List<HouseShare> propertyList);

    void setProgressBarLoading(boolean isLoading);

    PropertyType getPropertyType();
}
