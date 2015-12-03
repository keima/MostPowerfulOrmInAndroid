package net.pside.android.example.mostpowerfulorminandroid;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple;
import net.pside.android.example.mostpowerfulorminandroid.model.Simple$;

import org.brightify.torch.Torch;
import org.brightify.torch.TorchService;
import org.brightify.torch.android.TorchAndroid;
import org.brightify.torch.filter.EqualToFilter;

import java.util.List;

public class TorchTest extends OrmTestCase {
    public static final String TAG = TorchTest.class.getSimpleName();
    public static final String DATABASE_NAME = "torch.db";

    Torch torch;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        TorchAndroid.with(mContext).register(Simple$.create());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        TorchService.forceUnload();
    }

    @Override
    public void testSingleInsert() {
        insert(false);
    }

    @Override
    public void testSingleBulkInsert() {
//        Transaction相当のものが見当たらない？
//        insert(true);
    }

    private void insert(boolean isBulkMode) {
        TimingLogger logger = new TimingLogger(TAG, MSG_LOGGER_INITIALIZE(TAG, isBulkMode));

        if (isBulkMode) {
            // ...?
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            Simple simple = createSimple(i);
            TorchService.torch().save().entity(simple);
        }

        if (isBulkMode) {
            // ...?
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<Simple> simples = TorchService.torch().load().type(Simple.class).filter(
                new EqualToFilter<>(Simple$.booleanValue, true)
        ).list();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simples.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    private Simple createSimple(int i) {
        Simple simple = new Simple();
        simple.stringValue = "TestData" + i;
//        simple.dateValue = new Date(i * 1000);
        simple.booleanValue = (i % 2 == 0);
        simple.shortValue = (short) i;
        simple.intValue = i;
        simple.longValue = i;
        simple.floatValue = i;
        simple.doubleValue = i;
        return simple;
    }
}
