package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;

import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.Simple;
import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.SimpleManager;
import net.pside.android.example.mostpowerfulorminandroid.library.ApplicationOrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;

import org.codejargon.feather.Feather;

import java.util.List;

import javax.inject.Inject;

public class DbToolsTest extends ApplicationOrmTestCase<Application> {
    private static final String TAG = DbToolsTest.class.getSimpleName();

    @Inject
    SimpleManager mSimpleManager;

    public DbToolsTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();

        Feather feather = Feather.with();
        feather.injectFields(this);
        Application application = getApplication();
        mSimpleManager.setApplication(application);
    }

    @Override
    public String getDatabaseName() {
        return DatabaseManager.DBTOOLS_DATABASE_NAME;
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
        TimingLogger logger = new TimingLogger(TAG, isBulkMode ? MSG_LOGGER_INITIALIZE_BULK_ON : MSG_LOGGER_INITIALIZE_BULK_OFF);

        if (isBulkMode) {
            mSimpleManager.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = new Simple();
            simple.setStringValue("TestData" + i);
//            simple.setDateValue(new DateTime(i * 1000));
            simple.setBooleanValue(i % 2 == 0);
            simple.setShortValue(i);
            simple.setIntValue(i);
            simple.setLongValue((long) i);
            simple.setFloatValue((float) i);
            simple.setDoubleValue((double) i);
            mSimpleManager.insert(simple);
        }

        if (isBulkMode) {
            mSimpleManager.endTransaction(true);
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = mSimpleManager.findAllBySelection(Simple.C_BOOLEAN_VALUE + " = ?", new String[]{"1"});
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }
}
