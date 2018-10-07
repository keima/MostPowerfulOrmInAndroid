package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import net.pside.android.example.mostpowerfulorminandroid.helper.MySQLiteOpenHelper;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class OrmLiteTest {

    private static final String TAG = OrmLiteTest.class.getSimpleName();
    private static final String DATABASE_NAME = MySQLiteOpenHelper.DATABASE_NAME;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    private Dao<Simple, Long> mSimpleDao;

    @Before
    public void setUp() throws Exception {
        MySQLiteOpenHelper sqLiteOpenHelper = new MySQLiteOpenHelper(
                InstrumentationRegistry.getTargetContext()
        );
        mSimpleDao = sqLiteOpenHelper.getSimpleDao();
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

        rule.splitProfiling();

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

        rule.endProfiling();
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
