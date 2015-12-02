package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

@Table("Simple")
public class Simple extends Model {
    @Column("stringValue")
    public String stringValue;

    @Column("dateValue")
    public Date dateValue;

    @Column("booleanValue")
    public Boolean booleanValue;

    @Column("shortValue")
    public Short shortValue;

    @Column("intValue")
    public Integer intValue;

    @Column("longValue")
    public Long longValue;

    @Column("floatValue")
    public Float floatValue;

    @Column("doubleValue")
    public Double doubleValue;
}
