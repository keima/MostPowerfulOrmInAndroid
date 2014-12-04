package net.pside.android.example.mostpowerfulorminandroid.model;

import com.grosner.dbflow.annotation.Database;

/**
 * Created by keima on 14/11/20.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, foreignKeysSupported = true)
public class AppDatabase {
    public static final String NAME = "DBFlow";
    public static final int VERSION = 1;
}
