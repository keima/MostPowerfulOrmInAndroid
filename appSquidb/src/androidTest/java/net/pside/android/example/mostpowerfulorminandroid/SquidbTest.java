package net.pside.android.example.mostpowerfulorminandroid;

import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleDatabase;

import java.util.ArrayList;

public class SquidbTest extends OrmTestCase {
    public static final String TAG = SquidbTest.class.getSimpleName();

    private SimpleDatabase db;

    @Override
    public String getDatabaseName() {
        return SimpleDatabase.DB_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        db = new SimpleDatabase(mContext);
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
            db.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = createSimple(i);
            db.persist(simple);
        }

        if (isBulkMode) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        Query q = Query.select().where(Simple.BOOLEAN_VALUE.eq(true));
        SquidCursor<Simple> simpleCursors = db.query(Simple.class, q);


        ArrayList<Simple> simples = new ArrayList<>();

        while(simpleCursors.moveToNext()){
            Simple simple = new Simple();
            simple.readPropertiesFromCursor(simpleCursors);
            simples.add(simple);
        }

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    private Simple createSimple(int i) {
        Simple simple = new Simple();
        simple.setStringValue("TestData" + i);
//        simple.setDateValue(new Date(i * 1000));
        simple.setIsBooleanValue(i % 2 == 0);
        simple.setShortValue(i);
        simple.setIntValue(i);
        simple.setLongValue((long) i);
        simple.setFloatValue((double) i);
        simple.setDoubleValue((double) i);
        return simple;
    }
}
