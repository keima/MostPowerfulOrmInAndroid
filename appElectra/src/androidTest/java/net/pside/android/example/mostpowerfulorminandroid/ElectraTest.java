package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import electra.ED;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import org.bitbucket.txdrive.electra.Electra;
import org.bitbucket.txdrive.electra.core.manager.EntityManager;
import org.bitbucket.txdrive.electra.core.query.Restrictions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ElectraTest {

    private static final String DATABASE_NAME = "electra.db";

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    private EntityManager em;

    @Before
    public void setUp() {
        Electra.configure(ED.TYPES);
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(
                InstrumentationRegistry.getTargetContext(),
                DATABASE_NAME, 1);
        em = Electra.with(helper);
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
            List<Simple> items = new ArrayList<>();
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                items.add(createSimple(i));
            }
            em.create(items);
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                em.create(createSimple(i));
            }
        }

        rule.splitProfiling();

        List<Simple> simples = em.select(Simple.class).where(
                Restrictions.eq("booleanValue", 1)
        ).list();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        rule.endProfiling();
    }

    private Simple createSimple(int i) {
        Simple simple = new Simple();
        simple.setStringValue("TestData" + i);
        simple.setDateValue(new Date(i * 1000));
        simple.setBooleanValue((i % 2 == 0));
        simple.setShortValue((short) i);
        simple.setIntValue(i);
        simple.setLongValue(i);
        simple.setFloatValue(i);
        simple.setDoubleValue(i);
        return simple;
    }
}
