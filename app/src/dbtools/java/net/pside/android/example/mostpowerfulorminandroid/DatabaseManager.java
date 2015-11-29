
package net.pside.android.example.mostpowerfulorminandroid;

import android.util.Log;
import android.app.Application;
import org.dbtools.android.domain.AndroidDatabase;
import org.dbtools.android.domain.database.DatabaseWrapper;
import org.dbtools.android.domain.database.AndroidDatabaseWrapper;


public class DatabaseManager extends DatabaseBaseManager {

    private Application application;
    public static final int DBTOOLS_VERSION = 1;
    public static final int DBTOOLS_VIEWS_VERSION = 1;

    public void identifyDatabases() {
        addDatabase(application, DBTOOLS_DATABASE_NAME, DBTOOLS_VERSION, DBTOOLS_VIEWS_VERSION);
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

    public void setApplication(Application application) {
        this.application = application;
    }


}