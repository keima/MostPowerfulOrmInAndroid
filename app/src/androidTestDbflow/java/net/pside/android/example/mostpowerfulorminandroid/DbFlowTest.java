package net.pside.android.example.mostpowerfulorminandroid;

import android.support.annotation.NonNull;

import com.grosner.dbflow.config.FlowManager;
import com.grosner.dbflow.runtime.TransactionManager;

import net.pside.android.example.mostpowerfulorminandroid.model.AppDatabase;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.util.TimingLogger;

import java.util.Date;

/**
 * Created by keima on 14/11/25.
 */
public class DbFlowTest extends OrmTestCase {
    public static final String TAG = DbFlowTest.class.getSimpleName();

    @NonNull
    @Override
    protected String getDatabaseName() {
        return AppDatabase.NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        FlowManager.init(mContext);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        FlowManager.destroy();
    }

    @Override
    public void testSingleInsert() {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() {
        insert(true);
    }

    public void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG,
                "SingleInsert on DBFlow (BulkMode:" + (isBulkMode ? "ON" : "OFF") + ")"
        );

        if (isBulkMode) {
            TransactionManager.transact(getDatabaseName(), new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
                        insertSingle(i);
                    }
                }
            });
        } else {
            for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
                insertSingle(i);
            }
        }

        logger.addSplit("Insert " + IOrmTestCase.NUMBER_OF_INSERT_SINGLE + " records.");
        logger.dumpToLog();
    }

    private void insertSingle(int i) {
        Simple simple = new Simple();
        simple.stringValue = "TestData" + i;
        simple.dateValue = new Date(i * 1000);
        simple.booleanValue = (i % 2 == 0);
        simple.shortValue = (short) i;
        simple.intValue = i;
        simple.longValue = i;
        simple.floatValue = i;
        simple.doubleValue = i;

        simple.insert(false);
    }

}
