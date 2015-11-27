/*
 * Individual.java
 *
 * Created: 11/27/2015 02:30:54
 */



package net.pside.android.example.mostpowerfulorminandroid.main.individual;

import android.database.Cursor;
import android.content.ContentValues;


public class Individual extends IndividualBaseRecord {


    public Individual(Cursor cursor) {
        setContent(cursor);
    }

    public Individual(ContentValues values) {
        setContent(values);
    }

    public Individual() {
    }


}