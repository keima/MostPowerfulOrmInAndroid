/*
 * Simple.java
 *
 * Created: 12/02/2015 12:08:58
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