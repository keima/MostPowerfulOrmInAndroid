package net.pside.android.example.mostpowerfulorminandroid.model;

import org.brightify.torch.annotation.Entity;
import org.brightify.torch.annotation.Id;

@Entity
public class Simple {
    @Id
    Long id;

    public String stringValue;

//    現時点で未サポート・・・
//    public Date dateValue;

    public boolean booleanValue;
    public short shortValue;
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
}
