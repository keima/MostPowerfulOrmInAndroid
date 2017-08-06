package net.pside.android.example.mostpowerfulorminandroid;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.pside.android.example.mostpowerfulorminandroid.model.SimplesTable;


public class MyContentProvider extends ContentProvider {

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID;

    public static final String DATABASE_NAME = "storio_resolver.db";

    private static final UriMatcher URI_MATCHER = new UriMatcher(1);

    private SQLiteOpenHelper sqLiteOpenHelper;

    static {
        URI_MATCHER.addURI(AUTHORITY, SimplesTable.TABLE, SimplesTable.URI_MATCHER_CODE);
    }

    @Override
    public boolean onCreate() {
        sqLiteOpenHelper = new MySQLiteOpenHelper(getContext(), DATABASE_NAME, 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (URI_MATCHER.match(uri)) {
            case SimplesTable.URI_MATCHER_CODE:
                return sqLiteOpenHelper.getReadableDatabase()
                        .query(
                                SimplesTable.TABLE,
                                projection, selection, selectionArgs,
                                null, null, sortOrder
                        );
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final long insertedId;

        switch (URI_MATCHER.match(uri)) {
            case SimplesTable.URI_MATCHER_CODE:
                insertedId = sqLiteOpenHelper.getWritableDatabase()
                        .insert(SimplesTable.TABLE, null, values);
                break;
            default:
                return null;

        }

        if (insertedId != -1) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return ContentUris.withAppendedId(uri, insertedId);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int numberOfRowsAffected;

        switch (URI_MATCHER.match(uri)) {
            case SimplesTable.URI_MATCHER_CODE:
                numberOfRowsAffected = sqLiteOpenHelper
                        .getWritableDatabase()
                        .update(
                                SimplesTable.TABLE,
                                values,
                                selection,
                                selectionArgs
                        );
                break;
            default:
                return 0;
        }

        if (numberOfRowsAffected > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsAffected;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int numberOfRowsDeleted;

        switch (URI_MATCHER.match(uri)) {
            case SimplesTable.URI_MATCHER_CODE:
                numberOfRowsDeleted = sqLiteOpenHelper
                        .getWritableDatabase()
                        .delete(
                                SimplesTable.TABLE,
                                selection,
                                selectionArgs
                        );
                break;

            default:
                return 0;
        }

        if (numberOfRowsDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsDeleted;
    }
}
