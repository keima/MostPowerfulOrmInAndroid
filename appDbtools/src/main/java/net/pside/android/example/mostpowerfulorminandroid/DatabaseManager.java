
package net.pside.android.example.mostpowerfulorminandroid;

import org.dbtools.android.domain.AndroidDatabase;
import org.dbtools.android.domain.config.DatabaseConfig;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class DatabaseManager extends DatabaseBaseManager {

    public static final int DBTOOLS_TABLES_VERSION = 1;
    public static final int DBTOOLS_VIEWS_VERSION = 1;

    @javax.inject.Inject
    public DatabaseManager(DatabaseConfig databaseConfig) {
        super(databaseConfig);
    }

    public void onUpgrade(AndroidDatabase androidDatabase, int oldVersion, int newVersion) {
        String databaseName = androidDatabase.getName();
        getLogger().i(TAG, "Upgrading database [" + databaseName + "] from version " + oldVersion + " to " + newVersion);
    }

    public void onUpgradeViews(AndroidDatabase androidDatabase, int oldVersion, int newVersion) {
        String databaseName = androidDatabase.getName();
        getLogger().i(TAG, "Upgrading database [" + databaseName + "] VIEWS from version " + oldVersion + " to " + newVersion);
        // automatically drop/create views
        super.onUpgradeViews(androidDatabase, oldVersion, newVersion);
    }


}