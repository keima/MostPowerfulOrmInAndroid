package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;

import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.Simple;
import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.SimpleConst;
import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.SimpleManager;
import net.pside.android.example.mostpowerfulorminandroid.di.MyModule;
import net.pside.android.example.mostpowerfulorminandroid.library.ApplicationOrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;

import org.codejargon.feather.Feather;
import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

public class DbToolsTest extends ApplicationOrmTestCase<Application> {
    public static final String TAG = DbToolsTest.class.getSimpleName();

    @Inject
    private SimpleManager mSimpleManager;

    public DbToolsTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
        Feather.with(new MyModule(getApplication())).injectFields(this);
    }

    @Override
    public String getDatabaseName() {
        return DatabaseManagerConst.DBTOOLS_DATABASE_NAME;
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
                "Insert on " + "DbToolsTest".toUpperCase() + " (BulkMode:" + (isBulkMode ? "ON" : "OFF") + ")");

        if (isBulkMode) {
            mSimpleManager.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = new Simple();
            simple.setStringValue("TestData" + i);
            simple.setDateValue(new DateTime(i * 1000));
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

        List<Simple> simples = mSimpleManager.findAllBySelection(SimpleConst.C_BOOLEAN_VALUE + " = ?", new String[]{"1"});
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }
}
