/*
 * Simple.java
 *
 * Created: 12/06/2015 04:15:26
 */



package net.pside.android.example.mostpowerfulorminandroid.dbtools.simple;

import android.database.Cursor;
import android.content.ContentValues;


public class Simple extends SimpleBaseRecord {


    public Simple(Cursor cursor) {
        setContent(cursor);
    }

    public Simple(ContentValues values) {
        setContent(values);
    }

    public Simple() {
    }


}