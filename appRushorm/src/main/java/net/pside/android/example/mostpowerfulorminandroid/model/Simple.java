package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import co.uk.rushorm.core.RushObject;

public class Simple extends RushObject {
    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;

    public Simple() {
        // required by rushorm
    }
}
