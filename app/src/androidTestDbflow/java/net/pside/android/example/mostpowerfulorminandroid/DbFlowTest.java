package net.pside.android.example.mostpowerfulorminandroid;

import android.content.Context;
import android.database.DatabaseUtils;
import android.support.annotation.NonNull;
import android.util.Log;

import com.grosner.dbflow.config.FlowManager;
import com.grosner.dbflow.runtime.TransactionManager;
import com.grosner.dbflow.sql.builder.Condition;
import com.grosner.dbflow.sql.language.Delete;
import com.grosner.dbflow.sql.language.Select;

import net.pside.android.example.mostpowerfulorminandroid.model.AppDatabase;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple$Table;
import net.pside.android.example.mostpowerfulorminandroid.util.TimingLogger;

import java.util.Date;
import java.util.List;

/**
 * Created by keima on 14/11/25.
 */
public class DbFlowTest extends OrmTestCase {
    public static final String TAG = DbFlowTest.class.getSimpleName();

    @NonNull
    @Override
    protected String getDatabaseName() {
        return AppDatabase.NAME + ".db";
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        FlowManager.init(mContext);
    }

    @Override
    protected void tearDown() throws Exception {
        FlowManager.destroy();

        // DbFlowだけ事情が違うので別途対応する
        new Delete().from(Simple.class).query();
    }

    @Override
    public void testSingleInsert() {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() {
        insert(true);
    }

    public void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(isBulkMode));

        if (isBulkMode) {
            TransactionManager.transact(AppDatabase.NAME, new Runnable() {
                @Override
                public void run() {
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

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simpleList = new Select().from(Simple.class)
                .where(Condition.column(Simple$Table.BOOLEANVALUE).is(true))
                .queryList();
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
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

        simple.insert(false);
    }

}
