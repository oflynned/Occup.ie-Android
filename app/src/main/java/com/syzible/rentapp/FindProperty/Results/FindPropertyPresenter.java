package com.syzible.rentapp.FindProperty.Results;

import com.syzible.rentapp.Common.Mvp;

public interface FindPropertyPresenter extends Mvp.IPresenter<FindPropertyView> {
    void getProperties();
}
