package net.pside.android.example.mostpowerfulorminandroid.model;

import com.reactiveandroid.Model;
import com.reactiveandroid.annotation.Column;
import com.reactiveandroid.annotation.PrimaryKey;
import com.reactiveandroid.annotation.Table;

import net.pside.android.example.mostpowerfulorminandroid.AppDatabase;

import java.util.Date;

@Table(database = AppDatabase.class)
public class Simple extends Model {
    @PrimaryKey
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
