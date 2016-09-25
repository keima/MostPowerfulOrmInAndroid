package net.pside.android.example.mostpowerfulorminandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SIMPLE".
*/
public class SimpleDao extends AbstractDao<Simple, Long> {

    public static final String TABLENAME = "SIMPLE";

    /**
     * Properties of entity Simple.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StringValue = new Property(1, String.class, "stringValue", false, "STRING_VALUE");
        public final static Property DateValue = new Property(2, java.util.Date.class, "dateValue", false, "DATE_VALUE");
        public final static Property BooleanValue = new Property(3, Boolean.class, "booleanValue", false, "BOOLEAN_VALUE");
        public final static Property BlobValue = new Property(4, byte[].class, "blobValue", false, "BLOB_VALUE");
        public final static Property ShortValue = new Property(5, Short.class, "shortValue", false, "SHORT_VALUE");
        public final static Property IntValue = new Property(6, Integer.class, "intValue", false, "INT_VALUE");
        public final static Property LongValue = new Property(7, Long.class, "longValue", false, "LONG_VALUE");
        public final static Property FloatValue = new Property(8, Float.class, "floatValue", false, "FLOAT_VALUE");
        public final static Property DoubleValue = new Property(9, Double.class, "doubleValue", false, "DOUBLE_VALUE");
    }


    public SimpleDao(DaoConfig config) {
        super(config);
    }
    
    public SimpleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SIMPLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"STRING_VALUE\" TEXT," + // 1: stringValue
                "\"DATE_VALUE\" INTEGER," + // 2: dateValue
                "\"BOOLEAN_VALUE\" INTEGER," + // 3: booleanValue
                "\"BLOB_VALUE\" BLOB," + // 4: blobValue
                "\"SHORT_VALUE\" INTEGER," + // 5: shortValue
                "\"INT_VALUE\" INTEGER," + // 6: intValue
                "\"LONG_VALUE\" INTEGER," + // 7: longValue
                "\"FLOAT_VALUE\" REAL," + // 8: floatValue
                "\"DOUBLE_VALUE\" REAL);"); // 9: doubleValue
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SIMPLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Simple entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String stringValue = entity.getStringValue();
        if (stringValue != null) {
            stmt.bindString(2, stringValue);
        }
 
        java.util.Date dateValue = entity.getDateValue();
        if (dateValue != null) {
            stmt.bindLong(3, dateValue.getTime());
        }
 
        Boolean booleanValue = entity.getBooleanValue();
        if (booleanValue != null) {
            stmt.bindLong(4, booleanValue ? 1L: 0L);
        }
 
        byte[] blobValue = entity.getBlobValue();
        if (blobValue != null) {
            stmt.bindBlob(5, blobValue);
        }
 
        Short shortValue = entity.getShortValue();
        if (shortValue != null) {
            stmt.bindLong(6, shortValue);
        }
 
        Integer intValue = entity.getIntValue();
        if (intValue != null) {
            stmt.bindLong(7, intValue);
        }
 
        Long longValue = entity.getLongValue();
        if (longValue != null) {
            stmt.bindLong(8, longValue);
        }
 
        Float floatValue = entity.getFloatValue();
        if (floatValue != null) {
            stmt.bindDouble(9, floatValue);
        }
 
        Double doubleValue = entity.getDoubleValue();
        if (doubleValue != null) {
            stmt.bindDouble(10, doubleValue);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Simple entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String stringValue = entity.getStringValue();
        if (stringValue != null) {
            stmt.bindString(2, stringValue);
        }
 
        java.util.Date dateValue = entity.getDateValue();
        if (dateValue != null) {
            stmt.bindLong(3, dateValue.getTime());
        }
 
        Boolean booleanValue = entity.getBooleanValue();
        if (booleanValue != null) {
            stmt.bindLong(4, booleanValue ? 1L: 0L);
        }
 
        byte[] blobValue = entity.getBlobValue();
        if (blobValue != null) {
            stmt.bindBlob(5, blobValue);
        }
 
        Short shortValue = entity.getShortValue();
        if (shortValue != null) {
            stmt.bindLong(6, shortValue);
        }
 
        Integer intValue = entity.getIntValue();
        if (intValue != null) {
            stmt.bindLong(7, intValue);
        }
 
        Long longValue = entity.getLongValue();
        if (longValue != null) {
            stmt.bindLong(8, longValue);
        }
 
        Float floatValue = entity.getFloatValue();
        if (floatValue != null) {
            stmt.bindDouble(9, floatValue);
        }
 
        Double doubleValue = entity.getDoubleValue();
        if (doubleValue != null) {
            stmt.bindDouble(10, doubleValue);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Simple readEntity(Cursor cursor, int offset) {
        Simple entity = new Simple( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // stringValue
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // dateValue
            cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0, // booleanValue
            cursor.isNull(offset + 4) ? null : cursor.getBlob(offset + 4), // blobValue
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5), // shortValue
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // intValue
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7), // longValue
            cursor.isNull(offset + 8) ? null : cursor.getFloat(offset + 8), // floatValue
            cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9) // doubleValue
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Simple entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStringValue(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDateValue(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setBooleanValue(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0);
        entity.setBlobValue(cursor.isNull(offset + 4) ? null : cursor.getBlob(offset + 4));
        entity.setShortValue(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5));
        entity.setIntValue(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setLongValue(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
        entity.setFloatValue(cursor.isNull(offset + 8) ? null : cursor.getFloat(offset + 8));
        entity.setDoubleValue(cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Simple entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Simple entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Simple entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
