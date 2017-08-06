package net.pside.android.example.mostpowerfulorminandroid;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleSQLiteTypeMapping;
import net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorioSQLiteTest extends OrmTestCase {
    public static final String TAG = StorioSQLiteTest.class.getSimpleName();
    public static final String DATABASE_NAME = "storio_sqlite.db";

    private StorIOSQLite storIOSQLite;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        storIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new MySQLiteOpenHelper(getContext(), DATABASE_NAME, 1))
                .addTypeMapping(Simple.class, new SimpleSQLiteTypeMapping())
                .build();
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

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = storIOSQLite.get().listOfObjects(Simple.class)
                .withQuery(Query.builder()
                        .table(SimplesTable.TABLE)
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
