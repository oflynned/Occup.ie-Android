package com.syzible.occupie.Common.Persistence;

import android.content.Context;

import com.facebook.login.LoginManager;

public class FacebookUtils {

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
                if (LocalPrefs.getStringPref(context, LocalPrefs.Pref.user_oauth_provider).equals("facebook"))
                    LocalPrefs.purgePref(LocalPrefs.Pref.user_oauth_token, context);
                break;
            case landlord:
                if (LocalPrefs.getStringPref(context, LocalPrefs.Pref.landlord_oauth_provider).equals("facebook"))
                    LocalPrefs.purgePref(LocalPrefs.Pref.landlord_oauth_token, context);
                break;
        }
    }

    public static void saveProvider(Target target, Context context) {
        LocalPrefs.setStringPref(context,
                target == Target.user ? LocalPrefs.Pref.user_oauth_provider : LocalPrefs.Pref.landlord_oauth_provider,
                "facebook");
    }

    public static void saveToken(String token, Target target, Context context) {
        LocalPrefs.setStringPref(context,
                target == Target.user ? LocalPrefs.Pref.user_oauth_token : LocalPrefs.Pref.landlord_oauth_token,
                token);
    }

    public static boolean hasExistingToken(Context context, Target target) {
        return !getToken(context, target).equals("");
    }

    public static void deleteToken(Context context, Target target) {
        clearToken(context, target);
        LoginManager.getInstance().logOut();
    }
}
