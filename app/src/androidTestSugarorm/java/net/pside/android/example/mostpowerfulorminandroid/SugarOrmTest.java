package net.pside.android.example.mostpowerfulorminandroid;


import android.test.ApplicationTestCase;
import android.util.Log;

import com.orm.SugarTransactionHelper;
import com.orm.query.Condition;
import com.orm.query.Select;

import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.util.TimingLogger;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by keima on 14/11/26.
 */
public class SugarOrmTest extends ApplicationTestCase<SugarOrmApplication> implements IOrmTestCase {
    public static final String TAG = SugarOrmTest.class.getSimpleName();

    public SugarOrmTest() {
        super(SugarOrmApplication.class);
    }

    protected String getDatabaseName() {
        return "sugarorm.db";
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        Simple.deleteAll(Simple.class);
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
        TimingLogger logger = new TimingLogger(TAG, OrmTestCase.MSG_LOGGER_INITIALIZE(isBulkMode));

        if (isBulkMode) {
            SugarTransactionHelper.doInTansaction(new SugarTransactionHelper.Callback() {
                @Override
                public void manipulateInTransaction() {
                    for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
                        insertSingle(i);
                    }
                }
            });
        } else {
            for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
                insertSingle(i);
            }
        }

        logger.addSplit(OrmTestCase.MSG_LOGGER_SPLIT_INSERT);


        List<Simple> simpleList = Simple.find(Simple.class, "BOOLEAN_VALUE = ?", "1");

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        logger.addSplit(OrmTestCase.MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    private void insertSingle(int i) {
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
}
