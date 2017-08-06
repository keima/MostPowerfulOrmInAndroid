package net.pside.android.example.mostpowerfulorminandroid.model;

import net.pside.android.example.mostpowerfulorminandroid.BuildConfig;

public class SimplesTable {

    public static final String TABLE = "simple";
    public static final String URI = "content://" + BuildConfig.APPLICATION_ID + "/" + TABLE;

    public static final int URI_MATCHER_CODE = 1;

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STRING = "stringValue";
    public static final String COLUMN_DATE = "dateValue";
    public static final String COLUMN_BOOLEAN = "booleanValue";
    public static final String COLUMN_SHORT = "shortValue";
    public static final String COLUMN_INT = "intValue";
    public static final String COLUMN_LONG = "longValue";
    public static final String COLUMN_FLOAT = "floatValue";
    public static final String COLUMN_DOUBLE = "doubleValue";

    private SimplesTable() {
    }

    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + " (" +
                COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                COLUMN_STRING + " TEXT NOT NULL, " +
                COLUMN_DATE + " INTEGER NOT NULL, " +
                COLUMN_BOOLEAN + " INTEGER NOT NULL, " +
                COLUMN_SHORT + " INTEGER NOT NULL, " +
                COLUMN_INT + " INTEGER NOT NULL, " +
                COLUMN_LONG + " INTEGER NOT NULL, " +
                COLUMN_FLOAT + " REAL NOT NULL, " +
                COLUMN_DOUBLE + " REAL NOT NULL" +
                " );";
    }

}
