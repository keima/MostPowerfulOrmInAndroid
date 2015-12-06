package net.pside.android.example.mostpowerfulorminandroid;

import android.util.Log;

import com.slimgears.slimrepo.core.interfaces.conditions.Conditions;

import net.pside.android.example.mostpowerfulorminandroid.library.OrmTestCase;
import net.pside.android.example.mostpowerfulorminandroid.library.util.TimingLogger;
import net.pside.android.example.mostpowerfulorminandroid.model.GeneratedSimpleRepositoryService;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleEntity;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SlimrepoTest extends OrmTestCase {
    public static final String TAG = SlimrepoTest.class.getSimpleName();
    public static final String DATABASE_NAME = SimpleRepository.DB_NAME_WITH_EXT;

    private GeneratedSimpleRepositoryService mRepoService;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mRepoService = new GeneratedSimpleRepositoryService(mContext);
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

        if (isBulkMode) {
            // ...?
        }

        for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
            try {
                mRepoService.simples().add(createSimple(i));
            } catch (IOException e) {
                Log.e(TAG, "Catch!", e);
            }
        }

        if (isBulkMode) {
            // ...?
        }

        logger.addSplit(MSG_LOGGER_SPLIT_INSERT);

        List<SimpleEntity> simpleEntities = null;
        try {
            simpleEntities = mRepoService.simples().query()
                    .where(Conditions.equals(SimpleEntity.BooleanValue, 1))
                    .prepare()
                    .toList();
        } catch (IOException e) {
            Log.e(TAG, "Catch!", e);
        }

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleEntities.size());

        logger.addSplit(MSG_LOGGER_SPLIT_SELECT);
        logger.dumpToLog();
    }

    private SimpleEntity createSimple(int i) {
        return new SimpleEntity.Builder()
                .stringValue("TestData" + i)
                .dateValue(new Date(i * 1000))
                .booleanValue((i % 2 == 0) ? 1 : 0)
                .shortValue((short) i)
                .intValue(i)
                .longValue(i)
                .floatValue(i)
                .doubleValue(i)
                .build();
    }
}
