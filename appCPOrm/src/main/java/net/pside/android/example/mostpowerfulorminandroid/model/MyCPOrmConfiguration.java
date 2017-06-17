package net.pside.android.example.mostpowerfulorminandroid.model;

import java.util.ArrayList;
import java.util.List;

import za.co.cporm.model.CPOrmConfiguration;

public class MyCPOrmConfiguration implements CPOrmConfiguration {
    public static final String DB_NAME = "cporm.db";

    @Override
    public String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    public int getDatabaseVersion() {
        return 1;
    }

    @Override
    public boolean recreateDatabaseOnFailedUpgrade() {
        return true;
    }

    @Override
    public boolean isQueryLoggingEnabled() {
        return false;
    }

    @Override
    public String upgradeResourceDirectory() {
        return null;
    }

    @Override
    public List<Class<?>> getDataModelObjects() {
        List<Class<?>> domainObjects = new ArrayList<>();
        domainObjects.add(Simple.class);
        return domainObjects;
    }
}
