package com.syzible.rentapp.FindProperty;

import com.syzible.rentapp.Common.Mvp;

public interface FindPropertyPresenter extends Mvp.IPresenter<FindPropertyView> {
    void getProperties();
}
