package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;

import com.siimkinks.sqlitemagic.SqliteMagic;

import net.pside.android.example.mostpowerfulorminandroid.model.Simple;


public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SqliteMagic.init(this);
    }
}
