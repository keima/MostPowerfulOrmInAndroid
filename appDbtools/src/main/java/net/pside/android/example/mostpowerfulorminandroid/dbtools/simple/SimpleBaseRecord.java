/*
 * SimpleBaseRecord.java
 *
 * GENERATED FILE - DO NOT EDIT
 * CHECKSTYLE:OFF
 * 
 */



package net.pside.android.example.mostpowerfulorminandroid.dbtools.simple;

import org.dbtools.android.domain.AndroidBaseRecord;
import android.database.Cursor;
import android.content.ContentValues;


@SuppressWarnings("all")
public abstract class SimpleBaseRecord extends AndroidBaseRecord {

    public static final String DATABASE = "dbtools";
    public static final String TABLE = "Simple";
    public static final String FULL_TABLE = "dbtools.Simple";
    public static final String PRIMARY_KEY_COLUMN = "_id";
    public static final String C_ID = "_id";
    public static final String FULL_C_ID = "Simple._id";
    private long id = 0;
    public static final String C_STRING_VALUE = "STRING_VALUE";
    public static final String FULL_C_STRING_VALUE = "Simple.STRING_VALUE";
    private String stringValue = "";
    public static final String C_DATE_VALUE = "DATE_VALUE";
    public static final String FULL_C_DATE_VALUE = "Simple.DATE_VALUE";
    private java.util.Date dateValue = null;
    public static final String C_BOOLEAN_VALUE = "BOOLEAN_VALUE";
    public static final String FULL_C_BOOLEAN_VALUE = "Simple.BOOLEAN_VALUE";
    private Boolean booleanValue = false;
    public static final String C_SHORT_VALUE = "SHORT_VALUE";
    public static final String FULL_C_SHORT_VALUE = "Simple.SHORT_VALUE";
    private Integer shortValue = null;
    public static final String C_INT_VALUE = "INT_VALUE";
    public static final String FULL_C_INT_VALUE = "Simple.INT_VALUE";
    private Integer intValue = null;
    public static final String C_LONG_VALUE = "LONG_VALUE";
    public static final String FULL_C_LONG_VALUE = "Simple.LONG_VALUE";
    private Long longValue = null;
    public static final String C_FLOAT_VALUE = "FLOAT_VALUE";
    public static final String FULL_C_FLOAT_VALUE = "Simple.FLOAT_VALUE";
    private Float floatValue = null;
    public static final String C_DOUBLE_VALUE = "DOUBLE_VALUE";
    public static final String FULL_C_DOUBLE_VALUE = "Simple.DOUBLE_VALUE";
    private Double doubleValue = null;
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
    public static final String[] ALL_KEYS = new String[] {
        C_ID,
        C_STRING_VALUE,
        C_DATE_VALUE,
        C_BOOLEAN_VALUE,
        C_SHORT_VALUE,
        C_INT_VALUE,
        C_LONG_VALUE,
        C_FLOAT_VALUE,
        C_DOUBLE_VALUE};

    public SimpleBaseRecord() {
    }

    @Override
    public String getIdColumnName() {
        return C_ID;
    }

    @Override
    public long getPrimaryKeyId() {
        return id;
    }

    @Override
    public void setPrimaryKeyId(long id) {
        this.id = id;
    }

