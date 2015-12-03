package net.pside.android.example.mostpowerfulorminandroid.model;

import com.slimgears.slimrepo.core.annotations.GenerateEntity;

import java.util.Date;

@GenerateEntity
public class AbstractSimpleEntity {
    protected int id;

    protected String stringValue;
    Date dateValue;

    int booleanValue; // booleanをintとして使う・・・
    short shortValue;
    int intValue;
    long longValue;
    float floatValue;
    double doubleValue;

}
