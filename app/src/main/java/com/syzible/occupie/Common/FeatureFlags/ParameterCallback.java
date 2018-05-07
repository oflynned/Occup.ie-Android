package com.syzible.occupie.Common.FeatureFlags;

public interface Callback<T> {
    void onSuccess(T t);

    void onFailure();
}
