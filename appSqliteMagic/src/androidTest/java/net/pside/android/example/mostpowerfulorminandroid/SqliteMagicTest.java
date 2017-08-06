package net.pside.android.example.mostpowerfulorminandroid;

import com.siimkinks.sqlitemagic.Select;
import com.siimkinks.sqlitemagic.SimpleTable;
import com.siimkinks.sqlitemagic.SqliteMagic;
import com.siimkinks.sqlitemagic.Transaction;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.Date;
import java.util.List;

public class SqliteMagicTest extends OrmTestCase {
    public static final String TAG = SqliteMagicTest.class.getSimpleName();
    public static final String DATABASE_NAME = BuildConfig.DB_NAME;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    protected void tearDown() throws Exception {
        stopDatabaseCleanup = true;
        Simple.deleteTable().execute();

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
            Transaction transaction = SqliteMagic.newTransaction();

            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                createSimple(i).insert().execute();
            }

            transaction.markSuccessful();
            transaction.end();
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                createSimple(i).insert().execute();
            }
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = Select.from(SimpleTable.SIMPLE)
                .where(SimpleTable.SIMPLE.BOOLEAN_VALUE.is(true))
                .execute();

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
