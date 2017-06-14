/*
 * DatabaseBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 *
 */



package net.pside.android.example.mostpowerfulorminandroid;

import org.dbtools.android.domain.AndroidDatabase;
import org.dbtools.android.domain.AndroidBaseManager;
import org.dbtools.android.domain.AndroidDatabaseManager;
import org.dbtools.android.domain.database.DatabaseWrapper;
import org.dbtools.android.domain.config.DatabaseConfig;


@SuppressWarnings("all")
public abstract class DatabaseBaseManager extends AndroidDatabaseManager {


    public DatabaseBaseManager(DatabaseConfig databaseConfig) {
        super(databaseConfig);
    }

    public void createDbtoolsTables(@javax.annotation.Nonnull AndroidDatabase androidDatabase) {
        DatabaseWrapper database = androidDatabase.getDatabaseWrapper();
        database.beginTransaction();
        
        // Enum Tables
        
        // Tables
        AndroidBaseManager.createTable(database, net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.SimpleConst.CREATE_TABLE);
        
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void onCreate(@javax.annotation.Nonnull AndroidDatabase androidDatabase) {
        getLogger().i(TAG, "Creating database: " + androidDatabase.getName());
        if (androidDatabase.getName().equals(DatabaseManagerConst.DBTOOLS_DATABASE_NAME)) {
            createDbtoolsTables(androidDatabase);
        }
    }

    public void onCreateViews(@javax.annotation.Nonnull AndroidDatabase androidDatabase) {
        getLogger().i(TAG, "Creating database views: " + androidDatabase.getName());
    }

    public void onDropViews(@javax.annotation.Nonnull AndroidDatabase androidDatabase) {
        getLogger().i(TAG, "Dropping database views: " + androidDatabase.getName());
    }


}