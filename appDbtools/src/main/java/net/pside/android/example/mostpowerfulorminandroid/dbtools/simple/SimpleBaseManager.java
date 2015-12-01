/*
 * SimpleBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 * 
 */



package net.pside.android.example.mostpowerfulorminandroid.dbtools.simple;

import net.pside.android.example.mostpowerfulorminandroid.DatabaseManager;
import org.dbtools.android.domain.database.DatabaseWrapper;
import org.dbtools.android.domain.RxAndroidBaseManagerWritable;


@SuppressWarnings("all")
public abstract class SimpleBaseManager extends RxAndroidBaseManagerWritable<Simple> {

    @javax.inject.Inject
     DatabaseManager databaseManager;

    public SimpleBaseManager() {
    }

    @javax.annotation.Nonnull
    public String getDatabaseName() {
        return Simple.DATABASE;
    }

    @javax.annotation.Nonnull
    public Simple newRecord() {
        return new Simple();
    }

    @javax.annotation.Nonnull
    public String getTableName() {
        return Simple.TABLE;
    }

    @javax.annotation.Nonnull
    public String[] getAllKeys() {
        return Simple.ALL_KEYS;
    }

    @javax.annotation.Nonnull
    public DatabaseWrapper getReadableDatabase(@javax.annotation.Nonnull String databaseName) {
        return databaseManager.getReadableDatabase(databaseName);
    }

    @javax.annotation.Nonnull
    public DatabaseWrapper getReadableDatabase() {
        return databaseManager.getReadableDatabase(getDatabaseName());
    }

    @javax.annotation.Nonnull
    public DatabaseWrapper getWritableDatabase(@javax.annotation.Nonnull String databaseName) {
        return databaseManager.getWritableDatabase(databaseName);
    }

    @javax.annotation.Nonnull
    public DatabaseWrapper getWritableDatabase() {
        return databaseManager.getWritableDatabase(getDatabaseName());
    }

    @javax.annotation.Nonnull
    public org.dbtools.android.domain.AndroidDatabase getAndroidDatabase(@javax.annotation.Nonnull String databaseName) {
        return databaseManager.getDatabase(databaseName);
    }

    @javax.annotation.Nonnull
    public String getPrimaryKey() {
        return Simple.PRIMARY_KEY_COLUMN;
    }

    @javax.annotation.Nonnull
    public String getDropSql() {
        return Simple.DROP_TABLE;
    }

    @javax.annotation.Nonnull
    public String getCreateSql() {
        return Simple.CREATE_TABLE;
    }


}