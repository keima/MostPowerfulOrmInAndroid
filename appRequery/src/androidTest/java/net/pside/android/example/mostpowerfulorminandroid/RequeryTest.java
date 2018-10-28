package net.pside.android.example.mostpowerfulorminandroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Models;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class RequeryTest {

    public static final String DATABASE_NAME = "requery.db";

    private EntityDataStore<Persistable> store;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, DATABASE_NAME, 1);
        source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        store = new EntityDataStore<>(source.getConfiguration());
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
            store.runInTransaction(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                        store.insert(createSimple(i));
                    }
                    return null;
                }
            });
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                store.insert(createSimple(i));
            }
        }

        rule.splitProfiling();

        List<Simple> simples = store.select(Simple.class)
                .where(Simple.BOOLEAN_VALUE.equal(true))
                .get().toList();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        rule.endProfiling();
    }

    private Simple createSimple(int i) {
        Simple simple = new Simple();
        simple.setStringValue("TestData" + i);
        simple.setDateValue(new Date(i * 1000));
        simple.setBooleanValue(i % 2 == 0);
        simple.setShortValue((short) i);
        simple.setIntValue(i);
        simple.setLongValue(i);
        simple.setFloatValue(i);
        simple.setDoubleValue(i);
        return simple;
    }
}
