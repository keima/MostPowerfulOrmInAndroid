package net.pside.android.example.mostpowerfulorminandroid.model;

import android.content.Context;

import com.yahoo.squidb.android.AndroidOpenHelper;
import com.yahoo.squidb.data.ISQLiteDatabase;
import com.yahoo.squidb.data.ISQLiteOpenHelper;
import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.sql.Table;

public class SimpleDatabase extends SquidDatabase {
    public static final String DB_NAME = "squi.db";
    public static final int DB_VERSION = 1;

    private final Context context;

    /**
     * Create a new SquidDatabase
     *
     * @param context the Context, must not be null
     */
    public SimpleDatabase(Context context) {
        this.context = context;
    }

    @Override
    public String getName() {
        return DB_NAME;
    }

    @Override
    protected int getVersion() {
        return DB_VERSION;
    }

    @Override
    protected Table[] getTables() {
        return new Table[]{
                Simple.TABLE,
        };
    }

    @Override
    protected ISQLiteOpenHelper createOpenHelper(String databaseName, OpenHelperDelegate delegate, int version) {
        return new AndroidOpenHelper(context, databaseName, delegate, version);
    }

    @Override
    protected boolean onUpgrade(ISQLiteDatabase db, int oldVersion, int newVersion) {
        return false;
    }
}
