package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.siimkinks.sqlitemagic.Select;
import com.siimkinks.sqlitemagic.SimpleTable;
import com.siimkinks.sqlitemagic.SqliteMagic;
import com.siimkinks.sqlitemagic.Transaction;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SqliteMagicTest {

    private static final String DATABASE_NAME = BuildConfig.DB_NAME;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME,
            true
    );

    @After
    public void tearDown() throws Exception {
        Simple.deleteTable().execute();
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
            Transaction transaction = SqliteMagic.newTransaction();

            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                createSimple(i).insert().execute();
            }

            transaction.markSuccessful();
            transaction.end();
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                createSimple(i).insert().execute();
            }
        }

        rule.splitProfiling();

        List<Simple> simples = Select.from(SimpleTable.SIMPLE)
                .where(SimpleTable.SIMPLE.BOOLEAN_VALUE.is(true))
                .execute();

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
