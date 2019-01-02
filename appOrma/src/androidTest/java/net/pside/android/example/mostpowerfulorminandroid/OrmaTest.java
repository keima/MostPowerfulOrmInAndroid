package net.pside.android.example.mostpowerfulorminandroid;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.github.gfx.android.orma.AccessThreadConstraint;
import com.github.gfx.android.orma.Inserter;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class OrmaTest {
    private static final String DB_NAME = "orma.db";

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DB_NAME
    );

    private OrmaDatabase db;

    @Before
    public void setUp() {
        db = OrmaDatabase.builder(InstrumentationRegistry.getTargetContext())
                .name(DB_NAME)
                .writeAheadLogging(false)
                .readOnMainThread(AccessThreadConstraint.NONE)
                .writeOnMainThread(AccessThreadConstraint.NONE)
                .trace(false)
                .build();
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

    public void insert(boolean isBulkMode) {
        rule.beginProfiling();

        if (isBulkMode) {
            db.transactionSync(new Runnable() {
                @Override
                public void run() {
                    Inserter<Simple> inserter = db.prepareInsertIntoSimple();
                    for (int i = 1; i <= OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE; i++) {
                        inserter.execute(createSimple(i));
                    }
                }
            });
        } else {
            for (int i = 1; i <= OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE; i++) {
                db.insertIntoSimple(createSimple(i));
            }
        }

        rule.splitProfiling();

        List<Simple> simpleList = db.selectFromSimple()
                .where("booleanValue = ?", true)
                .toList();

        assertEquals(OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

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
