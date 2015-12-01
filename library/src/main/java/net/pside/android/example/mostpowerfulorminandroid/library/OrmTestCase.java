package net.pside.android.example.mostpowerfulorminandroid.library;

import android.test.AndroidTestCase;
import android.util.Log;

import java.io.File;
import java.util.Date;

public abstract class OrmTestCase extends AndroidTestCase implements IOrmTestCase {

    public static String MSG_LOGGER_INITIALIZE(String flavor, boolean isBulkMode) {
        return "Insert on " + flavor.toUpperCase() + " (BulkMode:" + (isBulkMode ? "ON" : "OFF") + ")";
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        renameDatabase(getDatabaseName());
    }

    protected void deleteDatabaseIfNeeded(String databaseName) {
        if (databaseName == null) {
            return;
        }

        File databasePath = mContext.getDatabasePath(databaseName);
        if (databasePath != null && databasePath.exists()) {
            mContext.deleteDatabase(databaseName);
        }
    }

    protected void renameDatabase(String databaseName) {
        if (databaseName == null) {
            return;
        }

        File databasePath = mContext.getDatabasePath(databaseName);
        if (databasePath != null && databasePath.exists()) {
            File renameTo = new File(databasePath.getAbsolutePath() + "-" + new Date().getTime());
            boolean result = databasePath.renameTo(renameTo);
            Log.d("OrmTestCase", (result ? "Moved:" : "FAILED:") + databasePath.toString() + " -> " + renameTo.toString());
        }
    }

}
