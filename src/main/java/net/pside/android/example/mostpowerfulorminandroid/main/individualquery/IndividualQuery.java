/*
 * IndividualQuery.java
 *
 * Created: 11/27/2015 02:30:54
 */



package net.pside.android.example.mostpowerfulorminandroid.main.individualquery;

import android.database.Cursor;
import android.content.ContentValues;

// todo Replace the following the QUERY sql (The following is a template suggestion for your query)
// todo BE SURE TO KEEP THE OPENING AND CLOSING PARENTHESES (so queries can be run as sub-select: select * from (select a, b from t) )
// todo SUGGESTION: Keep the " AS IndividualQuery.<columnname>" portion of the sql


public class IndividualQuery extends IndividualQueryBaseRecord {

    public static final String QUERY = "(" +
            "SELECT " +
            IndividualQuery.FULL_C_ID + " AS " + IndividualQuery.C_ID + ", " +
            IndividualQuery.FULL_C_NAME + " AS " + IndividualQuery.C_NAME + ", " +
            IndividualQuery.FULL_C_LOCATION_TYPE + " AS " + IndividualQuery.C_LOCATION_TYPE +
            " FROM SOME TABLE(S)" +
            ")";
    public static final String QUERY_RAW = "SELECT * FROM " + QUERY;

    public IndividualQuery(Cursor cursor) {
        setContent(cursor);
    }

    public IndividualQuery(ContentValues values) {
        setContent(values);
    }

    public IndividualQuery() {
    }


}