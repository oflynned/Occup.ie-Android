package com.syzible.occupie.Common.FeatureFlags;

import org.json.JSONException;
import org.json.JSONObject;

public class FeatureFlag {
    private String flagName, description, title, dialogTitle, dialogBody;
    private boolean shouldKickSession, shouldShowDialog, isEnabled;

    FeatureFlag(String flagName, JSONObject o) throws JSONException {
        this.flagName = flagName;
        this.title = o.getString("title");
        this.description = o.getString("description");
        this.isEnabled = o.getBoolean("enabled");

        JSONObject effects = o.getJSONObject("effects");
        this.shouldKickSession = effects.getBoolean("should_kick_session");
        this.shouldShowDialog = effects.getBoolean("should_show_dialog");
        this.dialogTitle = effects.has("dialog_title") ? effects.getString("dialog_title") : "";
        this.dialogBody = effects.has("dialog_body") ? effects.getString("dialog_body") : "";
    }

    FeatureFlag(String flagName, String description, String title, String dialogTitle, String dialogBody,
                boolean shouldKickSession, boolean shouldShowDialog, boolean isEnabled) {
        this.flagName = flagName;
        this.description = description;
        this.title = title;
        this.dialogTitle = dialogTitle;
        this.dialogBody = dialogBody;
        this.shouldKickSession = shouldKickSession;
        this.shouldShowDialog = shouldShowDialog;
        this.isEnabled = isEnabled;
    }

    public String getFlagName() {
        return flagName;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public String getDialogBody() {
        return dialogBody;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean shouldKickSession() {
        return shouldKickSession;
    }

    public boolean shouldShowDialog() {
        return shouldShowDialog;
    }

    @Override
    public String toString() {
        return "FeatureFlag{" +
                "flagName='" + flagName + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", dialogTitle='" + dialogTitle + '\'' +
                ", dialogBody='" + dialogBody + '\'' +
                ", shouldKickSession=" + shouldKickSession +
                ", shouldShowDialog=" + shouldShowDialog +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
