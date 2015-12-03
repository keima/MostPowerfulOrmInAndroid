package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import za.co.cporm.model.CPDefaultRecord;
import za.co.cporm.model.annotation.Column.Column;
import za.co.cporm.model.annotation.Table;

@Table
public class Simple extends CPDefaultRecord<Simple> {
    @Column
    public String stringValue;

    @Column
    public Date dateValue;

    @Column
    public boolean booleanValue;

    @Column
    public short shortValue;

    @Column
    public int intValue;

    @Column
    public long longValue;

    @Column
    public float floatValue;

    @Column
    public double doubleValue;
}
