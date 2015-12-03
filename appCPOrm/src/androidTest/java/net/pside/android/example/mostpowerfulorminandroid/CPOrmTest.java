package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.MyCPOrmConfiguration;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import za.co.cporm.model.CPOrm;
import za.co.cporm.model.query.Select;

public class CPOrmTest extends OrmTestCase {
    public static final String TAG = CPOrmTest.class.getSimpleName();
    public static final String DATABASE_NAME = MyCPOrmConfiguration.DB_NAME;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        stopDatabaseCleanup = true;
        CPOrm.deleteAll(Simple.class);
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
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        if (isBulkMode) {
            ArrayList<Simple> list = new ArrayList<>();
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                list.add(createSimple(i));
            }

            for (int i = 0; i < NUMBER_OF_INSERT_SINGLE / 1000; i++) {
                List<Simple> list1 = list.subList(i * 1000, (i + 1) * 1000);
                CPOrm.insertAll(list1);
            }
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                Simple simple = createSimple(i);
                simple.save();
            }
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = Select.from(Simple.class).whereEquals("boolean_value", true).queryAsList();

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
