package net.pside.android.example.mostpowerfulorminandroid;

import android.support.annotation.NonNull;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.Model;
import com.activeandroid.query.Select;

import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.util.TimingLogger;

import java.util.Date;
import java.util.List;

public class ActiveAndroidTest extends OrmTestCase {
    public static final String TAG = ActiveAndroidTest.class.getSimpleName();

    public static final String DATABASE_NAME = "ActiveAndroid.db";
    public static final int DATABASE_VERSION = 1;

    @NonNull
    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Configuration config = new Configuration.Builder(mContext)
                .setDatabaseName(DATABASE_NAME)
                .setDatabaseVersion(DATABASE_VERSION)
                .addModelClass(Simple.class)
                .create();
        ActiveAndroid.initialize(config);
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
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(isBulkMode));

        if (isBulkMode) {
            ActiveAndroid.beginTransaction();
        }

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

            simple.save();
        }

        if (isBulkMode) {
            ActiveAndroid.setTransactionSuccessful();
            ActiveAndroid.endTransaction();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simpleList = new Select().from(Simple.class)
                .where("booleanValue = ?", true)
                .execute();
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    @Override
    public void tearDown() throws Exception {
        ActiveAndroid.dispose();
        super.tearDown();
    }
}
