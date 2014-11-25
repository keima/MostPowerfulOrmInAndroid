package net.pside.android.example.mostpowerfulorminandroid;

import android.util.Log;

import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.util.TimingLogger;

import java.io.File;
import java.util.Date;

import io.realm.Realm;

/**
 * Created by keima on 14/11/25.
 */
public class RealmTest extends OrmTestCase {
    public static final String TAG = RealmTest.class.getSimpleName();
    public static final String DATABASE_NAME = "Realm.realm";

    private Realm mRealm;

    @Override
    protected String getDatabaseName() {
        return null; // Realmにはデータベースのファイル名という概念がない
    }

    @Override
    public void setUp() throws Exception {
        Log.d(TAG, "setUp()");
        super.setUp();

        // RealmのデータベースはAndroidのdatabasesに置かれないので自分でなんとかする
        File databasePath = new File(mContext.getFilesDir(), DATABASE_NAME);
        if (databasePath.exists()) {
            boolean result = databasePath.delete();
            Log.d(TAG, result ? "Deleted." : "ERROR!");
        }

        mRealm = Realm.getInstance(mContext, DATABASE_NAME);
    }

    @Override
    protected void tearDown() throws Exception {
        Log.d(TAG, "tearDown()");
        super.tearDown();
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
        TimingLogger logger = new TimingLogger(TAG,
                "SingleInsert on Realm (BulkMode:" + (isBulkMode ? "ON" : "OFF") + ")"
        );

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

        logger.addSplit("Insert " + IOrmTestCase.NUMBER_OF_INSERT_SINGLE + " records.");
        logger.dumpToLog();
    }
}
