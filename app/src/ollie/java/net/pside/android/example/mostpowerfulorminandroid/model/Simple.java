package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

/**
 * Created by keima on 14/11/18.
 */
@Table("Simple")
public class Simple extends Model {
    public static final String STRING_VALUE = "stringValue";
    public static final String DATE_VALUE = "dateValue";

    public static final String BOOLEAN_VALUE = "booleanValue";
    public static final String BLOB_VALUE = "blobValue";
    public static final String SHORT_VALUE = "shortValue";
    public static final String INT_VALUE = "intValue";
    public static final String LONG_VALUE = "longValue";
    public static final String FLOAT_VALUE = "floatValue";
    public static final String DOUBLE_VALUE = "doubleValue";


    @Column(STRING_VALUE)
    public String stringValue;
    @Column(DATE_VALUE)
    public Date dateValue;

    @Column(BOOLEAN_VALUE)
    public boolean booleanValue;
    @Column(BLOB_VALUE)
    public byte[] blobValue;
    @Column(SHORT_VALUE)
    public short shortValue;
    @Column(INT_VALUE)
    public int intValue;
    @Column(LONG_VALUE)
    public long longValue;
    @Column(FLOAT_VALUE)
    public float floatValue;
    @Column(DOUBLE_VALUE)
    public double doubleValue;

}
