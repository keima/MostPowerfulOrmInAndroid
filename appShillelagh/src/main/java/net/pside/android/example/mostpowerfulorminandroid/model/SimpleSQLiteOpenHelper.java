package net.pside.android.example.mostpowerfulorminandroid.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import shillelagh.Shillelagh;

public class SimpleSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "shillelagh.db";
    public static final int DB_VERSION = 1;

    public SimpleSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Shillelagh.createTable(db, Simple.class);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Shillelagh.dropTable(db, Simple.class);
        onCreate(db);
    }
}
