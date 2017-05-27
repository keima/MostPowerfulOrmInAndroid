package net.pside.android.example.mostpowerfulorminandroid;

import android.arch.persistence.room.Room;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.AppDatabase;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.Date;
import java.util.List;

public class RoomTest extends OrmTestCase {
    public static final String TAG = RoomTest.class.getSimpleName();
    public static final String DATABASE_NAME = "room.db";

    private AppDatabase db;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        db = Room.databaseBuilder(getContext(), AppDatabase.class, DATABASE_NAME)
                .build();
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
            db.simpleDao().insertSingle(simple);
        }

        if (isBulkMode) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = db.simpleDao().getAllByBooleanValue(true);

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
