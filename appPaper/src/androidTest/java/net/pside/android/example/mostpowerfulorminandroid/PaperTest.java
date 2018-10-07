package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import io.paperdb.Paper;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PaperTest {

    private static final String TAG = PaperTest.class.getSimpleName();
    private static final String DATABASE_NAME = "paper.db";

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        Paper.init(InstrumentationRegistry.getTargetContext());
    }

//    @Test
//    @OrmBenchmark(false)
//    public void testSingleInsert() {
//        insert(false);
//    }

    @Test
    @OrmBenchmark(true)
    public void testSingleBulkInsert() {
        insert(true);
    }

    private void insert(boolean isBulkMode) {
        rule.beginProfiling();

        if (isBulkMode) {
        }

        List<Simple> list = new LinkedList<>();
        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            list.add(createSimple(i));
        }
        Paper.book().write("Simples", list);

        if (isBulkMode) {
        }

        rule.splitProfiling();

        LinkedList<Simple> l = Paper.book().read("Simples");
        List<Simple> simples = new ArrayList<>();
        for (Simple simple : l) {
            if (simple.booleanValue)
                simples.add(simple);
        }

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
