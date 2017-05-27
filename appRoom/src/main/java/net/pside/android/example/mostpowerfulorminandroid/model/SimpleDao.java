package net.pside.android.example.mostpowerfulorminandroid.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SimpleDao {

    @Query("SELECT * FROM simple")
    List<Simple> getAll();

    @Query("SELECT * FROM simple WHERE boolean_value == :value")
    List<Simple> getAllByBooleanValue(boolean value);

    @Insert
    void insertSingle(Simple simple);

    @Insert
    void insertAll(Simple... simples);

}
