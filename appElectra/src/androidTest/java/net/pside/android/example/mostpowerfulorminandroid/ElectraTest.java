package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import org.bitbucket.txdrive.electra.Electra;
import org.bitbucket.txdrive.electra.core.manager.EntityManager;
import org.bitbucket.txdrive.electra.core.query.Restrictions;

import java.util.Date;
import java.util.List;

public class ElectraTest extends OrmTestCase {
    public static final String TAG = ElectraTest.class.getSimpleName();
    public static final String DATABASE_NAME = "electra.db";

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void testSingleInsert() {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() {
//        insert(true);
    }

    private void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(getContext(), DATABASE_NAME, 1);
        EntityManager em = Electra.with(helper);

        if (isBulkMode) {
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            em.create(createSimple(i));
        }

        if (isBulkMode) {
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = em.select(Simple.class).where(
                Restrictions.eq("booleanValue", true)
        ).list();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
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
