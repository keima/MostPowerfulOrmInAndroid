package net.pside.android.example.mostpowerfulorminandroid.model;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = SimpleDatabase.NAME, version = SimpleDatabase.VERSION, foreignKeysSupported = true)
public class SimpleDatabase {
    public static final String NAME = "DBFlow";
    public static final int VERSION = 1;
}
