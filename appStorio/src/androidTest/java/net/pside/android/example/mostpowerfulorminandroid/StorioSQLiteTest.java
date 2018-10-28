package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleSQLiteTypeMapping;
import net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable;
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
public class StorioSQLiteTest {
    private static final String DATABASE_NAME = "storio_sqlite.db";

    private StorIOSQLite storIOSQLite;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        storIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new MySQLiteOpenHelper(
                        InstrumentationRegistry.getTargetContext(), DATABASE_NAME, 1
                ))
                .addTypeMapping(Simple.class, new SimpleSQLiteTypeMapping())
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

    private void insert(boolean isBulkMode) {
        rule.beginProfiling();

        if (isBulkMode) {
            ArrayList<Simple> items = new ArrayList<>();
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                items.add(createSimple(i));
            }
            storIOSQLite.put()
                    .objects(items)
                    .prepare()
                    .executeAsBlocking();

        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                storIOSQLite.put()
                        .object(createSimple(i))
                        .prepare()
                        .executeAsBlocking();
            }
        }

        rule.splitProfiling();

        List<Simple> simples = storIOSQLite.get().listOfObjects(Simple.class)
                .withQuery(Query.builder()
                        .table(SimplesTable.TABLE)
                        .where(SimplesTable.COLUMN_BOOLEAN + " = ?")
                        .whereArgs(1)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        rule.endProfiling();
    }

    private Simple createSimple(int i) {
        Simple simple = new Simple(i);
        simple.stringValue = "TestData" + i;
        simple.dateValue(new Date(i * 1000));
        simple.booleanValue = (i % 2 == 0);
        simple.shortValue = (short) i;
        simple.intValue = i;
        simple.longValue = i;
        simple.floatValue = i;
        simple.doubleValue = i;
        return simple;
    }
}
