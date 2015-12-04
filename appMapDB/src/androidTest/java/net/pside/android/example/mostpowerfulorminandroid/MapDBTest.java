package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MapDBTest extends OrmTestCase {
    public static final String TAG = MapDBTest.class.getSimpleName();
    public static final String DATABASE_NAME = "torch.db";
    private DB db;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    public void testSingleInsert() {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() {
//        insert(true);
    }

    private void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        DBMaker dbMaker = DBMaker.newTempFileDB()
                .closeOnJvmShutdown()
                .deleteFilesAfterClose();

        if (isBulkMode) {
            dbMaker.transactionDisable();
        }

        db = dbMaker.make();


        HTreeMap<Integer, Simple> simplesMap = db.createHashMap("simples").make();

        if (isBulkMode) {
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            simplesMap.put(i, createSimple(i));
        }
        db.commit();

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        HTreeMap<Integer, Simple> simplesMap2 = db.getHashMap("simples");
        ArrayList<Simple> simples = new ArrayList<>();
        for (Map.Entry<Integer, Simple> entry : simplesMap2.entrySet()) {
            if (entry.getKey() % 2 == 0)
                simples.add(entry.getValue());
        }
        db.close();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
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
