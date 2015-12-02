/*
 * SimpleManager.java
 *
 * Generated on: 12/02/2015 12:08:58
 *
 */



package net.pside.android.example.mostpowerfulorminandroid.dbtools.simple;


import android.app.Application;

import net.pside.android.example.mostpowerfulorminandroid.DatabaseManager;

public class SimpleManager extends SimpleBaseManager {

    public SimpleManager() {
        databaseManager = new DatabaseManager();
    }

    public void setApplication(Application application) {
        databaseManager.setApplication(application);
    }


}