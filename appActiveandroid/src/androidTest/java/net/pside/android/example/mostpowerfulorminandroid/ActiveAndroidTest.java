package net.pside.android.example.mostpowerfulorminandroid;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.query.Select;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ActiveAndroidTest {

    private static final String DATABASE_NAME = "ActiveAndroid.db";
    private static final int DATABASE_VERSION = 1;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        Configuration config = new Configuration.Builder(
                InstrumentationRegistry.getTargetContext()
        )
                .setDatabaseName(DATABASE_NAME)
                .setDatabaseVersion(DATABASE_VERSION)
                .addModelClass(Simple.class)
                .create();
        ActiveAndroid.initialize(config);
    }

    @After
    public void tearDown() {
        ActiveAndroid.dispose();
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
            ActiveAndroid.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = new Simple();
            simple.stringValue = "TestData" + i;
            simple.dateValue = new Date(i * 1000);
            simple.booleanValue = (i % 2 == 0);
            simple.shortValue = (short) i;
            simple.intValue = i;
            simple.longValue = i;
            simple.floatValue = i;
            simple.doubleValue = i;

            simple.save();
        }

        if (isBulkMode) {
            ActiveAndroid.setTransactionSuccessful();
            ActiveAndroid.endTransaction();
        }

        rule.splitProfiling();

        List<Simple> simpleList = new Select().from(Simple.class)
                .where("booleanValue = ?", true)
                .execute();
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        rule.endProfiling();
    }
}
