package net.pside.android.example.mostpowerfulorminandroid.model;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.Table;

import java.util.Date;

@Table
public class Simple {

    @Column
    public long id;

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
