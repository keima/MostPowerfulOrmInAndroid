package net.pside.android.example.mostpowerfulorminandroid.model;

import org.bitbucket.txdrive.electra.annotation.Column;
import org.bitbucket.txdrive.electra.annotation.Entity;
import org.bitbucket.txdrive.electra.annotation.Key;

import java.util.Date;

@Entity
public class Simple {

    private String stringValue;

    private Date dateValue;

    private boolean booleanValue;

    private short shortValue;

    private int intValue;

    @Column("_id")
    @Key
    private long longValue;

    private float floatValue;

    private double doubleValue;

    public Simple() {
        // default costructor is mandatory
    }

    // getter, setters is mandatory

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
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
}
