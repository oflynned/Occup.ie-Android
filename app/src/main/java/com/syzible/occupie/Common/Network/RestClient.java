package com.syzible.occupie.Common.Network;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class RestClient {
    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        new AsyncHttpClient().get(url, null, responseHandler);
    }

    public static void post(Context context, String url, JSONObject data, AsyncHttpResponseHandler responseHandler) throws UnsupportedEncodingException {
        new AsyncHttpClient().post(context, url, new StringEntity(data.toString()), "application/json", responseHandler);
    }

    public static void put(Context context, String url, JSONObject data, AsyncHttpResponseHandler responseHandler) throws UnsupportedEncodingException {
        new AsyncHttpClient().put(context, url, new StringEntity(data.toString()), "application/json", responseHandler);
    }

    public static void delete(String url, AsyncHttpResponseHandler responseHandler) {
        new AsyncHttpClient().delete(url, null, responseHandler);
    }
}
