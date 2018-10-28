package net.pside.android.example.mostpowerfulorminandroid.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "simple", indices = {@Index("boolean_value")})
public class Simple {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String stringValue;
    public Date dateValue;

    @ColumnInfo(name = "boolean_value")
    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
}
