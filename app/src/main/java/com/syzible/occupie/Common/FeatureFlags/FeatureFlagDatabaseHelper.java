package com.syzible.occupie.Common.FeatureFlags;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class FeatureFlagDatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;

    private static final String CREATE_FEATURE_FLAG_TABLE =
            "CREATE TABLE " + FeatureFlagDatabase.Columns.TABLE_NAME + "(" +
                    FeatureFlagDatabase.Columns.FLAG_NAME + " TEXT PRIMARY KEY," +
                    FeatureFlagDatabase.Columns.TITLE + " TEXT," +
                    FeatureFlagDatabase.Columns.DESCRIPTION + " TEXT," +
                    FeatureFlagDatabase.Columns.DIALOG_TITLE + " TEXT," +
                    FeatureFlagDatabase.Columns.DIALOG_BODY + " TEXT," +
                    FeatureFlagDatabase.Columns.IS_FLAG_ENABLED + " INTEGER," +
                    FeatureFlagDatabase.Columns.SHOULD_KICK_SESSION + " INTEGER," +
                    FeatureFlagDatabase.Columns.SHOULD_SHOW_DIALOG + " INTEGER);";

    private static final String DELETE_FEATURE_FLAG_TABLE =
            "DROP TABLE IF EXISTS " + FeatureFlagDatabase.Columns.TABLE_NAME + ";";

    private FeatureFlagDatabaseHelper(Context context) {
        super(context, FeatureFlagDatabase.DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FEATURE_FLAG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_FEATURE_FLAG_TABLE);
        onCreate(db);
    }

    public static FeatureFlag getFeatureFlag(Context context, Flags flag) throws FeatureFlagNotPresentException {
        FeatureFlagDatabaseHelper db = new FeatureFlagDatabaseHelper(context);
        SQLiteDatabase readDb = db.getReadableDatabase();
        String tableName = FeatureFlagDatabase.Columns.TABLE_NAME;

        String query = "SELECT * FROM " + tableName +
                " WHERE " + FeatureFlagDatabase.Columns.FLAG_NAME + "='" + flag.name() + "';";
        Cursor cursor = readDb.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            String flagName = cursor.getString(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String dialogTitle = cursor.getString(3);
            String dialogBody = cursor.getString(4);
            boolean enabled = cursor.getInt(5) == 1;
            boolean shouldKickSession = cursor.getInt(6) == 1;
            boolean shouldShowDialog = cursor.getInt(7) == 1;

            readDb.close();
            cursor.close();
            db.close();

            return new FeatureFlag(flagName, title, description, dialogTitle, dialogBody,
                    shouldKickSession, shouldShowDialog, enabled);
        } else {
            throw new FeatureFlagNotPresentException();
        }
    }

    public static void updateFeatureFlags(Context context, List<FeatureFlag> featureFlags) {
        FeatureFlagDatabaseHelper db = new FeatureFlagDatabaseHelper(context);
        SQLiteDatabase writeDb = db.getWritableDatabase();
        String tableName = FeatureFlagDatabase.Columns.TABLE_NAME;

        // first purge the db as feature flags are populated frequently
        writeDb.execSQL(DELETE_FEATURE_FLAG_TABLE);
        writeDb.execSQL(CREATE_FEATURE_FLAG_TABLE);

        // now repopulate with flag data
        for (FeatureFlag featureFlag : featureFlags) {
            ContentValues rowValue = getFlagRowValue(featureFlag);
            writeDb.insert(tableName, null, rowValue);
        }

        writeDb.close();
        db.close();
        writeDb.close();
    }

    private static ContentValues getFlagRowValue(FeatureFlag featureFlag) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeatureFlagDatabase.Columns.FLAG_NAME, featureFlag.getFlagName());
        contentValues.put(FeatureFlagDatabase.Columns.TITLE, featureFlag.getTitle());
        contentValues.put(FeatureFlagDatabase.Columns.DESCRIPTION, featureFlag.getDescription());
        contentValues.put(FeatureFlagDatabase.Columns.DIALOG_TITLE, featureFlag.getDialogTitle());
        contentValues.put(FeatureFlagDatabase.Columns.DIALOG_BODY, featureFlag.getDialogBody());
        contentValues.put(FeatureFlagDatabase.Columns.IS_FLAG_ENABLED, featureFlag.isEnabled() ? 1 : 0);
        contentValues.put(FeatureFlagDatabase.Columns.SHOULD_KICK_SESSION, featureFlag.shouldKickSession() ? 1 : 0);
        contentValues.put(FeatureFlagDatabase.Columns.SHOULD_SHOW_DIALOG, featureFlag.shouldShowDialog() ? 1 : 0);

        return contentValues;
    }

    public static void printDbContents(Context context) {
        FeatureFlagDatabaseHelper db = new FeatureFlagDatabaseHelper(context);
        SQLiteDatabase readDb = db.getReadableDatabase();
        String tableName = FeatureFlagDatabase.Columns.TABLE_NAME;

        String query = "SELECT * FROM (" + tableName + ");";
        Cursor cursor = readDb.rawQuery(query, null);
        cursor.moveToFirst();

        int rowNum = cursor.getCount();
        int colNum = cursor.getColumnCount();

        for (int r = 0; r < rowNum; r++) {
            for (int col = 0; col < colNum; col++) {
                System.out.print(col + ". " + cursor.getString(col) + "; ");
            }
            System.out.print("\n");
            cursor.moveToNext();
        }

        readDb.close();
        cursor.close();
        db.close();
    }
}
