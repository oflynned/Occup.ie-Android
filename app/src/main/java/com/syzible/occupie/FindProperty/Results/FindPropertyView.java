package com.syzible.occupie.FindProperty.Results;

import com.syzible.occupie.Common.Mvp;
import com.syzible.occupie.Common.Objects.Property;

import java.util.List;

public interface FindPropertyView extends Mvp.IView {
    void showProperties(List<Property> propertyList);
}
