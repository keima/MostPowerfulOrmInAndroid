package net.pside.android.example.mostpowerfulorminandroid;


import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.Date;
import java.util.List;

public class SugarOrmTest extends OrmTestCase {
    public static final String TAG = SugarOrmTest.class.getSimpleName();

    public String getDatabaseName() {
        return "sugarorm.db";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Thread.sleep(500); // Application#onCreate()内のSugarORMのinitializeより先にテストケースが走ることがある・・・
    }

    @Override
    protected void tearDown() throws Exception {
        stopDatabaseCleanup = true;
        SugarRecord.deleteAll(Simple.class);
        super.tearDown();
    }

    @Override
    public void testSingleInsert() {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() {
        insert(true);
    }

    private void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG, OrmTestCase.MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        if (isBulkMode) {
            SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
                @Override
                public void manipulateInTransaction() {
                    for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                        insertSingle(i);
                    }
                }
            });
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                insertSingle(i);
            }
        }

        logger.addSplit(OrmTestCase.MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simpleList = SugarRecord.find(Simple.class, "BOOLEAN_VALUE = ?", "1");
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        logger.addSplit(OrmTestCase.MSG_LOGGER_SPLIT_SELECT);
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

        SugarRecord.save(simple);
    }
}
