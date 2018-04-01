package com.syzible.occupie.Common.Network;

import android.content.Context;
import android.os.Looper;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.syzible.occupie.Common.Persistence.LocalPrefs;
import com.syzible.occupie.Common.Persistence.Target;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class RestClient {
    private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private static SyncHttpClient syncHttpClient = new SyncHttpClient();

    public static void get(Context context, String url, AsyncHttpResponseHandler handler) {
        getClient(context).get(url, handler);
    }

    public static void post(Context context, String url, JSONObject data, AsyncHttpResponseHandler handler) throws UnsupportedEncodingException {
        getClient(context).post(context, url, new StringEntity(data.toString()), "application/json", handler);
    }

    public static void put(Context context, String url, JSONObject data, AsyncHttpResponseHandler handler) throws UnsupportedEncodingException {
        getClient(context).put(context, url, new StringEntity(data.toString()), "application/json", handler);
    }

    public static void delete(Context context, String url, AsyncHttpResponseHandler handler) {
        getClient(context).delete(url, handler);
    }

    private static AsyncHttpClient getClient(Context context) {
        if (Looper.myLooper() == null) {
            setHeaders(context, syncHttpClient);
            return syncHttpClient;
        }

        setHeaders(context, asyncHttpClient);
        return asyncHttpClient;
    }

    private static void setHeaders(Context context, AsyncHttpClient client) {
        boolean isUser = LocalPrefs.getStringPref(context, LocalPrefs.Pref.current_account).equals(Target.user.name());
        String oauthId = LocalPrefs.getStringPref(context, isUser ? LocalPrefs.Pref.user_oauth_id : LocalPrefs.Pref.landlord_oauth_id);
        String oauthToken = LocalPrefs.getStringPref(context, isUser ? LocalPrefs.Pref.user_oauth_token : LocalPrefs.Pref.landlord_oauth_token);
        String oauthProvider = LocalPrefs.getStringPref(context, isUser ? LocalPrefs.Pref.user_oauth_provider : LocalPrefs.Pref.landlord_oauth_provider);

        client.addHeader("oauth_id", oauthId);
        client.addHeader("oauth_provider", oauthProvider);
        client.addHeader("Authorization", String.format("Bearer %s", oauthToken));
    }
}
