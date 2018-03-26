package com.syzible.occupie.Common.Persistence;

import android.content.Context;
import android.preference.PreferenceManager;

public class LocalPreferences {
    public enum Pref {
        tenant_id,
        tenant_oauth_provider, tenant_oauth_id, tenant_oauth_token,
        tenant_forename, tenant_surname,
        tenant_tos_version_accepted, tenant_privacy_version_accepted,

        landlord_id,
        landlord_oauth_provider, landlord_oauth_id, landlord_oauth_token,
        landlord_forename, landlord_surname,
        landlord_tos_version_accepted, landlord_privacy_version_accepted
    }

    public static int getVersionAccepted(Context context, Pref pref) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(pref.name(), -1);
    }

    public static void setVersionAccepted(Context context, Pref pref, int version) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putInt(pref.name(), version).apply();
    }

    public static String getStringPref(Context context, Pref pref) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(pref.name(), null);
    }

    public static void setStringPref(Context context, Pref pref, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(pref.name(), value).apply();
    }
}
