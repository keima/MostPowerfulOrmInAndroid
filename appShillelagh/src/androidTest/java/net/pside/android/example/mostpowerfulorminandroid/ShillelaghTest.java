package net.pside.android.example.mostpowerfulorminandroid;

import android.database.sqlite.SQLiteDatabase;

import net.pside.android.example.mostpowerfulorminandroid.library.IOrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleSQLiteOpenHelper;

import java.util.Date;
import java.util.List;

import shillelagh.Shillelagh;

public class ShillelaghTest extends OrmTestCase {
    private static final String TAG = ShillelaghTest.class.getSimpleName();

    @Override
    public String getDatabaseName() {
        return SimpleSQLiteOpenHelper.DB_NAME;
    }

    @Override
    public void testSingleInsert() {
        insert(true);
    }

    @Override
    public void testSingleBulkInsert() {
        insert(false);
    }

    private void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG, OrmTestCase.MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        SimpleSQLiteOpenHelper helper = new SimpleSQLiteOpenHelper(mContext);
        Shillelagh shillelagh = new Shillelagh(helper);

        SQLiteDatabase db = shillelagh.getWritableDatabase();

        if (isBulkMode) {
            db.beginTransaction();
        }

        for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = new Simple();
            simple.setStringValue("TestData" + i);
            simple.setDateValue(new Date(i * 1000));
            simple.setBooleanValue(i % 2 == 0);
            simple.setShortValue((short) i);
            simple.setIntValue(i);
            simple.setLongValue((long) i);
            simple.setFloatValue((float) i);
            simple.setDoubleValue((double) i);
            shillelagh.insert(simple);
        }

        if (isBulkMode) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = shillelagh.selectFrom(Simple.class).where("booleanValue").isEqualTo(1).toList();
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);
        logger.dumpToLog();

    }
}
