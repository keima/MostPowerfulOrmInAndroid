package net.pside.android.example.mostpowerfulorminandroid;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;

import net.pside.android.example.mostpowerfulorminandroid.manager.SimpleModelManager;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.util.TimingLogger;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by keima on 14/11/25.
 */
public class CouchbaseTest extends OrmTestCase {
    public static final String TAG = CouchbaseTest.class.getSimpleName();

    private SimpleModelManager mManager;

    @Override
    protected String getDatabaseName() {
        return null;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mManager = new SimpleModelManager(mContext);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mManager.deleteDatabase();
        mManager.destroy();
    }

    @Override
    public void testSingleInsert() {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(false));

        for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = new Simple();
            simple.stringValue = "TestData" + i;
            simple.dateValue = new Date(i * 1000);
            simple.booleanValue = (i % 2 == 0);
            simple.shortValue = (short) i;
            simple.intValue = i;
            simple.longValue = i;
            simple.floatValue = i;
            simple.doubleValue = i;

            mManager.save(simple);
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);


        try {
            Manager manager = mManager.getManager();
            Database database = manager.getDatabase(Simple.DATABASE_NAME);
            List<Simple> all = mManager.findAll(database);
            assertEquals(NUMBER_OF_INSERT_SINGLE / 2, all.size());
        } catch (IOException | CouchbaseLiteException e) {
            e.printStackTrace();
            fail();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    @Override
    public void testSingleBulkInsert() {

    }

}
