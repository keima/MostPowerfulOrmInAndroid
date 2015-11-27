
package net.pside.android.example.mostpowerfulorminandroid;

import android.util.Log;
import android.app.Application;
import org.dbtools.android.domain.AndroidDatabase;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.dbtools.android.domain.database.DatabaseWrapper;
import org.dbtools.android.domain.database.AndroidDatabaseWrapper;


@Singleton
public class DatabaseManager extends DatabaseBaseManager {

    @Inject
     Application application;
    public static final int MAIN_VERSION = 1;
    public static final int MAIN_VIEWS_VERSION = 1;

    @javax.inject.Inject
    public DatabaseManager() {
    }

    public void identifyDatabases() {
        addDatabase(application, MAIN_DATABASE_NAME, MAIN_VERSION, MAIN_VIEWS_VERSION);
    }

    public DatabaseWrapper createNewDatabaseWrapper(AndroidDatabase androidDatabase) {
        return new AndroidDatabaseWrapper(androidDatabase.getPath());
    }

    public void onUpgrade(AndroidDatabase androidDatabase, int oldVersion, int newVersion) {
        String databaseName = androidDatabase.getName();
        Log.i(TAG, "Upgrading database [" + databaseName + "] from version " + oldVersion + " to " + newVersion);
    }

    public void onUpgradeViews(AndroidDatabase androidDatabase, int oldVersion, int newVersion) {
        String databaseName = androidDatabase.getName();
        Log.i(TAG, "Upgrading database [" + databaseName + "] VIEWS from version " + oldVersion + " to " + newVersion);
        // automatically drop/create views
        super.onUpgradeViews(androidDatabase, oldVersion, newVersion);
    }


}