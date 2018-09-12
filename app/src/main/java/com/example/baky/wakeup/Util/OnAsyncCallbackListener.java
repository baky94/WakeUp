package com.example.baky.wakeup.Util;

public interface OnAsyncCallbackListener<T> {
    public void onSuccess(T object);
    public void onFailure(Exception e);
}