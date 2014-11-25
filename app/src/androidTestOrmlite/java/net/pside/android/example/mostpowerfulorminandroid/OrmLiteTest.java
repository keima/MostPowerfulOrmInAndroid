package net.pside.android.example.mostpowerfulorminandroid;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;

import net.pside.android.example.mostpowerfulorminandroid.helper.MySQLiteOpenHelper;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.util.TimingLogger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by keima on 14/11/21.
 */
public class OrmLiteTest extends OrmTestCase {
    public static final String TAG = OrmLiteTest.class.getSimpleName();

    private Dao<Simple, Long> mSimpleDao;

    @Override
    protected String getDatabaseName() {
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
        TimingLogger logger = new TimingLogger(TAG,
                "SingleInsert on ORMLite (BulkMode:" + (isBulkMode ? "ON" : "OFF") + ")"
        );

        if (isBulkMode) {
            try {
                TransactionManager.callInTransaction(mSimpleDao.getConnectionSource(), new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
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
            for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
                try {
                    insertSingle(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                    fail();
                }
            }
        }

        logger.addSplit("Insert " + IOrmTestCase.NUMBER_OF_INSERT_SINGLE + " records.");
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
