package com.syzible.occupie.Common.Persistence;

import android.content.Context;

import com.facebook.login.LoginManager;

public class OAuthUtils {

    private static String getToken(Context context, Target target) throws IllegalStateException {
        switch (target) {
            case user:
                return LocalPrefs.getStringPref(context, LocalPrefs.Pref.user_oauth_token);
            case landlord:
                return LocalPrefs.getStringPref(context, LocalPrefs.Pref.landlord_oauth_token);
            default:
                throw new IllegalStateException("no_user_target");
        }
    }

    private static void clearToken(Context context, Target target) {
        switch (target) {
            case user:
                LocalPrefs.purgePref(LocalPrefs.Pref.user_oauth_token, context);
                break;
            case landlord:
                LocalPrefs.purgePref(LocalPrefs.Pref.landlord_oauth_token, context);
                break;
        }
    }

    public static void saveId(String oauthId, Target target, Context context) {
        LocalPrefs.setStringPref(context,
                target == Target.user ? LocalPrefs.Pref.user_oauth_id : LocalPrefs.Pref.landlord_oauth_id,
                oauthId);
    }

    public static void saveProvider(String oauthProvider, Target target, Context context) {
        LocalPrefs.setStringPref(context,
                target == Target.user ? LocalPrefs.Pref.user_oauth_provider : LocalPrefs.Pref.landlord_oauth_provider,
                oauthProvider);
    }

    public static void saveToken(String oauthToken, Target target, Context context) {
        LocalPrefs.setStringPref(context,
                target == Target.user ? LocalPrefs.Pref.user_oauth_token : LocalPrefs.Pref.landlord_oauth_token,
                oauthToken);
    }

    public static boolean hasExistingToken(Context context, Target target) {
        return !getToken(context, target).equals("");
    }

    public static void deleteFacebookToken(Context context, Target target) {
        clearToken(context, target);
        LoginManager.getInstance().logOut();
    }
}
