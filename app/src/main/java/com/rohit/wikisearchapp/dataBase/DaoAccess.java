package com.rohit.wikisearchapp.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rohit.wikisearchapp.beans.WikiRoomData;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTask(WikiRoomData wikiRoomData);


    @Query("SELECT * FROM WikiRoomData ORDER BY `index` asc")
    List<WikiRoomData> fetchAllTasks();


    @Query("SELECT * FROM WikiRoomData WHERE pageId =:taskId")
    LiveData<WikiRoomData> getTask(int taskId);


    @Update
    void updateTask(WikiRoomData note);


    @Delete
    void deleteTask(WikiRoomData note);
}