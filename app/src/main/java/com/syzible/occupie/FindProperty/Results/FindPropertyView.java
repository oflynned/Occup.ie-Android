package com.syzible.rentapp.FindProperty.Results;

import com.syzible.rentapp.Common.Mvp;
import com.syzible.rentapp.Common.Objects.Property;

import java.util.List;

public interface FindPropertyView extends Mvp.IView {
    void showProperties(List<Property> propertyList);
}
