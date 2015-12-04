package net.pside.android.example.mostpowerfulorminandroid.model;

import com.sabres.SabresObject;

import java.util.Date;

public class Simple extends SabresObject {
    public static final String KEY_STRING = "stringValue";
    public static final String KEY_DATE = "dateValue";
    public static final String KEY_BOOLEAN = "dateBoolean";
    public static final String KEY_SHORT = "dateShort";
    public static final String KEY_INT = "intValue";
    public static final String KEY_LONG = "longValue";
    public static final String KEY_FLOAT = "floatValue";
    public static final String KEY_DOUBLE = "doubleValue";

    public String getStringValue() {
        return getString(KEY_STRING);
    }

    public void setStringValue(String stringValue) {
        put(KEY_STRING, stringValue);
    }

    public Date getDateValue() {
        return getDate(KEY_DATE);
    }

    public void setDateValue(Date dateValue) {
        put(KEY_DATE, dateValue);
    }

    public boolean isBooleanValue() {
        return getBoolean(KEY_BOOLEAN);
    }

    public void setBooleanValue(boolean booleanValue) {
        put(KEY_BOOLEAN, booleanValue);
    }

    public short getShortValue() {
        return getShort(KEY_SHORT);
    }

    public void setShortValue(short shortValue) {
        put(KEY_SHORT, shortValue);
    }

    public int getIntValue() {
        return getInt(KEY_INT);
    }

    public void setIntValue(int intValue) {
        put(KEY_INT, intValue);
    }

    public long getLongValue() {
        return getLong(KEY_LONG);
    }

    public void setLongValue(long longValue) {
        put(KEY_LONG, longValue);
    }

    public float getFloatValue() {
        return getFloat(KEY_FLOAT);
    }

    public void setFloatValue(float floatValue) {
        put(KEY_FLOAT, floatValue);
    }

    public double getDoubleValue() {
        return getDouble(KEY_DOUBLE);
    }

    public void setDoubleValue(double doubleValue) {
        put(KEY_DOUBLE,doubleValue);
    }
}
