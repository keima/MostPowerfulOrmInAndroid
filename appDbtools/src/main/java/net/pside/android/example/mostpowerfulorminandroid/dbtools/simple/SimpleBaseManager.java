/*
 * SimpleBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 * 
 */



package net.pside.android.example.mostpowerfulorminandroid.dbtools.simple;

import net.pside.android.example.mostpowerfulorminandroid.DatabaseManager;
import org.dbtools.android.domain.AndroidBaseManagerWritable;


@SuppressWarnings("all")
public abstract class SimpleBaseManager extends AndroidBaseManagerWritable<Simple> {


    public SimpleBaseManager(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @javax.annotation.Nonnull
    public String getDatabaseName() {
        return SimpleConst.DATABASE;
    }

    @javax.annotation.Nonnull
    public Simple newRecord() {
        return new Simple();
    }

    @javax.annotation.Nonnull
    public String getTableName() {
        return SimpleConst.TABLE;
    }

    @javax.annotation.Nonnull
    public String[] getAllColumns() {
        return SimpleConst.ALL_COLUMNS;
    }

    @javax.annotation.Nonnull
    public String getPrimaryKey() {
        return SimpleConst.PRIMARY_KEY_COLUMN;
    }

    @javax.annotation.Nonnull
    public String getDropSql() {
        return SimpleConst.DROP_TABLE;
    }

    @javax.annotation.Nonnull
    public String getCreateSql() {
        return SimpleConst.CREATE_TABLE;
    }

    @javax.annotation.Nonnull
    public String getInsertSql() {
        return SimpleConst.INSERT_STATEMENT;
    }

    @javax.annotation.Nonnull
    public String getUpdateSql() {
        return SimpleConst.UPDATE_STATEMENT;
    }


}