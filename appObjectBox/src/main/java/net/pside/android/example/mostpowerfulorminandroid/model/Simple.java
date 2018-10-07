package net.pside.android.example.mostpowerfulorminandroid.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

import java.util.Date;

@Entity
public class Simple {

    @Id
    public long id;

    public String stringValue;
    public Date dateValue;

    @Index public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
}
