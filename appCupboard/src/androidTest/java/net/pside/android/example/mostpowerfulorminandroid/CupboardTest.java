package net.pside.android.example.mostpowerfulorminandroid;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleSQLiteOpenHelper;
import nl.qbusict.cupboard.CupboardFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CupboardTest {

    private static final String TAG = CupboardTest.class.getSimpleName();
    private static final String DATABASE_NAME = SimpleSQLiteOpenHelper.DB_NAME;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    SQLiteDatabase db;

    @Before
    public void setUp() {
        SimpleSQLiteOpenHelper helper = new SimpleSQLiteOpenHelper(
                InstrumentationRegistry.getTargetContext()
        );
        db = helper.getWritableDatabase();
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
            CupboardFactory.cupboard().withDatabase(db).put(simple);
        }

        if (isBulkMode) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        rule.splitProfiling();

        List<Simple> simples = CupboardFactory.cupboard().withDatabase(db)
                .query(Simple.class)
                .withSelection("booleanValue = ?", "1")
                .list();

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
