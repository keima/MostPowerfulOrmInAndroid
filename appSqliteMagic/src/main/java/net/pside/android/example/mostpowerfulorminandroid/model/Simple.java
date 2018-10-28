package net.pside.android.example.mostpowerfulorminandroid.model;

import com.siimkinks.sqlitemagic.annotation.Column;
import com.siimkinks.sqlitemagic.annotation.Id;
import com.siimkinks.sqlitemagic.annotation.Table;

import java.util.Date;

@Table
public class Simple {
    @Column(value = "_id")
    @Id
    public long id;

    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
}
