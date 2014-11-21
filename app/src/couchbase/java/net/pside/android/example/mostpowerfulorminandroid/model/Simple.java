package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

/**
 * Created by keima on 14/11/18.
 */
public class Simple {
    public static final String DATABASE_NAME = "Simple";

    public static final String STRING_VALUE = "stringValue";
    public static final String DATE_VALUE = "dateValue";

    public static final String BOOLEAN_VALUE = "booleanValue";
    public static final String BLOB_VALUE = "blobValue";
    public static final String SHORT_VALUE = "shortValue";
    public static final String INT_VALUE = "intValue";
    public static final String LONG_VALUE = "longValue";
    public static final String FLOAT_VALUE = "floatValue";
    public static final String DOUBLE_VALUE = "doubleValue";

    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public byte[] blobValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;

    // couchbase liteはドキュメント型ORM


}
