package net.pside.android.example.mostpowerfulorminandroid.model;

import com.hendrix.triorm.TriData;
import com.hendrix.triorm.annotations.TriTable;

import java.util.Date;

@TriTable(dbName = "triorm", tableName = "simple")
public class Simple extends TriData{
    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;

    public Simple() {
    }

    public Simple(String id) {
        super(id);
    }
}
