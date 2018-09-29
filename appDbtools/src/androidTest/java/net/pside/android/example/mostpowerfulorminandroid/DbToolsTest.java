package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.Simple;
import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.SimpleConst;
import net.pside.android.example.mostpowerfulorminandroid.dbtools.simple.SimpleManager;
import net.pside.android.example.mostpowerfulorminandroid.di.MyModule;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import org.codejargon.feather.Feather;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DbToolsTest {

    private static final String DATABASE_NAME =
            DatabaseManagerConst.DBTOOLS_DATABASE_NAME;

    @Inject
    private SimpleManager mSimpleManager;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Rule
    public ActivityTestRule<MockActivity> activityRule =
            new ActivityTestRule<>(MockActivity.class);

    @Before
    public void setUp() {
        Feather.with(new MyModule(
                activityRule.getActivity().getApplication()
        )).injectFields(this);
    }

    @Test
    @OrmBenchmark(false)
    public void testSingleInsert() {
        insert(false);
    }

    @Test
    @OrmBenchmark(true)
    public void testSingleBulkInsert() {
        insert(true);
    }

    private void insert(boolean isBulkMode) {
        rule.beginProfiling();

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

        rule.splitProfiling();

        List<Simple> simples = mSimpleManager.findAllBySelection(SimpleConst.C_BOOLEAN_VALUE + " = ?", new String[]{"1"});
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        rule.endProfiling();
    }
}
