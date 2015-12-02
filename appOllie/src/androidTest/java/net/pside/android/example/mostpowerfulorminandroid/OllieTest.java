package net.pside.android.example.mostpowerfulorminandroid;

import android.support.annotation.NonNull;

import net.pside.android.example.mostpowerfulorminandroid.library.IOrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.Date;
import java.util.List;

import ollie.Ollie;
import ollie.query.Delete;
import ollie.query.Select;

public class OllieTest extends OrmTestCase {
    public static final String TAG = OllieTest.class.getSimpleName();

    @NonNull
    @Override
    public String getDatabaseName() {
        return "ollie.db";
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Ollie.with(mContext)
                .setName(getDatabaseName())
                .setVersion(1)
                .init();
    }

    @Override
    protected void tearDown() throws Exception {
//        super.tearDown();
        Delete.from(Simple.class).execute();
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
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        if (isBulkMode) {
            Ollie.getDatabase().beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = createSimple(i);
            simple.save();
        }

        if (isBulkMode) {
            Ollie.getDatabase().setTransactionSuccessful();
            Ollie.getDatabase().endTransaction();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = Select.from(Simple.class).where("booleanValue = ?", 1).fetch();

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
        simple.longValue = (long)i;
        simple.floatValue = (float)i;
        simple.doubleValue = (double)i;
        return simple;
    }
}
