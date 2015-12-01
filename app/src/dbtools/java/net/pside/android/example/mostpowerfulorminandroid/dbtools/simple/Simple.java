/*
 * Simple.java
 *
 * Created: 11/29/2015 07:29:54
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