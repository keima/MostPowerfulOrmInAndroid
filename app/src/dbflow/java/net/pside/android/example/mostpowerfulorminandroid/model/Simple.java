package net.pside.android.example.mostpowerfulorminandroid.model;

import com.google.common.primitives.Bytes;
import com.grosner.dbflow.annotation.Column;
import com.grosner.dbflow.annotation.Table;
import com.grosner.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by keima on 14/11/18.
 */
@Table
public class Simple extends BaseModel {
    @Column(columnType = Column.PRIMARY_KEY_AUTO_INCREMENT)
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
