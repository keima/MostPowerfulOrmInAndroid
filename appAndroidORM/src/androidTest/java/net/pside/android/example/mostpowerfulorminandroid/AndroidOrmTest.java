package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import cn.ieclipse.aorm.Aorm;
import cn.ieclipse.aorm.Criteria;
import cn.ieclipse.aorm.Restrictions;
import cn.ieclipse.aorm.Session;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AndroidOrmTest {

    private static final String TAG = AndroidOrmTest.class.getSimpleName();
    private static final String DATABASE_NAME = SimpleContentProvider.DB_NAME;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME,
            true
    );

    private Session session;

    @Before
    public void setUp(){
        session = SimpleContentProvider.getSession();
    }

    @After
    public void tearDown() {
        session.deleteAll(Simple.class);
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
            session.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            session.insert(createSimple(i));
        }

        if (isBulkMode) {
            session.setTransactionSuccessful();
            session.endTransaction();
        }

        rule.splitProfiling();

        Criteria criteria = Criteria.create(Simple.class)
                .add(Restrictions.eq("booleanValue", 1));
        List<Simple> simples = session.list(criteria);
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        rule.endProfiling();
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
