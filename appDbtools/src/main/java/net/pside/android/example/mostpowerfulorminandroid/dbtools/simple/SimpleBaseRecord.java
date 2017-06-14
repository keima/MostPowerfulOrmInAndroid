/*
 * SimpleBaseRecord.java
 *
 * GENERATED FILE - DO NOT EDIT
 * CHECKSTYLE:OFF
 * 
 */



package net.pside.android.example.mostpowerfulorminandroid.dbtools.simple;

import org.dbtools.android.domain.AndroidBaseRecord;
import org.dbtools.android.domain.database.statement.StatementWrapper;
import org.dbtools.android.domain.database.contentvalues.DBToolsContentValues;
import android.database.Cursor;


@SuppressWarnings("all")
public abstract class SimpleBaseRecord extends AndroidBaseRecord {

    private long id = 0;
    private String stringValue = null;
    private org.joda.time.DateTime dateValue = null;
    private Boolean booleanValue = null;
    private Integer shortValue = null;
    private Integer intValue = null;
    private Long longValue = null;
    private Float floatValue = null;
    private Double doubleValue = null;

    public SimpleBaseRecord() {
    }

    @Override
    public String getIdColumnName() {
        return SimpleConst.C_ID;
    }

    @Override
    public long getPrimaryKeyId() {
        return id;
    }

    @Override
    public void setPrimaryKeyId(long id) {
        this.id = id;
    }

    @Override
    public String[] getAllColumns() {
        return SimpleConst.ALL_COLUMNS.clone();
    }

    public String[] getAllColumnsFull() {
        return SimpleConst.ALL_COLUMNS_FULL.clone();
    }

    @Override
    public void getContentValues(DBToolsContentValues values) {
        values.put(SimpleConst.C_STRING_VALUE, stringValue);
        values.put(SimpleConst.C_DATE_VALUE, org.dbtools.android.domain.date.DBToolsJodaFormatter.dateTimeToDBString(dateValue));
        values.put(SimpleConst.C_BOOLEAN_VALUE, booleanValue != null ? (booleanValue ? 1 : 0) : 0);
        values.put(SimpleConst.C_SHORT_VALUE, shortValue);
        values.put(SimpleConst.C_INT_VALUE, intValue);
        values.put(SimpleConst.C_LONG_VALUE, longValue);
        values.put(SimpleConst.C_FLOAT_VALUE, floatValue);
        values.put(SimpleConst.C_DOUBLE_VALUE, doubleValue);
    }

    @Override
    public Object[] getValues() {
        Object[] values = new Object[]{
            id,
            stringValue,
            org.dbtools.android.domain.date.DBToolsJodaFormatter.dateTimeToDBString(dateValue),
            booleanValue != null ? (booleanValue ? 1 : 0) : 0,
            shortValue,
            intValue,
            longValue,
            floatValue,
            doubleValue,
        };
        return values;
    }

    public Simple copy() {
        Simple copy = new Simple();
        copy.setId(id);
        copy.setStringValue(stringValue);
        copy.setDateValue(dateValue);
        copy.setBooleanValue(booleanValue);
        copy.setShortValue(shortValue);
        copy.setIntValue(intValue);
        copy.setLongValue(longValue);
        copy.setFloatValue(floatValue);
        copy.setDoubleValue(doubleValue);
        return copy;
    }

    @Override
    public void bindInsertStatement(StatementWrapper statement) {
        if (stringValue != null) {
            statement.bindString(1, stringValue);
        } else {
            statement.bindNull(1);
        }
        if (dateValue != null) {
            statement.bindString(2, org.dbtools.android.domain.date.DBToolsJodaFormatter.dateTimeToDBString(dateValue));
        } else {
            statement.bindNull(2);
        }
        if (booleanValue != null) {
            statement.bindLong(3, booleanValue != null ? (booleanValue ? 1 : 0) : 0);
        } else {
            statement.bindNull(3);
        }
        if (shortValue != null) {
            statement.bindLong(4, shortValue);
        } else {
            statement.bindNull(4);
        }
        if (intValue != null) {
            statement.bindLong(5, intValue);
        } else {
            statement.bindNull(5);
        }
        if (longValue != null) {
            statement.bindLong(6, longValue);
        } else {
            statement.bindNull(6);
        }
        if (floatValue != null) {
            statement.bindDouble(7, floatValue);
        } else {
            statement.bindNull(7);
        }
        if (doubleValue != null) {
            statement.bindDouble(8, doubleValue);
        } else {
            statement.bindNull(8);
        }
    }

