package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleDatabase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SquidbTest {

    private static final String DATABASE_NAME = SimpleDatabase.DB_NAME;

    private SimpleDatabase db;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        db = new SimpleDatabase(
                InstrumentationRegistry.getTargetContext()
        );
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
            db.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = createSimple(i);
            db.persist(simple);
        }

        if (isBulkMode) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        rule.splitProfiling();

        Query q = Query.select().where(Simple.BOOLEAN_VALUE.eq(true));
        SquidCursor<Simple> simpleCursors = db.query(Simple.class, q);


        ArrayList<Simple> simples = new ArrayList<>();

        while (simpleCursors.moveToNext()) {
            Simple simple = new Simple();
            simple.readPropertiesFromCursor(simpleCursors);
            simples.add(simple);
        }

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        rule.endProfiling();
    }

    private Simple createSimple(int i) {
        Simple simple = new Simple();
        simple.setStringValue("TestData" + i);
        simple.setDateValue(new Date(i * 1000));
        simple.setIsBooleanValue(i % 2 == 0);
        simple.setShortValue(i);
        simple.setIntValue(i);
        simple.setLongValue((long) i);
        simple.setFloatValue((double) i);
        simple.setDoubleValue((double) i);
        return simple;
    }
}
