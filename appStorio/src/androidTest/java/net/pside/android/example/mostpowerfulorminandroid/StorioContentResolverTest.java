package net.pside.android.example.mostpowerfulorminandroid;

import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.Query;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleContentResolverTypeMapping;
import net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorioContentResolverTest extends OrmTestCase {
    public static final String TAG = StorioContentResolverTest.class.getSimpleName();
    public static final String DATABASE_NAME = MyContentProvider.DATABASE_NAME;

    private StorIOContentResolver storIOContentResolver;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        storIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(getContext().getContentResolver())
                .addTypeMapping(Simple.class, new SimpleContentResolverTypeMapping())
                .build();
    }

    @Override
    protected void tearDown() throws Exception {
        // ContentProviderはDB差し替えがしづらいため
        stopDatabaseCleanup = true;
        storIOContentResolver.delete()
                .byQuery(DeleteQuery.builder()
                        .uri(SimplesTable.URI)
                        .build())
                .prepare()
                .executeAsBlocking();
        super.tearDown();
    }

    @Override
    public void testSingleInsert() {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() {
        insert(true);
    }

    private void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

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

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = storIOContentResolver.get().listOfObjects(Simple.class)
                .withQuery(Query.builder()
                        .uri(SimplesTable.URI)
                        .where(SimplesTable.COLUMN_BOOLEAN + " = ?")
                        .whereArgs(1)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
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
