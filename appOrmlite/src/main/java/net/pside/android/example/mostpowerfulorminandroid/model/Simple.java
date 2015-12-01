package net.pside.android.example.mostpowerfulorminandroid.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class Simple {
    @DatabaseField(generatedId = true, columnName = "_id")
    public long id;

    @DatabaseField
    public String stringValue;
    @DatabaseField
    public Date dateValue;

    @DatabaseField
    public boolean booleanValue;
    @DatabaseField
    public short shortValue;
    @DatabaseField
    public int intValue;
    @DatabaseField
    public long longValue;
    @DatabaseField
    public float floatValue;
    @DatabaseField
    public double doubleValue;

    public Simple() {
        // ORMLiteは無引数のConstructorを要求する
    }
}
