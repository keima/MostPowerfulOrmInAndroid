package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import cn.ieclipse.aorm.annotation.Column;
import cn.ieclipse.aorm.annotation.Table;

@Table(name = "simple")
public class Simple {
    @Column(name = "_id", id = true)
    public int intValue;

    @Column
    public String stringValue;

    @Column
    public Date dateValue;

    @Column
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
