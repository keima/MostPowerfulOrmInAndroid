package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import shillelagh.Column;
import shillelagh.Id;
import shillelagh.Table;

@Table
public class Simple {
    @Id
    long id;

    @Column
    String stringValue;
    @Column
    Date dateValue;

    @Column
    boolean booleanValue;
    @Column(isBlob = true)
    byte[] blobValue;
    @Column
    short shortValue;
    @Column
    int intValue;
    @Column
    long longValue;
    @Column
    float floatValue;
    @Column
    double doubleValue;

    public final long getId() {
        return id;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public byte[] getBlobValue() {
        return blobValue;
    }

    public void setBlobValue(byte[] blobValue) {
        this.blobValue = blobValue;
    }

    public short getShortValue() {
        return shortValue;
    }

    public void setShortValue(short shortValue) {
        this.shortValue = shortValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
