package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.Date;
import java.util.List;

import cn.ieclipse.aorm.Criteria;
import cn.ieclipse.aorm.Restrictions;
import cn.ieclipse.aorm.Session;

public class AndroidOrmTest extends OrmTestCase {
    public static final String TAG = AndroidOrmTest.class.getSimpleName();
    public static final String DATABASE_NAME = SimpleContentProvider.DB_NAME;

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

        Session session = SimpleContentProvider.getSession();

        if (isBulkMode) {
            session.beginTransaction();
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            session.insert(createSimple(i));
        }

        if (isBulkMode) {
            session.endTransaction();
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        Criteria criteria = Criteria.create(Simple.class);
        criteria.add(Restrictions.eq("booleanValue", true));
        List<Simple> simples = session.list(criteria);

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
