/*
 * SimpleManager.java
 *
 * Generated on: 12/06/2015 04:15:26
 *
 */



package net.pside.android.example.mostpowerfulorminandroid.dbtools.simple;


import android.app.Application;

import net.pside.android.example.mostpowerfulorminandroid.DatabaseManager;

@javax.inject.Singleton
public class SimpleManager extends SimpleBaseManager {


    @javax.inject.Inject
    public SimpleManager() {
        databaseManager = new DatabaseManager();
    }

    public void setApplication(Application application) {
        databaseManager.setApplication(application);
    }

}