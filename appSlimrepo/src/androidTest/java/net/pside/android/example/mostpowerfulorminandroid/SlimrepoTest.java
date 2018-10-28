package net.pside.android.example.mostpowerfulorminandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.slimgears.slimrepo.core.interfaces.RepositoryService;
import com.slimgears.slimrepo.core.interfaces.conditions.Conditions;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmark;
import net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule;
import net.pside.android.example.mostpowerfulorminandroid.model.GeneratedSimpleRepositoryService;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleEntity;
import net.pside.android.example.mostpowerfulorminandroid.model.SimpleRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static net.pside.android.example.mostpowerfulorminandroid.library.OrmBenchmarkRule.NUMBER_OF_INSERT_SINGLE;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SlimrepoTest {

    private static final String DATABASE_NAME = SimpleRepository.DB_NAME_WITH_EXT;

    private GeneratedSimpleRepositoryService mRepoService;

    @Rule
    public OrmBenchmarkRule rule = new OrmBenchmarkRule(
            InstrumentationRegistry.getTargetContext(),
            DATABASE_NAME
    );

    @Before
    public void setUp() {
        mRepoService = new GeneratedSimpleRepositoryService(
                InstrumentationRegistry.getTargetContext()
        );
    }

    @Test
    @OrmBenchmark(false)
    public void testSingleInsert() throws IOException {
        insert(false);
    }

    @Test
    @OrmBenchmark(true)
    public void testSingleBulkInsert() throws IOException {
        insert(true);
    }

    private void insert(boolean isBulkMode) throws IOException {
        rule.beginProfiling();

        if (isBulkMode) {
            mRepoService.update(new RepositoryService.UpdateAction<SimpleRepository>() {
                @Override
                public void execute(SimpleRepository repository) throws IOException {
                    for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                        repository.simples().add(createSimple(i));
                    }
                }
            });
        } else {
            for (int i = 1; i <= NUMBER_OF_INSERT_SINGLE; i++) {
                mRepoService.simples().add(createSimple(i));
            }
        }

        rule.splitProfiling();

        List<SimpleEntity> simpleEntities = mRepoService.simples().query()
                .where(Conditions.equals(SimpleEntity.BooleanValue, 1))
                .prepare()
                .toList();

        assertEquals(NUMBER_OF_INSERT_SINGLE / 2, simpleEntities.size());

        rule.endProfiling();
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
