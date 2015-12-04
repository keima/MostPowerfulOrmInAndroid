package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

public class Simple {
    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;

    public String getSnappyKey() {
        return String.format("Simple:%d:%05d", booleanValue ? 1 : 0, intValue);
    }
}
