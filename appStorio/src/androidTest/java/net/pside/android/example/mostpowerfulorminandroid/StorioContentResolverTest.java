package net.pside.android.example.mostpowerfulorminandroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.Query;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleContentResolverTypeMapping;
import net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable;
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
public class StorioContentResolverTest {
    private static final String DATABASE_NAME = MyContentProvider.DATABASE_NAME;

    private StorIOContentResolver storIOContentResolver;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        storIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(context.getContentResolver())
                .addTypeMapping(Simple.class, new SimpleContentResolverTypeMapping())
                .build();
    }

    @After
    public void tearDown() {
        // ContentProviderはDB差し替えがしづらいため
        storIOContentResolver.delete()
                .byQuery(DeleteQuery.builder()
                        .uri(SimplesTable.URI)
                        .build())
                .prepare()
                .executeAsBlocking();
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
            storIOContentResolver.put()
                    .objects(items)
                    .prepare()
                    .executeAsBlocking();
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                storIOContentResolver.put()
                        .object(createSimple(i))
                        .prepare()
                        .executeAsBlocking();
            }
        }

        rule.splitProfiling();

        List<Simple> simples = storIOContentResolver.get().listOfObjects(Simple.class)
                .withQuery(Query.builder()
                        .uri(SimplesTable.URI)
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
