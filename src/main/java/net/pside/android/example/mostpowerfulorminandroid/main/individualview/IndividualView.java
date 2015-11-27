/*
 * IndividualView.java
 *
 * Created: 11/27/2015 02:30:54
 */



package net.pside.android.example.mostpowerfulorminandroid.main.individualview;

import android.database.Cursor;
import android.content.ContentValues;

// todo Replace the following the CREATE_VIEW sql (The following is a template suggestion for your view)
// todo SUGGESTION: Keep the " AS IndividualView.<columnname>" portion of the sql


public class IndividualView extends IndividualViewBaseRecord {

    public static final String DROP_VIEW;
    public static final String CREATE_VIEW = "CREATE VIEW IF NOT EXISTS " + IndividualView.TABLE + " AS SELECT " +
            IndividualView.FULL_C_ID + " AS " + IndividualView.C_ID + ", " +
            IndividualView.FULL_C_NAME + " AS " + IndividualView.C_NAME +
            " FROM " + IndividualView.TABLE;

    static {
        DROP_VIEW = "DROP VIEW IF EXISTS " + IndividualView.TABLE + ";";

    }

    public IndividualView(Cursor cursor) {
        setContent(cursor);
    }

    public IndividualView(ContentValues values) {
        setContent(values);
    }

    public IndividualView() {
    }

    public String getDropSql() {
        return DROP_VIEW;
    }

    public String getCreateSql() {
        return CREATE_VIEW;
    }


}