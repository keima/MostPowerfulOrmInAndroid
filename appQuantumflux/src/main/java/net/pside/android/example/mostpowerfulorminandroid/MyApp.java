package net.pside.android.example.mostpowerfulorminandroid;

import android.app.Application;
import android.content.Context;

import info.quantumflux.QuantumFlux;

public class MyApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        QuantumFlux.initialize(this);
    }
}
