package net.pside.android.example.mostpowerfulorminandroid;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleSQLiteOpenHelper;

import java.util.Date;
import java.util.List;

import nl.qbusict.cupboard.CupboardFactory;

public class CupboardTest extends OrmTestCase {
    public static final String TAG = CupboardTest.class.getSimpleName();
    public static final String DATABASE_NAME = SimpleSQLiteOpenHelper.DB_NAME;

    SQLiteDatabase db;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        SimpleSQLiteOpenHelper helper = new SimpleSQLiteOpenHelper(mContext);
        db = helper.getWritableDatabase();
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
            db.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = createSimple(i);
            CupboardFactory.cupboard().withDatabase(db).put(simple);
        }

        if (isBulkMode) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = CupboardFactory.cupboard().withDatabase(db)
                .query(Simple.class)
                .withSelection("booleanValue = ?", "1")
                .list();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);
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
