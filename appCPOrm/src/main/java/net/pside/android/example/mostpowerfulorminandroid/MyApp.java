package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;

import za.co.cporm.model.CPOrm;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CPOrm.initialize(this);
    }
}
