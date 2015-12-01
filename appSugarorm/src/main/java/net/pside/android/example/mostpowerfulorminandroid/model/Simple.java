package net.pside.android.example.mostpowerfulorminandroid.model;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.util.Date;

@Table
public class Simple {
    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public byte[] blobValue;
    public short shortValue;
    public int intValue;

    @Unique
    public long longValue;

    public float floatValue;
    public double doubleValue;

    public Simple() {
    }
}
