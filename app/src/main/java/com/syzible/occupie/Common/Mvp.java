package com.syzible.rentapp.Common;

import android.content.Context;
import android.view.View;

public interface Mvp {

    interface IPresenter<View> {
        void attach(View view);

        View getNonNullableView() throws IllegalStateException;

        void detach();
    }

    interface IView {
        View getView();

        Context getContext();
    }
}
