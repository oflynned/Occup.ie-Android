package com.syzible.occupie.Common.Persistence;

import android.content.Context;
import android.preference.PreferenceManager;

public class LocalPrefs {
    public enum Pref {
        user_id, user_oauth_provider, user_oauth_id, user_oauth_token,
        user_forename, user_surname,

        landlord_id, landlord_oauth_provider, landlord_oauth_id, landlord_oauth_token,
        landlord_forename, landlord_surname,

        current_account, is_user_first_run_done, is_landlord_first_run_done
    }

    public static int getVersionAccepted(Context context, Pref pref) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(pref.name(), -1);
    }

    public static void setVersionAccepted(Context context, Pref pref, int version) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putInt(pref.name(), version).apply();
    }

    public static String getFullName(Context context) {
        if (getStringPref(context, Pref.current_account).equals(Target.user.name()))
            return String.format("%s %s", getStringPref(context, Pref.user_forename), getStringPref(context, Pref.user_surname));
        else if (getStringPref(context, Pref.current_account).equals(Target.landlord.name()))
            return String.format("%s %s", getStringPref(context, Pref.landlord_forename), getStringPref(context, Pref.landlord_surname));
        else return "";
    }

    public static String getCurrentProfile(Context context) {
        if (getStringPref(context, Pref.current_account).equals(Target.landlord.name()))
            return "Landlord";

        return "Tenant";
    }

    public static String getStringPref(Context context, Pref pref) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(pref.name(), "");
    }

    public static void setStringPref(Context context, Pref pref, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(pref.name(), value).apply();
    }

    public static boolean getBooleanPref(Context context, Pref pref) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(pref.name(), false);
    }

    public static void setBooleanPref(Context context, Pref pref, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean(pref.name(), value).apply();
    }

    public static void purgePref(Pref key, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString(key.name(), "").apply();
    }

    public static boolean isUserLoggedIn(Context context) {
        if (getStringPref(context, Pref.current_account).equals(Target.landlord.name()))
            return false;

        if (getStringPref(context, Pref.current_account).equals(""))
            return false;

        return true;
    }

    public static boolean isLandlordLoggedIn(Context context) {
        if (getStringPref(context, Pref.current_account).equals(Target.user.name()))
            return false;

        if (getStringPref(context, Pref.current_account).equals(""))
            return false;

        return true;
    }

    public static boolean isFirstRunCompleted(Context context, Target target) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean((target == Target.landlord ?
                        Pref.is_landlord_first_run_done : Pref.is_user_first_run_done).name(), false);
    }

    public static boolean isSomeoneLoggedIn(Context context) {
        return isLandlordLoggedIn(context) || isUserLoggedIn(context);
    }
}
