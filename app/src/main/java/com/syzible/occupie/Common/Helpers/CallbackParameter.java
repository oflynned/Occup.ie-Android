package com.syzible.occupie.Common.Helpers;

public interface CallbackParameter<T> {
    void onSuccess(T t);

    void onFailure();
}
