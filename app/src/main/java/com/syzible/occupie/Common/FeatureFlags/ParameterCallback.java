package com.syzible.occupie.Common.FeatureFlags;

public interface ParameterCallback<T> {
    void onSuccess(T t);

    void onFailure();
}
