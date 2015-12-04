package net.pside.android.example.mostpowerfulorminandroid;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SnappyDBTest extends OrmTestCase {
    public static final String TAG = SnappyDBTest.class.getSimpleName();
    public static final String DATABASE_NAME = "torch.db";
    private DB db;


    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        db = DBFactory.open(mContext);
    }

    @Override
    protected void tearDown() throws Exception {
        db.close();
        db.destroy();

        super.tearDown();
    }

    @Override
    public void testSingleInsert() throws Exception {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() throws Exception {
//        insert(true);
    }

    private void insert(boolean isBulkMode) throws SnappydbException {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        if (isBulkMode) {
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = createSimple(i);
            db.put(simple.getSnappyKey(), simple);
        }

        if (isBulkMode) {
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        String[] keys = db.findKeys("Simple:1");
        List<Simple> simples = new ArrayList<>();
        for (String key : keys) {
            Simple simple = db.getObject(key, Simple.class);
            simples.add(simple);
        }

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
