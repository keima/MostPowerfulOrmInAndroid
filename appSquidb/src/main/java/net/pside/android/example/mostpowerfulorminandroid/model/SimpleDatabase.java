package net.pside.android.example.mostpowerfulorminandroid.model;

import android.content.Context;

import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.data.adapter.SQLiteDatabaseWrapper;
import com.yahoo.squidb.sql.Table;

public class SimpleDatabase extends SquidDatabase {
    public static final String DB_NAME = "squi.db";
    public static final int DB_VERSION = 1;

    /**
     * Create a new SquidDatabase
     *
     * @param context the Context, must not be null
     */
    public SimpleDatabase(Context context) {
        super(context);
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
    protected boolean onUpgrade(SQLiteDatabaseWrapper db, int oldVersion, int newVersion) {
        return false;
    }
}
