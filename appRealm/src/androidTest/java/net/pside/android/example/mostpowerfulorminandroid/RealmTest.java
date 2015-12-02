package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.library.IOrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmTest extends OrmTestCase {
    public static final String TAG = RealmTest.class.getSimpleName();
    public static final String DATABASE_NAME = "Realm.realm";

    private Realm mRealm;
    volatile String vStr;

    @Override
    public String getDatabaseName() {
        return null; // Realmにはデータベースのファイル名という概念がない
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        RealmConfiguration conf = new RealmConfiguration.Builder(mContext)
                .name(DATABASE_NAME)
                .build();

        mRealm = Realm.getInstance(conf);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mRealm.beginTransaction();
        mRealm.clear(Simple.class);
        mRealm.commitTransaction();
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
            mRealm.beginTransaction();
        }

        for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
            if (!isBulkMode) {
                mRealm.beginTransaction();
            }

            Simple simple = mRealm.createObject(Simple.class);
            simple.setStringValue("TestData" + i);
            simple.setDateValue(new Date(i * 1000));
            simple.setBooleanValue(i % 2 == 0);
            simple.setShortValue((short) i);
            simple.setIntValue(i);
            simple.setLongValue((long) i);
            simple.setFloatValue((float) i);
            simple.setDoubleValue((double) i);

            if (!isBulkMode) {
                mRealm.commitTransaction();
            }
        }

        if (isBulkMode) {
            mRealm.commitTransaction();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        RealmResults<Simple> simples = mRealm.where(Simple.class)
                .equalTo("booleanValue", true)
                .findAll();
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);

        List<Simple> simpleList = new ArrayList<>();
        for (Simple simple : simples) {
            simpleList.add(simple);
        }
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        logger.addSplit("Manual Import!");

        for (Simple simple : simples) {
            vStr = simple.getStringValue();
        }

        logger.addSplit("Fetching data!");

        logger.dumpToLog();
    }
}
