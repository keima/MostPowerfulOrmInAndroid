/*
 * SimpleBaseManager.java
 *
 * GENERATED FILE - DO NOT EDIT
 * 
 */



package net.pside.android.example.mostpowerfulorminandroid.dbtools.simple;

import android.content.ContentValues;
import android.database.Cursor;

import org.dbtools.android.domain.AndroidBaseManager;
import org.dbtools.android.domain.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
public abstract class SimpleBaseManager {


    public SimpleBaseManager() {
    }

    public static boolean save(DatabaseWrapper db, Simple record) {
        return AndroidBaseManager.save(db, record);
    }

    public static long insert(DatabaseWrapper db, Simple record) {
        return AndroidBaseManager.insert(db, record);
    }

    public static int update(DatabaseWrapper db, Simple record) {
        return AndroidBaseManager.update(db, record);
    }

    public static long delete(DatabaseWrapper db, Simple record) {
        return AndroidBaseManager.delete(db, record);
    }

    public static int update(DatabaseWrapper db, ContentValues values, long rowId) {
        return AndroidBaseManager.update(db, Simple.TABLE, values, Simple.PRIMARY_KEY_COLUMN, rowId);
    }

    public static int update(DatabaseWrapper db, ContentValues values, String where, String[] whereArgs) {
        return AndroidBaseManager.update(db, Simple.TABLE, values, where, whereArgs);
    }

    public static long delete(DatabaseWrapper db, long rowId) {
        return AndroidBaseManager.delete(db, Simple.TABLE, Simple.PRIMARY_KEY_COLUMN, rowId);
    }

    public static long delete(DatabaseWrapper db, String where, String[] whereArgs) {
        return AndroidBaseManager.delete(db, Simple.TABLE, where, whereArgs);
    }

    public static Cursor findCursorByRowId(DatabaseWrapper db, long rowId) {
        return findCursorBySelection(db, Simple.PRIMARY_KEY_COLUMN+ "=" + rowId, null);
    }

    public static Simple findByRowId(DatabaseWrapper db, long rowId) {
        return findBySelection(db, Simple.PRIMARY_KEY_COLUMN+ "=" + rowId, null);
    }

    public static void dropSql(DatabaseWrapper db) {
        AndroidBaseManager.executeSql(db, Simple.DROP_TABLE);
    }

    public static void createSql(DatabaseWrapper db) {
        AndroidBaseManager.executeSql(db, Simple.CREATE_TABLE);
    }

    public static Cursor findCursorBySelection(DatabaseWrapper db, String selection, String orderBy) {
        Cursor cursor = db.query(true, Simple.TABLE, Simple.ALL_KEYS, selection, null, null, null, orderBy, null);
        
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return cursor;
            }
            cursor.close();
        }
        return null;
    }

    public static Simple findBySelection(DatabaseWrapper db, String selection, String orderBy) {
        Cursor cursor = findCursorBySelection(db, selection, null);
        if (cursor != null) {
            Simple record = new Simple();
            record.setContent(cursor);
            cursor.close();
            return record;
        } else {
            return null;
        }
    }

    public static Simple findBySelection(DatabaseWrapper db, String selection) {
        return findBySelection(db, selection, null);
    }

    public static List<Simple> findAllBySelection(DatabaseWrapper db, String selection, String orderBy) {
        Cursor cursor = findCursorBySelection(db, selection, orderBy);
        List<Simple> foundItems = new ArrayList<Simple>();
        if (cursor != null) {
            do {
                Simple record = new Simple();
                record.setContent(cursor);
                foundItems.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return foundItems;
    }

    public static List<Simple> findAllBySelection(DatabaseWrapper db, String selection) {
        return findAllBySelection(db, selection, null);
    }

    public long findCount(DatabaseWrapper db) {
        long count = -1;
        
        Cursor c = db.query(Simple.TABLE, new String[]{"count(1)"}, null, null, null, null, null);
        if (c != null) {
            if (c.getCount() > 0) {
                c.moveToFirst();
                count = c.getLong(0);
                }
            c.close();
        }
        return count;
    }


}