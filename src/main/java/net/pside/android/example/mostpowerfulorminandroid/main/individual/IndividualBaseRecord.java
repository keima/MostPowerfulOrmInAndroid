/*
 * IndividualBaseRecord.java
 *
 * GENERATED FILE - DO NOT EDIT
 * CHECKSTYLE:OFF
 * 
 */



package net.pside.android.example.mostpowerfulorminandroid.main.individual;

import org.dbtools.android.domain.AndroidBaseRecord;
import android.database.Cursor;
import net.pside.android.example.mostpowerfulorminandroid.main.individualtype.IndividualType;
import android.content.ContentValues;


@SuppressWarnings("all")
public abstract class IndividualBaseRecord extends AndroidBaseRecord {

    public static final String DATABASE = "main";
    public static final String TABLE = "INDIVIDUAL";
    public static final String FULL_TABLE = "main.INDIVIDUAL";
    public static final String PRIMARY_KEY_COLUMN = "_id";
    public static final String C_ID = "_id";
    public static final String FULL_C_ID = "INDIVIDUAL._id";
    private long id = 0;
    public static final String C_INDIVIDUAL_TYPE = "INDIVIDUAL_TYPE_ID";
    public static final String FULL_C_INDIVIDUAL_TYPE = "INDIVIDUAL.INDIVIDUAL_TYPE_ID";
    private IndividualType individualType = IndividualType.HEAD;
    public static final String C_NAME = "NAME";
    public static final String FULL_C_NAME = "INDIVIDUAL.NAME";
    private String name = "";
    public static final String C_BIRTH_DATE = "BIRTH_DATE";
    public static final String FULL_C_BIRTH_DATE = "INDIVIDUAL.BIRTH_DATE";
    private org.joda.time.DateTime birthDate = null;
    public static final String C_NUMBER = "NUMBER";
    public static final String FULL_C_NUMBER = "INDIVIDUAL.NUMBER";
    private int number = 0;
    public static final String C_PHONE = "PHONE";
    public static final String FULL_C_PHONE = "INDIVIDUAL.PHONE";
    private String phone = "";
    public static final String C_EMAIL = "EMAIL";
    public static final String FULL_C_EMAIL = "INDIVIDUAL.EMAIL";
    private String email = "";
    public static final String C_DATA = "DATA";
    public static final String FULL_C_DATA = "INDIVIDUAL.DATA";
    private byte[] data = null;
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS INDIVIDUAL (" + 
        "_id INTEGER PRIMARY KEY  AUTOINCREMENT," + 
        "INDIVIDUAL_TYPE_ID INTEGER NOT NULL," + 
        "NAME TEXT NOT NULL," + 
        "BIRTH_DATE TEXT NOT NULL," + 
        "NUMBER INTEGER NOT NULL," + 
        "PHONE TEXT NOT NULL," + 
        "EMAIL TEXT NOT NULL," + 
        "DATA BLOB NOT NULL," + 
        "FOREIGN KEY (INDIVIDUAL_TYPE_ID) REFERENCES INDIVIDUAL_TYPE (_id)" + 
        ");" + 
        "" + 
        "";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS INDIVIDUAL;";
    public static final String[] ALL_KEYS = new String[] {
        C_ID,
        C_INDIVIDUAL_TYPE,
        C_NAME,
        C_BIRTH_DATE,
        C_NUMBER,
        C_PHONE,
        C_EMAIL,
        C_DATA};

    public IndividualBaseRecord() {
    }

    @Override
    public String getIdColumnName() {
        return C_ID;
    }

    @Override
    public long getPrimaryKeyId() {
        return id;
    }

    @Override
    public void setPrimaryKeyId(long id) {
        this.id = id;
    }

    public static long getId(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(C_ID));
    }

    public static IndividualType getIndividualType(Cursor cursor) {
        return IndividualType.values()[cursor.getInt(cursor.getColumnIndexOrThrow(C_INDIVIDUAL_TYPE))];
    }

    public static String getName(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow(C_NAME));
    }

    public static org.joda.time.DateTime getBirthDate(Cursor cursor) {
        return dbStringToDateTime(cursor.getString(cursor.getColumnIndexOrThrow(C_BIRTH_DATE)));
    }

    public static int getNumber(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(C_NUMBER));
    }

    public static String getPhone(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow(C_PHONE));
    }

    public static String getEmail(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow(C_EMAIL));
    }

    public static byte[] getData(Cursor cursor) {
        return cursor.getBlob(cursor.getColumnIndexOrThrow(C_DATA));
    }

    @Override
    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(C_INDIVIDUAL_TYPE, individualType.ordinal());
        values.put(C_NAME, name);
        values.put(C_BIRTH_DATE, birthDate != null ? birthDate.getMillis() : null);
        values.put(C_NUMBER, number);
        values.put(C_PHONE, phone);
        values.put(C_EMAIL, email);
        values.put(C_DATA, data);
        return values;
    }

    @Override
    public Object[] getValues() {
        Object[] values = new Object[]{
            id,
            individualType.ordinal(),
            name,
            birthDate != null ? birthDate.getMillis() : null,
            number,
            phone,
            email,
            data,
        };
        return values;
    }

    public void setContent(ContentValues values) {
        individualType = IndividualType.values()[values.getAsInteger(C_INDIVIDUAL_TYPE)];
        name = values.getAsString(C_NAME);
        birthDate = dbStringToDateTime(values.getAsString(C_BIRTH_DATE));
        number = values.getAsInteger(C_NUMBER);
        phone = values.getAsString(C_PHONE);
        email = values.getAsString(C_EMAIL);
        data = values.getAsByteArray(C_DATA);
    }

    @Override
    public void setContent(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndexOrThrow(C_ID));
        individualType = IndividualType.values()[cursor.getInt(cursor.getColumnIndexOrThrow(C_INDIVIDUAL_TYPE))];
        name = cursor.getString(cursor.getColumnIndexOrThrow(C_NAME));
        birthDate = dbStringToDateTime(cursor.getString(cursor.getColumnIndexOrThrow(C_BIRTH_DATE)));
        number = cursor.getInt(cursor.getColumnIndexOrThrow(C_NUMBER));
        phone = cursor.getString(cursor.getColumnIndexOrThrow(C_PHONE));
        email = cursor.getString(cursor.getColumnIndexOrThrow(C_EMAIL));
        data = cursor.getBlob(cursor.getColumnIndexOrThrow(C_DATA));
    }

    public boolean isNewRecord() {
        return getPrimaryKeyId() <= 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @javax.annotation.Nonnull
    public IndividualType getIndividualType() {
        return individualType;
    }

    public void setIndividualType(@javax.annotation.Nonnull IndividualType individualType) {
        this.individualType = individualType;
    }

    @javax.annotation.Nonnull
    public String getName() {
        return name;
    }

    public void setName(@javax.annotation.Nonnull String name) {
        this.name = name;
    }

    @javax.annotation.Nonnull
    public org.joda.time.DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@javax.annotation.Nonnull org.joda.time.DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @javax.annotation.Nonnull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@javax.annotation.Nonnull String phone) {
        this.phone = phone;
    }

    @javax.annotation.Nonnull
    public String getEmail() {
        return email;
    }

    public void setEmail(@javax.annotation.Nonnull String email) {
        this.email = email;
    }

    @javax.annotation.Nonnull
    public byte[] getData() {
        return data;
    }

    public void setData(@javax.annotation.Nonnull byte[] data) {
        this.data = data;
    }


}