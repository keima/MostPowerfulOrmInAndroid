package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;

import com.reactiveandroid.ReActiveAndroid;
import com.reactiveandroid.ReActiveConfig;
import com.reactiveandroid.internal.database.DatabaseConfig;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseConfig appDatabase = new DatabaseConfig.Builder(AppDatabase.class).build();

        ReActiveAndroid.init(
                new ReActiveConfig.Builder(this)
                        .addDatabaseConfigs(appDatabase)
                        .build()
        );
    }
}
