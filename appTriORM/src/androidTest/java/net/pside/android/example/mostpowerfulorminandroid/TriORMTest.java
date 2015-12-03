package net.pside.android.example.mostpowerfulorminandroid;

import com.hendrix.triorm.TriDatabase;
import com.hendrix.triorm.TriOrm;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.ArrayList;
import java.util.Date;

public class TriORMTest extends OrmTestCase {
    public static final String TAG = TriORMTest.class.getSimpleName();
    public static final String DATABASE_NAME = "triorm.db";

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        new TriDatabase.Builder(mContext).addTable(Simple.class).build();
    }

    @Override
    public void testSingleInsert() {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() {
//        insert(true);
    }

    private void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        if (isBulkMode) {
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = createSimple(i);
            simple.setType((i % 2 == 0) ? "true" : "false");
            simple.setId(String.valueOf(i));
            simple.save();
        }

        if (isBulkMode) {
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        ArrayList<Simple> simples = TriOrm.query(Simple.class).type("true").build().query();

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
