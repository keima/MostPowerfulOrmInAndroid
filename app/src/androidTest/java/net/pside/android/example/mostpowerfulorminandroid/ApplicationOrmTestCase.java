package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.io.File;
import java.util.Date;

public abstract class ApplicationOrmTestCase extends ApplicationTestCase<Application> implements IOrmTestCase {

    public static String MSG_LOGGER_INITIALIZE(boolean isBulkMode) {
        return "Insert on " + BuildConfig.FLAVOR.toUpperCase() +
                " (BulkMode:" + (isBulkMode ? "ON" : "OFF") + ")";
    }

    public ApplicationOrmTestCase(Class<Application> applicationClass) {
        super(applicationClass);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        renameDatabase(getDatabaseName());
    }

    protected void deleteDatabaseIfNeeded(String dbName) {
        if (dbName == null) return;

        File databasePath = mContext.getDatabasePath(dbName);
        if (databasePath != null && databasePath.exists()) {
            mContext.deleteDatabase(dbName);
        }
    }

    protected void renameDatabase(String dbName) {
        if (dbName == null) return;

        File dbPath = mContext.getDatabasePath(dbName);
        if (dbPath != null && dbPath.exists()) {
            File renameTo = new File(dbPath.getAbsolutePath() + "-" + new Date().getTime());
            boolean result = dbPath.renameTo(renameTo);

            Log.d("AppOrmTestCase", (result ? "Moved:" : "FAILED:") + dbPath.toString() + " -> " + renameTo.toString());
        }
    }
}
