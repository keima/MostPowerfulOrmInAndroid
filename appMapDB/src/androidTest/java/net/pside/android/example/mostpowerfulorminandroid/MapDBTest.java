package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

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
    public void testSingleInsert() {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() {
        insert(true);
    }

    private void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        DBMaker.Maker dbMaker = DBMaker.tempFileDB()
                .closeOnJvmShutdown()
                .fileDeleteAfterClose();

        if (isBulkMode) {
            dbMaker.transactionEnable();
        }

        db = dbMaker.make();

        HTreeMap<Integer, Simple> simplesMap = db.hashMap("simples")
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.JAVA)
                .createOrOpen();

        if (isBulkMode) {
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            simplesMap.put(i, createSimple(i));
        }
        db.commit();

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        HTreeMap<Integer, Simple> simplesMap2 = db.hashMap("simples")
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.JAVA)
                .open();
        ArrayList<Simple> simples = new ArrayList<>();
        for (Object object : simplesMap2.entrySet()) {
            if (object instanceof Map.Entry) {
                Map.Entry<Integer, Simple> entry = (Map.Entry<Integer, Simple>) object;
                if (entry.getValue().booleanValue) {
                    simples.add(entry.getValue());
                }
            }
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
