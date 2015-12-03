package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.library.IOrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.Date;

public class HogeFugaTest extends OrmTestCase {
    public static final String TAG = HogeFugaTest.class.getSimpleName();
    public static final String DATABASE_NAME = "torch.db";

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
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
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        if (isBulkMode) {
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {

        }

        if (isBulkMode) {
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);


        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    private Simple createSimple(int i) {
        Simple simple = new Simple();
        simple.stringValue = "TestData" + i;
        simple.dateValue = new Date(i * 1000);
        simple.booleanValue = (i % 2 == 0);
        simple.shortValue = (short) i;
        simple.intValue = i;
        simple.longValue = i;
        simple.floatValue = i;
        simple.doubleValue = i;
        return simple;
    }
}
