package net.pside.android.example.mostpowerfulorminandroid;

import android.test.AndroidTestCase;
import android.util.Log;

import java.io.File;
import java.util.Date;

/**
 * Created by keima on 14/11/25.
 */
public abstract class OrmTestCase extends AndroidTestCase implements IOrmTestCase {

    protected abstract String getDatabaseName();

    public static String MSG_LOGGER_INITIALIZE(boolean isBulkMode) {
        return "Insert on " + BuildConfig.FLAVOR.toUpperCase() +
                " (BulkMode:" + (isBulkMode ? "ON" : "OFF") + ")";
    }

    public static final String MSG_LOGGER_SPLIT_INSERT =
            "Insert " + NUMBER_OF_INSERT_SINGLE + " records.";

    public static final String MSG_LOGGER_SPLIT_SELECT =
            "Select Records.";


    @Override
    protected void setUp() throws Exception {
        super.setUp();
//        deleteDatabaseIfNeeded(getDatabaseName());
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
