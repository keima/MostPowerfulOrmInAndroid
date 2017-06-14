/*
 * SimpleBaseRecord.java
 *
 * GENERATED FILE - DO NOT EDIT
 * CHECKSTYLE:OFF
 * 
 */



package net.pside.android.example.mostpowerfulorminandroid.dbtools.simple;

import android.database.Cursor;


@SuppressWarnings("all")
public class SimpleConst {

    public static final String DATABASE = "dbtools";
    public static final String TABLE = "Simple";
    public static final String FULL_TABLE = "dbtools.Simple";
    public static final String PRIMARY_KEY_COLUMN = "_id";
    public static final String C_ID = "_id";
    public static final String FULL_C_ID = "Simple._id";
    public static final String C_STRING_VALUE = "STRING_VALUE";
    public static final String FULL_C_STRING_VALUE = "Simple.STRING_VALUE";
    public static final String C_DATE_VALUE = "DATE_VALUE";
    public static final String FULL_C_DATE_VALUE = "Simple.DATE_VALUE";
    public static final String C_BOOLEAN_VALUE = "BOOLEAN_VALUE";
    public static final String FULL_C_BOOLEAN_VALUE = "Simple.BOOLEAN_VALUE";
    public static final String C_SHORT_VALUE = "SHORT_VALUE";
    public static final String FULL_C_SHORT_VALUE = "Simple.SHORT_VALUE";
    public static final String C_INT_VALUE = "INT_VALUE";
    public static final String FULL_C_INT_VALUE = "Simple.INT_VALUE";
    public static final String C_LONG_VALUE = "LONG_VALUE";
    public static final String FULL_C_LONG_VALUE = "Simple.LONG_VALUE";
    public static final String C_FLOAT_VALUE = "FLOAT_VALUE";
    public static final String FULL_C_FLOAT_VALUE = "Simple.FLOAT_VALUE";
    public static final String C_DOUBLE_VALUE = "DOUBLE_VALUE";
    public static final String FULL_C_DOUBLE_VALUE = "Simple.DOUBLE_VALUE";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Simple (" + 
        "_id INTEGER PRIMARY KEY  AUTOINCREMENT," + 
        "STRING_VALUE TEXT," + 
        "DATE_VALUE TEXT," + 
        "BOOLEAN_VALUE INTEGER," + 
        "SHORT_VALUE INTEGER," + 
        "INT_VALUE INTEGER," + 
        "LONG_VALUE INTEGER," + 
        "FLOAT_VALUE REAL," + 
        "DOUBLE_VALUE REAL" + 
        ");" + 
        "" + 
        "";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS Simple;";
    public static final String INSERT_STATEMENT = "INSERT INTO Simple (STRING_VALUE,DATE_VALUE,BOOLEAN_VALUE,SHORT_VALUE,INT_VALUE,LONG_VALUE,FLOAT_VALUE,DOUBLE_VALUE) VALUES (?,?,?,?,?,?,?,?)";
    public static final String UPDATE_STATEMENT = "UPDATE Simple SET STRING_VALUE=?, DATE_VALUE=?, BOOLEAN_VALUE=?, SHORT_VALUE=?, INT_VALUE=?, LONG_VALUE=?, FLOAT_VALUE=?, DOUBLE_VALUE=? WHERE _id = ?";
    public static final String[] ALL_COLUMNS = new String[] {
        C_ID,
        C_STRING_VALUE,
        C_DATE_VALUE,
        C_BOOLEAN_VALUE,
        C_SHORT_VALUE,
        C_INT_VALUE,
        C_LONG_VALUE,
        C_FLOAT_VALUE,
        C_DOUBLE_VALUE};
    public static final String[] ALL_COLUMNS_FULL = new String[] {
        FULL_C_ID,
        FULL_C_STRING_VALUE,
        FULL_C_DATE_VALUE,
        FULL_C_BOOLEAN_VALUE,
        FULL_C_SHORT_VALUE,
        FULL_C_INT_VALUE,
        FULL_C_LONG_VALUE,
        FULL_C_FLOAT_VALUE,
        FULL_C_DOUBLE_VALUE};

    public SimpleConst() {
    }

    public static long getId(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(C_ID));
    }

    public static String getStringValue(Cursor cursor) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(C_STRING_VALUE)) ? cursor.getString(cursor.getColumnIndexOrThrow(C_STRING_VALUE)) : null;
    }

    public static org.joda.time.DateTime getDateValue(Cursor cursor) {
        return org.dbtools.android.domain.date.DBToolsJodaFormatter.dbStringToDateTime(cursor.getString(cursor.getColumnIndexOrThrow(C_DATE_VALUE)));
    }

    public static Boolean isBooleanValue(Cursor cursor) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(C_BOOLEAN_VALUE)) ? cursor.getInt(cursor.getColumnIndexOrThrow(C_BOOLEAN_VALUE)) != 0 ? true : false : null;
    }

    public static Integer getShortValue(Cursor cursor) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(C_SHORT_VALUE)) ? cursor.getInt(cursor.getColumnIndexOrThrow(C_SHORT_VALUE)) : null;
    }

    public static Integer getIntValue(Cursor cursor) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(C_INT_VALUE)) ? cursor.getInt(cursor.getColumnIndexOrThrow(C_INT_VALUE)) : null;
    }

    public static Long getLongValue(Cursor cursor) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(C_LONG_VALUE)) ? cursor.getLong(cursor.getColumnIndexOrThrow(C_LONG_VALUE)) : null;
    }

    public static Float getFloatValue(Cursor cursor) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(C_FLOAT_VALUE)) ? cursor.getFloat(cursor.getColumnIndexOrThrow(C_FLOAT_VALUE)) : null;
    }

    public static Double getDoubleValue(Cursor cursor) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(C_DOUBLE_VALUE)) ? cursor.getDouble(cursor.getColumnIndexOrThrow(C_DOUBLE_VALUE)) : null;
    }


}