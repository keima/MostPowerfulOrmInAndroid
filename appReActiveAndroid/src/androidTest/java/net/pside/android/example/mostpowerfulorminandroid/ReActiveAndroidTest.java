package net.pside.android.example.mostpowerfulorminandroid;

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

import com.reactiveandroid.ReActiveAndroid;
import com.reactiveandroid.ReActiveConfig;
import com.reactiveandroid.internal.database.DatabaseConfig;
import com.reactiveandroid.internal.database.DatabaseInfo;
import com.reactiveandroid.query.Select;

import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ReActiveAndroidTest {

    private static final String TAG = ReActiveAndroidTest.class.getSimpleName();
    private static final String DATABASE_NAME = "reactiveandroid.db";

    private DatabaseInfo dbInfo;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        DatabaseConfig appDatabase = new DatabaseConfig.Builder(AppDatabase.class).build();
        ReActiveConfig config = new ReActiveConfig.Builder(InstrumentationRegistry.getTargetContext())
                .addDatabaseConfigs(appDatabase)
                .build();

        ReActiveAndroid.init(config);

        dbInfo = ReActiveAndroid.getDatabase(AppDatabase.class);
    }

    @After
    public void tearDown() {
        ReActiveAndroid.destroy();
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
            dbInfo.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple s = createSimple(i);
            s.save();
        }

        if (isBulkMode) {
            dbInfo.getWritableDatabase().setTransactionSuccessful();
            dbInfo.endTransaction();
        }

        rule.splitProfiling();

        List<Simple> simples = Select.from(Simple.class)
                .where("booleanValue = ?", true)
                .fetch();
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
