package net.pside.android.example.mostpowerfulorminandroid.model;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by keima on 14/11/18.
 */
@Table(name = "Simple", id = BaseColumns._ID)
public class Simple extends Model {
    // idは自動で採番

    @Column
    public String stringValue;

    @Column
    public Date dateValue;

    @Column
    public boolean booleanValue;
    @Column
    public byte[] blobValue;
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
