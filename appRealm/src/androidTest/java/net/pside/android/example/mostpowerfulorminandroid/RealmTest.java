package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class RealmTest {
    private static final String DATABASE_NAME = "Realm.realm";

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    private Realm mRealm;

    private void initRealm() {
        Realm.init(InstrumentationRegistry.getTargetContext());
        RealmConfiguration conf = new RealmConfiguration.Builder()
                .name(DATABASE_NAME)
                .build();
        Realm.setDefaultConfiguration(conf);
    }

    @Before
    public void setUp() {
        initRealm();
        mRealm = Realm.getDefaultInstance();
    }

    @After
    public void tearDown() {
        initRealm();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(Simple.class);
        realm.commitTransaction();
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
            mRealm.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            if (!isBulkMode) {
                mRealm.beginTransaction();
            }

            createSimple(i);

            if (!isBulkMode) {
                mRealm.commitTransaction();
            }
        }

        if (isBulkMode) {
            mRealm.commitTransaction();
        }

        rule.splitProfiling();

        RealmResults<Simple> simples = mRealm.where(Simple.class)
                .equalTo("booleanValue", true)
                .findAll();
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        rule.splitProfiling();

        List<Simple> simpleList = mRealm.copyFromRealm(simples);
        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleList.size());

        rule.endProfiling();
    }

    private void createSimple(int i) {
        Simple simple = mRealm.createObject(Simple.class);
        simple.setStringValue("TestData" + i);
        simple.setDateValue(new Date(i * 1000));
        simple.setBooleanValue(i % 2 == 0);
        simple.setShortValue((short) i);
        simple.setIntValue(i);
        simple.setLongValue((long) i);
        simple.setFloatValue((float) i);
        simple.setDoubleValue((double) i);
    }
}
