package com.syzible.occupie.Common.Helpers;

import org.json.JSONException;

public interface CallbackParameter<T> {
    void onSuccess(T t) throws JSONException;

    void onFailure();
}
