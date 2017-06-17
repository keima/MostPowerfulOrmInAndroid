package net.pside.android.example.mostpowerfulorminandroid.model;

import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.ModelMethod;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

import java.util.Date;

@TableModelSpec(className = "Simple", tableName = "simple")
public class SimpleSpec {
    @PrimaryKey
    @ColumnSpec(name = "_id")
    public int id;

    public String stringValue;
    private Long dateValueAsLong;
    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;

    @ModelMethod
    public static Date getDateValue(Simple simple) {
        return new Date(simple.getDateValueAsLong());
    }

    @ModelMethod
    public static void setDateValue(Simple simple, Date value) {
        simple.setDateValueAsLong(value.getTime());
    }
}
