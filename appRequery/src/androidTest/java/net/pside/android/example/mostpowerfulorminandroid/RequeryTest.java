package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Models;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

public class RequeryTest extends OrmTestCase {
    public static final String TAG = RequeryTest.class.getSimpleName();
    public static final String DATABASE_NAME = "requery.db";

    private EntityDataStore<Persistable> store;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        DatabaseSource source = new DatabaseSource(getContext(), Models.DEFAULT, DATABASE_NAME, 1);
        source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        store = new EntityDataStore<>(source.getConfiguration());
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

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = store.select(Simple.class)
                .where(Simple.BOOLEAN_VALUE.equal(true))
                .get().toList();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
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
