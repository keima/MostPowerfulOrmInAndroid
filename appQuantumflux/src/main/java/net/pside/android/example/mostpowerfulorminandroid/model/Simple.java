package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import info.quantumflux.model.annotation.Column.PrimaryKey;
import info.quantumflux.model.annotation.Table;

@Table
public class Simple {
    @PrimaryKey
    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
}
