package com.syzible.occupie.Common.FeatureFlags;

import android.provider.BaseColumns;

public class FeatureFlagDatabase {
    public static final String DATABASE_NAME = "occupie_local_data";

    private FeatureFlagDatabase() {
    }

    public static abstract class Columns implements BaseColumns {
        public static final String TABLE_NAME = "feature_flags";
        public static final String FLAG_NAME = "flag_name";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String DIALOG_TITLE = "dialog_title";
        public static final String DIALOG_BODY = "dialog_body";
        public static final String IS_FLAG_ENABLED = "is_flag_enabled";
        public static final String SHOULD_KICK_SESSION = "should_kick_session";
        public static final String SHOULD_SHOW_DIALOG = "should_show_dialog";
    }
}
