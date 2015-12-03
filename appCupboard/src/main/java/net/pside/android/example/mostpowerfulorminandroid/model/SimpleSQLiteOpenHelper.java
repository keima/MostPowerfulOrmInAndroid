package net.pside.android.example.mostpowerfulorminandroid.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nl.qbusict.cupboard.CupboardFactory;

public class SimpleSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "cupboard.db";
    public static final int DB_VERSION = 1;

    static {
        CupboardFactory.cupboard().register(Simple.class);
    }

    public SimpleSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CupboardFactory.cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CupboardFactory.cupboard().withDatabase(db).upgradeTables();
    }
}
