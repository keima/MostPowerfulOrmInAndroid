package net.pside.android.example.mostpowerfulorminandroid.model;

import com.easyliteorm.annotation.Entity;
import com.easyliteorm.annotation.GenerationType;
import com.easyliteorm.annotation.Id;

import java.util.Date;

@Entity
public class Simple {
    @Id(strategy = GenerationType.AUTO)
    public int id;

    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
}
