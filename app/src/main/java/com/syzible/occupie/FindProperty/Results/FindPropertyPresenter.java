package com.syzible.occupie.FindProperty.Results;

import com.syzible.occupie.Common.Mvp;

public interface FindPropertyPresenter extends Mvp.IPresenter<FindPropertyView> {
    void getProperties();
}
