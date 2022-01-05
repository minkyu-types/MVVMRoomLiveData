package com.architecture.mvvmroomlivedata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemoDao {

    @Insert
    void insert(MemoDto memoDto);

    @Update
    void update(MemoDto memoDto);

    @Delete
    void delete(MemoDto memoDto);

    @Query("SELECT*FROM memoTable")
    LiveData<List<MemoDto>> getAll(); // LiveData

    @Query("DELETE FROM memoTable")
    void deleteAll();
}
