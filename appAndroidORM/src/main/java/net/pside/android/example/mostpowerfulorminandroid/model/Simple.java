package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import cn.ieclipse.aorm.annotation.Column;
import cn.ieclipse.aorm.annotation.Table;

@Table(name = "simple")
public class Simple {
    @Column(name = "_id", id = true)
    public long id;

    @Column
    public int intValue;

    @Column
    public String stringValue;

    @Column
    public Date dateValue;

    @Column(name = "booleanValue")
    public boolean booleanValue;

    @Column
    public short shortValue;

    @Column
    public long longValue;

    @Column
    public float floatValue;

    @Column
    public double doubleValue;
}
