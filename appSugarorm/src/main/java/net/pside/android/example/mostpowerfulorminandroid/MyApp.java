package net.pside.android.example.mostpowerfulorminandroid;

import android.util.Log;
import com.orm.SugarApp;

public class MyApp extends SugarApp {

    @Override
    public void onCreate() {
        Log.d("SugarOrm", "MyApp#onCreate");
        super.onCreate();
    }
}
