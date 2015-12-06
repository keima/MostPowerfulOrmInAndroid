package net.pside.android.example.mostpowerfulorminandroid;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import net.pside.android.example.mostpowerfulorminandroid.helper.MySQLiteOpenHelper;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

public class OrmLiteTest extends OrmTestCase {
    public static final String TAG = OrmLiteTest.class.getSimpleName();

    private Dao<Simple, Long> mSimpleDao;

    @Override
    public String getDatabaseName() {
        return MySQLiteOpenHelper.DATABASE_NAME;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        MySQLiteOpenHelper sqLiteOpenHelper = new MySQLiteOpenHelper(getContext());
        mSimpleDao = sqLiteOpenHelper.getSimpleDao();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

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
            try {
                TransactionManager.callInTransaction(mSimpleDao.getConnectionSource(), new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                            insertSingle(i);
                        }
                        return null;
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
                fail();
            }
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                try {
                    insertSingle(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                    fail();
                }
            }
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        try {
            QueryBuilder<Simple, Long> builder = mSimpleDao.queryBuilder();
            PreparedQuery<Simple> preparedQuery = builder.where()
                    .eq("booleanValue", true)
                    .prepare();

            List<Simple> simpleList = mSimpleDao.query(preparedQuery);
            assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    private void insertSingle(int i) throws SQLException {
        Simple simple = new Simple();
        simple.stringValue = "TestData" + i;
        simple.dateValue = new Date(i * 1000);
        simple.booleanValue = (i % 2 == 0);
        simple.shortValue = (short) i;
        simple.intValue = i;
        simple.longValue = i;
        simple.floatValue = i;
        simple.doubleValue = i;

        mSimpleDao.create(simple);
    }
}
