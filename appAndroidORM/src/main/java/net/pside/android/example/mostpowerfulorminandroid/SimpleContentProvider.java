package net.pside.android.example.mostpowerfulorminandroid;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.pside.android.example.mostpowerfulorminandroid.model.Simple;

import cn.ieclipse.aorm.Aorm;
import cn.ieclipse.aorm.Session;


public class SimpleContentProvider extends ContentProvider {
    public static final String AUTH = BuildConfig.APPLICATION_ID + ".provider";
    public static final Uri URI = Uri.parse("content://" + AUTH);
    public static final String DB_NAME = "androidorm.db";
    private static Session session;
    private SQLiteOpenHelper openHelper;

    public static Session getSession() {
        return session;
    }

    @Override
    public boolean onCreate() {
        openHelper = new SQLiteOpenHelper(getContext(), DB_NAME, null, 1) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                Aorm.createTable(db, Simple.class);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        session = new Session(openHelper, getContext().getContentResolver());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
