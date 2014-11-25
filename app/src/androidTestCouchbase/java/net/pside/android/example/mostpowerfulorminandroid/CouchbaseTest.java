package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.manager.SimpleModelManager;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.util.TimingLogger;

import java.util.Date;

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
    public void testSingleInsert() {
        TimingLogger logger = new TimingLogger(TAG,
                "SingleInsert on Couchbase lite"
        );

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

        logger.addSplit("Insert " + IOrmTestCase.NUMBER_OF_INSERT_SINGLE + " records.");
        logger.dumpToLog();
    }

    @Override
    public void testSingleBulkInsert() {

    }

}
