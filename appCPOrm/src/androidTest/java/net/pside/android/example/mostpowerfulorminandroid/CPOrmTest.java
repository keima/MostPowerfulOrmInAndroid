package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.MyCPOrmConfiguration;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import za.co.cporm.model.CPOrm;
import za.co.cporm.model.query.Select;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CPOrmTest {
    private static final String TAG = CPOrmTest.class.getSimpleName();
    private static final String DATABASE_NAME = MyCPOrmConfiguration.DB_NAME;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @After
    public void tearDown() {
        CPOrm.deleteAll(Simple.class);
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
            ArrayList<Simple> list = new ArrayList<>();
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                list.add(createSimple(i));
            }

            for (int i = 0; i < NUMBER_OF_INSERT_SINGLE / 1000; i++) {
                List<Simple> list1 = list.subList(i * 1000, (i + 1) * 1000);
                CPOrm.insertAll(list1);
            }
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                Simple simple = createSimple(i);
                simple.save();
            }
        }

        rule.splitProfiling();

        List<Simple> simples = Select.from(Simple.class).whereEquals("boolean_value", true).queryAsList();

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
