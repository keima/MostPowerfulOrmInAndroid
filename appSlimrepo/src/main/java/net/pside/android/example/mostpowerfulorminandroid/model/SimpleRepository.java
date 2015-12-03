package net.pside.android.example.mostpowerfulorminandroid.model;

import com.slimgears.slimrepo.android.core.SqliteOrmServiceProvider;
import com.slimgears.slimrepo.core.annotations.GenerateRepository;
import com.slimgears.slimrepo.core.annotations.OrmProvider;
import com.slimgears.slimrepo.core.interfaces.Repository;
import com.slimgears.slimrepo.core.interfaces.entities.EntitySet;

@GenerateRepository(name = SimpleRepository.DB_NAME, version = SimpleRepository.DB_VERSION)
@OrmProvider(SqliteOrmServiceProvider.class)
public interface SimpleRepository extends Repository {
    String DB_NAME = "slimrepo";
    String DB_NAME_WITH_EXT = "slimrepo.db";
    int DB_VERSION = 1;

    EntitySet<SimpleEntity> simples();
}
