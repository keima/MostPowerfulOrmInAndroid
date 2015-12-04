package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;

import com.sabres.Sabres;
import com.sabres.SabresObject;

import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SabresObject.registerSubclass(Simple.class);
        Sabres.initialize(this);
    }
}
