package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import me.himanshusoni.quantumflux.model.annotation.Column.PrimaryKey;
import me.himanshusoni.quantumflux.model.annotation.Table;


@Table
public class Simple {
    @PrimaryKey
    Long simpleId;

    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
}
