package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleDatabase;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple_Table;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DbFlowTest {

    private static final String DATABASE_NAME = SimpleDatabase.NAME + ".db";

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        FlowManager.init(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void tearDown() {
        // DbFlowだけ事情が違うので別途対応する
        SQLite.delete().from(Simple.class).query();
        FlowManager.destroy();
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
            FlowManager.getDatabase(SimpleDatabase.NAME)
                    .executeTransaction(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
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

        List<Simple> simpleList = SQLite.select().from(Simple.class)
                .where(Simple_Table.booleanValue.eq(true))
                .queryList();
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

        simple.insert();
    }

}
