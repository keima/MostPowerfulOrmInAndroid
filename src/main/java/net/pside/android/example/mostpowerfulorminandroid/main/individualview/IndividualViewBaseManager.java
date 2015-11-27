/*
 * IndividualViewBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 * 
 */



package net.pside.android.example.mostpowerfulorminandroid.main.individualview;

import net.pside.android.example.mostpowerfulorminandroid.DatabaseManager;
import org.dbtools.android.domain.database.DatabaseWrapper;
import org.dbtools.android.domain.AndroidBaseManagerReadOnly;


@SuppressWarnings("all")
public abstract class IndividualViewBaseManager extends AndroidBaseManagerReadOnly<IndividualView> {

    @javax.inject.Inject
     DatabaseManager databaseManager;
    @javax.inject.Inject
     org.dbtools.android.domain.DBToolsEventBus bus;

    public IndividualViewBaseManager() {
    }

    @javax.annotation.Nonnull
    public String getDatabaseName() {
        return IndividualView.DATABASE;
    }

    @javax.annotation.Nonnull
    public IndividualView newRecord() {
        return new IndividualView();
    }

    @javax.annotation.Nonnull
    public String getTableName() {
        return IndividualView.TABLE;
    }

    @javax.annotation.Nonnull
    public String[] getAllKeys() {
        return IndividualView.ALL_KEYS;
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
        return null;
    }

    @javax.annotation.Nonnull
    public String getDropSql() {
        return IndividualView.DROP_VIEW;
    }

    @javax.annotation.Nonnull
    public String getCreateSql() {
        return IndividualView.CREATE_VIEW;
    }

    public org.dbtools.android.domain.DBToolsEventBus getBus() {
        return bus;
    }

    public void setBus(org.dbtools.android.domain.DBToolsEventBus bus) {
        this.bus = bus;
    }


}