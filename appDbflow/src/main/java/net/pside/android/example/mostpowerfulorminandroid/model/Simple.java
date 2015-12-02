package net.pside.android.example.mostpowerfulorminandroid.model;

import android.provider.BaseColumns;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(databaseName = SimpleDatabase.NAME)
public class Simple extends BaseModel {
    @Column(name = BaseColumns._ID)
    @PrimaryKey(autoincrement = true)
    long id;

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

    public Simple() {
        // DBFlowが無引数コンストラクタを要求するため
    }
}
