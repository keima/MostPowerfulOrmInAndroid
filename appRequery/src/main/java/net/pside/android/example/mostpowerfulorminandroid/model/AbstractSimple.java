package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.Date;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;

@Entity
abstract class AbstractSimple {
    @Key
    @Generated
    long id;

    String stringValue;
    Date dateValue;

    boolean booleanValue;
    short shortValue;
    int intValue;
    long longValue;
    float floatValue;
    double doubleValue;
}
