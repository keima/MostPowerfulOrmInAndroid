package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;

import net.pside.android.example.mostpowerfulorminandroid.library.ApplicationOrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.SetupObject;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.uk.rushorm.android.RushAndroid;
import co.uk.rushorm.core.Rush;
import co.uk.rushorm.core.RushCore;
import co.uk.rushorm.core.RushSearch;

public class RushormTest extends ApplicationOrmTestCase<Application> {
    public static final String TAG = RushormTest.class.getSimpleName();
    public static final String DATABASE_NAME = "rush.db";

    public RushormTest() {
        super(Application.class);
    }

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        getContext().deleteDatabase(DATABASE_NAME);

        List<Class<? extends Rush>> classes = new ArrayList<>();
        classes.add(SetupObject.class);
        classes.add(Simple.class);
        RushAndroid.initialize(getContext(), classes);

        // Saving this object makes setUp wait until initialize finishes
        // otherwise it seems that the thread initialize is done on gets killed
        new SetupObject().save();
    }

    @Override
    protected void tearDown() throws Exception {
        stopDatabaseCleanup = true;

        RushCore.getInstance().deleteAll(Simple.class);
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
        TimingLogger logger = new TimingLogger(TAG, isBulkMode ? MSG_LOGGER_INITIALIZE_BULK_ON : MSG_LOGGER_INITIALIZE_BULK_OFF);

        if (isBulkMode) {
            ArrayList<Simple> simpleList = new ArrayList<>();
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                simpleList.add(createSimple(i));
            }
            RushCore.getInstance().save(simpleList);
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                createSimple(i).save();
            }
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = new RushSearch().whereEqual("booleanValue", true).find(Simple.class);

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    private Simple createSimple(int i) {
        Simple simple = new Simple();
        simple.stringValue = "TestData" + i;
        simple.dateValue = new Date(i * 1000);
        simple.booleanValue = (i % 2 == 0);
        simple.shortValue = (short) i;
        simple.intValue = i;
        simple.longValue = i;
        simple.floatValue = i;
        simple.doubleValue = i;
        return simple;
    }
}
