package net.pside.android.example.mostpowerfulorminandroid.library;

import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.util.Date;

public class OrmBenchmarkRule extends TestWatcher {

    public static final int NUMBER_OF_INSERT_SINGLE = 10000;

    private static final String MSG_LOGGER_SPLIT_INSERT = "Insert " + NUMBER_OF_INSERT_SINGLE + " records.";

    private static final String MSG_LOGGER_SPLIT_SELECT = "Select Records.";

    private Context context;
    private String dbName;
    private boolean skipMoveDbFile;
    private String className;
    private TimingLogger logger;

    public OrmBenchmarkRule(
            @NonNull Context context,
            @NonNull String dbName
    ) {
        this(context, dbName, false);
    }

    public OrmBenchmarkRule(
            @NonNull Context context,
            @NonNull String dbName,
            boolean skipMoveDbFile
    ) {
        this.context = context;
        this.dbName = dbName;
        this.skipMoveDbFile = skipMoveDbFile;
    }

    @Override
    protected void starting(Description description) {
        className = description.getTestClass().getSimpleName();

        OrmBenchmark ormBenchmark = description.getAnnotation(OrmBenchmark.class);
        if (ormBenchmark != null) {
            boolean isBulkMode = ormBenchmark.value();

            String bulkStatus = isBulkMode ? "ON" : "OFF";
            logger = new TimingLogger(
                    className,
                    String.format("Insert on %s (BulkInsert: %s)", className, bulkStatus)
            );
        }
    }

    @Override
    protected void finished(Description description) {
        renameDatabase();
    }

    private void renameDatabase() {
        if (skipMoveDbFile) {
            Log.w(className, "Database rename is skipped. Cleanup all entities manually.");
            return;
        }

        File databasePath = context.getDatabasePath(dbName);
        if (databasePath != null && databasePath.exists()) {
            File renameTo = new File(databasePath.getAbsolutePath() + "-" + new Date().getTime());
            boolean result = databasePath.renameTo(renameTo);
            Log.d(className,
                    (result ? "Moved:" : "FAILED:") + databasePath.toString() + " -> " + renameTo
                            .toString());
        }
    }

    public void beginProfiling() {
        logger.reset();
    }

    public void splitProfiling() {
        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);
    }

    public void endProfiling() {
        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }
}