    @Override
    public void bindUpdateStatement(StatementWrapper statement) {
        if (stringValue != null) {
            statement.bindString(1, stringValue);
        } else {
            statement.bindNull(1);
        }
        if (dateValue != null) {
            statement.bindString(2, org.dbtools.android.domain.date.DBToolsJodaFormatter.dateTimeToDBString(dateValue));
        } else {
            statement.bindNull(2);
        }
        if (booleanValue != null) {
            statement.bindLong(3, booleanValue != null ? (booleanValue ? 1 : 0) : 0);
        } else {
            statement.bindNull(3);
        }
        if (shortValue != null) {
            statement.bindLong(4, shortValue);
        } else {
            statement.bindNull(4);
        }
        if (intValue != null) {
            statement.bindLong(5, intValue);
        } else {
            statement.bindNull(5);
        }
        if (longValue != null) {
            statement.bindLong(6, longValue);
        } else {
            statement.bindNull(6);
        }
        if (floatValue != null) {
            statement.bindDouble(7, floatValue);
        } else {
            statement.bindNull(7);
        }
        if (doubleValue != null) {
            statement.bindDouble(8, doubleValue);
        } else {
            statement.bindNull(8);
        }
        statement.bindLong(9, id);
    }

    public void setContent(DBToolsContentValues values) {
        stringValue = values.getAsString(SimpleConst.C_STRING_VALUE);
        dateValue = org.dbtools.android.domain.date.DBToolsJodaFormatter.dbStringToDateTime(values.getAsString(SimpleConst.C_DATE_VALUE));
        booleanValue = values.getAsBoolean(SimpleConst.C_BOOLEAN_VALUE);
        shortValue = values.getAsInteger(SimpleConst.C_SHORT_VALUE);
        intValue = values.getAsInteger(SimpleConst.C_INT_VALUE);
        longValue = values.getAsLong(SimpleConst.C_LONG_VALUE);
        floatValue = values.getAsFloat(SimpleConst.C_FLOAT_VALUE);
        doubleValue = values.getAsDouble(SimpleConst.C_DOUBLE_VALUE);
    }

    @Override
    public void setContent(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndexOrThrow(SimpleConst.C_ID));
        stringValue = !cursor.isNull(cursor.getColumnIndexOrThrow(SimpleConst.C_STRING_VALUE)) ? cursor.getString(cursor.getColumnIndexOrThrow(SimpleConst.C_STRING_VALUE)) : null;
        dateValue = org.dbtools.android.domain.date.DBToolsJodaFormatter.dbStringToDateTime(cursor.getString(cursor.getColumnIndexOrThrow(SimpleConst.C_DATE_VALUE)));
        booleanValue = !cursor.isNull(cursor.getColumnIndexOrThrow(SimpleConst.C_BOOLEAN_VALUE)) ? cursor.getInt(cursor.getColumnIndexOrThrow(SimpleConst.C_BOOLEAN_VALUE)) != 0 ? true : false : null;
        shortValue = !cursor.isNull(cursor.getColumnIndexOrThrow(SimpleConst.C_SHORT_VALUE)) ? cursor.getInt(cursor.getColumnIndexOrThrow(SimpleConst.C_SHORT_VALUE)) : null;
        intValue = !cursor.isNull(cursor.getColumnIndexOrThrow(SimpleConst.C_INT_VALUE)) ? cursor.getInt(cursor.getColumnIndexOrThrow(SimpleConst.C_INT_VALUE)) : null;
        longValue = !cursor.isNull(cursor.getColumnIndexOrThrow(SimpleConst.C_LONG_VALUE)) ? cursor.getLong(cursor.getColumnIndexOrThrow(SimpleConst.C_LONG_VALUE)) : null;
        floatValue = !cursor.isNull(cursor.getColumnIndexOrThrow(SimpleConst.C_FLOAT_VALUE)) ? cursor.getFloat(cursor.getColumnIndexOrThrow(SimpleConst.C_FLOAT_VALUE)) : null;
        doubleValue = !cursor.isNull(cursor.getColumnIndexOrThrow(SimpleConst.C_DOUBLE_VALUE)) ? cursor.getDouble(cursor.getColumnIndexOrThrow(SimpleConst.C_DOUBLE_VALUE)) : null;
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
    public org.joda.time.DateTime getDateValue() {
        return dateValue;
    }

    public void setDateValue(@javax.annotation.Nullable org.joda.time.DateTime dateValue) {
        this.dateValue = dateValue;
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