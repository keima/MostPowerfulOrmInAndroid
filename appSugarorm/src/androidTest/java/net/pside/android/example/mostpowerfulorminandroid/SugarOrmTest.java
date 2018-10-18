package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.orm.SugarTransactionHelper;
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
public class SugarOrmTest {

    private static final String TAG = SugarOrmTest.class.getSimpleName();
    private static final String DATABASE_NAME = "sugarorm.db";

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

// Initialize on Application class

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
            SugarTransactionHelper.doInTransaction(new SugarTransactionHelper.Callback() {
                @Override
                public void manipulateInTransaction() {
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

        List<Simple> simpleList = SugarRecord.find(Simple.class, "BOOLEAN_VALUE = ?", "1");
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        rule.endProfiling();
    }

    private void insertSingle(int i) {
        Simple simple = new Simple();
        simple.stringValue = "TestData" + i;
        simple.dateValue = new Date(i * 1000);
        simple.booleanValue = (i % 2 == 0);
        simple.shortValue = (short) i;
        simple.intValue = i;
        simple.longValue = i;
        simple.floatValue = i;
        simple.doubleValue = i;

        SugarRecord.save(simple);
    }
}
