package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.MyObjectBox;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple_;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ObjectBoxTest {

    private static final String TAG = ObjectBoxTest.class.getSimpleName();
    private static final String DATABASE_NAME = "objectbox";

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    private BoxStore boxStore;
    private Box<Simple> simpleBox;

    @Before
    public void setUp() {
        boxStore = MyObjectBox.builder()
                .androidContext(InstrumentationRegistry.getTargetContext())
                .build();
        simpleBox = boxStore.boxFor(Simple.class);
    }

    @After
    public void tearDown() {
        boxStore.close();
        boxStore.deleteAllFiles();
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
            List<Simple> items = new ArrayList<>();
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                items.add(createSimple(i));
            }
            simpleBox.put(items);
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                simpleBox.put(createSimple(i));
            }
        }

        rule.splitProfiling();

        List<Simple> simples = simpleBox.query()
                .equal(Simple_.booleanValue, true)
                .build()
                .find();
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
