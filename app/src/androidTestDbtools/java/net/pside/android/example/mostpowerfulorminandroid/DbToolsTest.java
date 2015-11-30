package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;

import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.Simple;
import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.SimpleManager;
import net.pside.android.example.mostpowerfulorminandroid.util.TimingLogger;

import org.dbtools.android.domain.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbToolsTest extends ApplicationOrmTestCase {
    private static final String TAG = DbToolsTest.class.getSimpleName();

    public DbToolsTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

    @Override
    public String getDatabaseName() {
        return DatabaseManager.DBTOOLS_DATABASE_NAME + ".db";
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

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.setApplication(getApplication());
        DatabaseWrapper database = databaseManager.getWritableDatabase(DatabaseManager.DBTOOLS_DATABASE_NAME);

        if (isBulkMode) {
            databaseManager.beginTransaction(DatabaseManager.DBTOOLS_DATABASE_NAME);
        }

        for (int i = 1; i <= IOrmTestCase.NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = new Simple();
            simple.setStringValue("TestData" + i);
            simple.setDateValue(new Date(i * 1000));
            simple.setBooleanValue(i % 2 == 0);
            simple.setShortValue((short) i);
            simple.setIntValue(i);
            simple.setLongValue((long) i);
            simple.setFloatValue((float) i);
            simple.setDoubleValue((double) i);
            SimpleManager.insert(database, simple);
        }

        if (isBulkMode){
            databaseManager.endTransaction(DatabaseManager.DBTOOLS_DATABASE_NAME, true);
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = SimpleManager.findAllBySelection(database, Simple.C_BOOLEAN_VALUE + " = true");
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();

    }
}
