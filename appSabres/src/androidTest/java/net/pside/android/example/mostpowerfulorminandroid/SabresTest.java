package net.pside.android.example.mostpowerfulorminandroid;

import com.sabres.Sabres;
import com.sabres.SabresException;
import com.sabres.SabresQuery;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SabresTest extends OrmTestCase {
    public static final String TAG = SabresTest.class.getSimpleName();
    public static final String DATABASE_NAME = "torch.db";

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        Simple.deleteAll(Simple.class);
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        stopDatabaseCleanup = true;
        super.tearDown();
    }

    @Override
    public void testSingleInsert() throws Exception {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() throws Exception {
        insert(true);
    }

    private void insert(boolean isBulkMode) throws SabresException {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        if (isBulkMode) {
            ArrayList<Simple> list = new ArrayList<>();
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                Simple simple = createSimple(i);
                list.add(simple);
            }
            Simple.saveAll(list);
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                Simple simple = createSimple(i);
                simple.save();
            }
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        SabresQuery<Simple> query = SabresQuery.getQuery(Simple.class);
        query.whereEqualTo(Simple.KEY_BOOLEAN, true);
        List<Simple> simples = query.find();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    private Simple createSimple(int i) {
        Simple simple = new Simple();
        simple.setStringValue("TestData" + i);
        simple.setDateValue(new Date(i * 1000));
        simple.setBooleanValue(i % 2 == 0);
        simple.setShortValue((short) i);
        simple.setIntValue(i);
        simple.setLongValue((long) i);
        simple.setFloatValue((float) i);
        simple.setDoubleValue((double) i);
        return simple;
    }
}
