package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import fr.xebia.android.freezer.annotations.Id;
import fr.xebia.android.freezer.annotations.Model;

@Model
public class Simple {
    @Id
    public long id;

    public String stringValue;
    public Date dateValue;

    public boolean booleanValue;
    public int shortValue; // TODO: short is not supported.
    public int intValue;
    public long longValue;
    public float floatValue;
    public double doubleValue;
}
