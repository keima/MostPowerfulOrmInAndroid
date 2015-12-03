package net.pside.android.example.mostpowerfulorminandroid.model;

import com.yahoo.squidb.annotations.TableModelSpec;

import java.util.Date;

@TableModelSpec(className = "Simple", tableName = "simple")
public class SimpleSpec {
    public String stringValue;

//    Date型対応してないの・・・
//    public Date dateValue;

    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
}
