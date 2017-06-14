package net.pside.android.example.mostpowerfulorminandroid.di;

import android.app.Application;

import net.pside.android.example.mostpowerfulorminandroid.AppDatabaseConfig;

import org.codejargon.feather.Provides;
import org.dbtools.android.domain.config.DatabaseConfig;

public class MyModule {

    private final Application app;

    public MyModule(Application application) {
        this.app = application;
    }

    @Provides
    DatabaseConfig provideAppDatabaseConfig() {
        return new AppDatabaseConfig(app);
    }

}
