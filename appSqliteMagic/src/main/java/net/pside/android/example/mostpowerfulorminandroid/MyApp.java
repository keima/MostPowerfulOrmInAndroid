package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import com.siimkinks.sqlitemagic.SqliteMagic;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SqliteMagic.builder(this)
                .sqliteFactory(new FrameworkSQLiteOpenHelperFactory())
                .openDefaultConnection();
    }
}
