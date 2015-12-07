package net.pside.android.example.mostpowerfulorminandroid;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;

import java.util.Date;
import java.util.List;

public class GreenDaoTest extends OrmTestCase {
    public static final String TAG = GreenDaoTest.class.getSimpleName();

    public static final String DATABASE_NAME = "GreenDao.db";

    private DaoSession mDaoSession;
    private SimpleDao mSimpleDao;

    @NonNull
    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        SQLiteDatabase db = new DaoMaster
                .DevOpenHelper(mContext, DATABASE_NAME, null)
                .getWritableDatabase();
        mDaoSession = new DaoMaster(db).newSession();
        mSimpleDao = mDaoSession.getSimpleDao();
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
            mDaoSession.runInTx(new Runnable() {
                @Override
                public void run() {
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

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simpleList = mSimpleDao.queryBuilder()
                .where(SimpleDao.Properties.BooleanValue.eq(true))
                .list();
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    private void insertSingle(int i) {
        Simple simple = new Simple();
        simple.setStringValue("TestData" + i);
        simple.setDateValue(new Date(i * 1000));
        simple.setBooleanValue(i % 2 == 0);
        simple.setShortValue((short) i);
        simple.setIntValue(i);
        simple.setLongValue((long) i);
        simple.setFloatValue((float) i);
        simple.setDoubleValue((double) i);

        mSimpleDao.insert(simple);
    }
}
