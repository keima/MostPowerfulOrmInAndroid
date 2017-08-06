package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;

import fr.xebia.android.freezer.Freezer;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Freezer.onCreate(this);
    }

}
