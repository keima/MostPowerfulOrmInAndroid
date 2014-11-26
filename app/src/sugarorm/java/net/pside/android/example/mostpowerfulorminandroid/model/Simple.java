package net.pside.android.example.mostpowerfulorminandroid.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by keima on 14/11/18.
 */
public class Simple extends SugarRecord<Simple> {
    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public byte[] blobValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;

    public Simple() {
    }
}
