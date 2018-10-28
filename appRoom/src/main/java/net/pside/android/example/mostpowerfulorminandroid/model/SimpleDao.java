package net.pside.android.example.mostpowerfulorminandroid.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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