    public static long getId(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(C_ID));
    }

    public static String getStringValue(Cursor cursor) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(C_STRING_VALUE)) ? cursor.getString(cursor.getColumnIndexOrThrow(C_STRING_VALUE)) : null;
    }

    public static java.util.Date getDateValue(Cursor cursor) {
        return dbStringToDate(cursor.getString(cursor.getColumnIndexOrThrow(C_DATE_VALUE)));
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
        return cursor.getDouble(cursor.getColumnIndexOrThrow(C_DOUBLE_VALUE));
    }

    @Override
    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(C_STRING_VALUE, stringValue);
        values.put(C_DATE_VALUE, dateValue != null ? dateValue.getTime() : null);
        values.put(C_BOOLEAN_VALUE, booleanValue != null ? (booleanValue ? 1 : 0) : 0);
        values.put(C_SHORT_VALUE, shortValue);
        values.put(C_INT_VALUE, intValue);
        values.put(C_LONG_VALUE, longValue);
        values.put(C_FLOAT_VALUE, floatValue);
        values.put(C_DOUBLE_VALUE, doubleValue);
        return values;
    }

    @Override
    public Object[] getValues() {
        Object[] values = new Object[]{
            id,
            stringValue,
            dateValue != null ? dateValue.getTime() : null,
            booleanValue != null ? (booleanValue ? 1 : 0) : 0,
            shortValue,
            intValue,
            longValue,
            floatValue,
            doubleValue,
        };
        return values;
    }

    public void setContent(ContentValues values) {
        stringValue = values.getAsString(C_STRING_VALUE);
        dateValue = dbStringToDate(values.getAsString(C_DATE_VALUE));
        booleanValue = values.getAsBoolean(C_BOOLEAN_VALUE);
        shortValue = values.getAsInteger(C_SHORT_VALUE);
        intValue = values.getAsInteger(C_INT_VALUE);
        longValue = values.getAsLong(C_LONG_VALUE);
        floatValue = values.getAsFloat(C_FLOAT_VALUE);
        doubleValue = values.getAsDouble(C_DOUBLE_VALUE);
    }

    @Override
    public void setContent(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndexOrThrow(C_ID));
        stringValue = !cursor.isNull(cursor.getColumnIndexOrThrow(C_STRING_VALUE)) ? cursor.getString(cursor.getColumnIndexOrThrow(C_STRING_VALUE)) : null;
        dateValue = dbStringToDate(cursor.getString(cursor.getColumnIndexOrThrow(C_DATE_VALUE)));
        booleanValue = !cursor.isNull(cursor.getColumnIndexOrThrow(C_BOOLEAN_VALUE)) ? cursor.getInt(cursor.getColumnIndexOrThrow(C_BOOLEAN_VALUE)) != 0 ? true : false : null;
        shortValue = !cursor.isNull(cursor.getColumnIndexOrThrow(C_SHORT_VALUE)) ? cursor.getInt(cursor.getColumnIndexOrThrow(C_SHORT_VALUE)) : null;
        intValue = !cursor.isNull(cursor.getColumnIndexOrThrow(C_INT_VALUE)) ? cursor.getInt(cursor.getColumnIndexOrThrow(C_INT_VALUE)) : null;
        longValue = !cursor.isNull(cursor.getColumnIndexOrThrow(C_LONG_VALUE)) ? cursor.getLong(cursor.getColumnIndexOrThrow(C_LONG_VALUE)) : null;
        floatValue = !cursor.isNull(cursor.getColumnIndexOrThrow(C_FLOAT_VALUE)) ? cursor.getFloat(cursor.getColumnIndexOrThrow(C_FLOAT_VALUE)) : null;
        doubleValue = cursor.getDouble(cursor.getColumnIndexOrThrow(C_DOUBLE_VALUE));
    }

    public boolean isNewRecord() {
        return getPrimaryKeyId() <= 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @javax.annotation.Nullable
    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(@javax.annotation.Nullable String stringValue) {
        this.stringValue = stringValue;
    }

    @javax.annotation.Nullable
    public java.util.Date getDateValue() {
        if (dateValue != null) {
            return (java.util.Date)dateValue.clone();
        } else {
            return null;
        }
    }

    public void setDateValue(@javax.annotation.Nullable java.util.Date dateValue) {
        if (dateValue != null) {
            this.dateValue = (java.util.Date) dateValue.clone();
        } else {
            this.dateValue = null;
        }
    }

    @javax.annotation.Nullable
    public Boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(@javax.annotation.Nullable Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    @javax.annotation.Nullable
    public Integer getShortValue() {
        return shortValue;
    }

    public void setShortValue(@javax.annotation.Nullable Integer shortValue) {
        this.shortValue = shortValue;
    }

    @javax.annotation.Nullable
    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(@javax.annotation.Nullable Integer intValue) {
        this.intValue = intValue;
    }

    @javax.annotation.Nullable
    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(@javax.annotation.Nullable Long longValue) {
        this.longValue = longValue;
    }

    @javax.annotation.Nullable
    public Float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(@javax.annotation.Nullable Float floatValue) {
        this.floatValue = floatValue;
    }

    @javax.annotation.Nullable
    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(@javax.annotation.Nullable Double doubleValue) {
        this.doubleValue = doubleValue;
    }


}