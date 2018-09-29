package net.pside.android.example.mostpowerfulorminandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class GreenDaoTest {

    private static final String DATABASE_NAME = "GreenDao.db";

    private DaoSession mDaoSession;
    private SimpleDao mSimpleDao;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db = new DaoMaster
                .DevOpenHelper(context, DATABASE_NAME, null)
                .getWritableDatabase();
        mDaoSession = new DaoMaster(db).newSession();
        mSimpleDao = mDaoSession.getSimpleDao();
    }

    @Test
    @OrmBenchmark(false)
    public void testSingleInsert() {
        insert(false);
    }

    @Test
    @OrmBenchmark(true)
    public void testSingleBulkInsert() {
        insert(true);
    }

    private void insert(boolean isBulkMode) {
        rule.beginProfiling();

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

        rule.splitProfiling();

        List<Simple> simpleList = mSimpleDao.queryBuilder()
                .where(SimpleDao.Properties.BooleanValue.eq(true))
                .list();
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        rule.endProfiling();
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
