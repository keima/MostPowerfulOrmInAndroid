package net.pside.android.example.mostpowerfulorminandroid.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.sql.SQLException;

public class MySQLiteOpenHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "ormlite.db";
    public static final int DATABASE_VERSION = 1;

    private Dao<Simple, Long> simpleDao;

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Simple.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        for (;oldVersion <= newVersion; oldVersion++) {
            System.out.println("oldVersion: " + oldVersion);
        }
    }

    public Dao<Simple, Long> getSimpleDao() throws SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(Simple.class);
        }
        return simpleDao;
    }

    @Override
    public void close() {
        super.close();
        simpleDao = null;
    }
}
