package net.pside.android.example.mostpowerfulorminandroid.model;

import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import java.util.Date;

import static net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable.COLUMN_BOOLEAN;
import static net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable.COLUMN_DATE;
import static net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable.COLUMN_DOUBLE;
import static net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable.COLUMN_FLOAT;
import static net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable.COLUMN_ID;
import static net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable.COLUMN_INT;
import static net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable.COLUMN_LONG;
import static net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable.COLUMN_SHORT;
import static net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable.COLUMN_STRING;

@StorIOSQLiteType(table = SimplesTable.TABLE)
@StorIOContentResolverType(uri = SimplesTable.URI)
public class Simple {
    @StorIOSQLiteColumn(name = COLUMN_ID, key = true)
    @StorIOContentResolverColumn(name = COLUMN_ID, key = true)
    public long id;

    @StorIOSQLiteColumn(name = COLUMN_STRING)
    @StorIOContentResolverColumn(name = COLUMN_STRING)
    public String stringValue;

    @StorIOSQLiteColumn(name = COLUMN_DATE)
    @StorIOContentResolverColumn(name = COLUMN_DATE)
    long dateValue;

    @StorIOSQLiteColumn(name = COLUMN_BOOLEAN)
    @StorIOContentResolverColumn(name = COLUMN_BOOLEAN)
    public boolean booleanValue;

    @StorIOSQLiteColumn(name = COLUMN_SHORT)
    @StorIOContentResolverColumn(name = COLUMN_SHORT)
    public short shortValue;

    @StorIOSQLiteColumn(name = COLUMN_INT)
    @StorIOContentResolverColumn(name = COLUMN_INT)
    public int intValue;

    @StorIOSQLiteColumn(name = COLUMN_LONG)
    @StorIOContentResolverColumn(name = COLUMN_LONG)
    public long longValue;

    @StorIOSQLiteColumn(name = COLUMN_FLOAT)
    @StorIOContentResolverColumn(name = COLUMN_FLOAT)
    public float floatValue;

    @StorIOSQLiteColumn(name = COLUMN_DOUBLE)
    @StorIOContentResolverColumn(name = COLUMN_DOUBLE)
    public double doubleValue;

    public Simple() {
        // reserved for storio
    }

    public Simple(long id) {
        this.id = id;
    }

    public Date dateValue() {
        return new Date(dateValue);
    }

    public void dateValue(Date value) {
        dateValue = value.getTime();
    }
}
