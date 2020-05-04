package com.rohit.wikisearchapp.dataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rohit.wikisearchapp.beans.WikiRoomData;

@Database(entities = {WikiRoomData.class}, version = 1, exportSchema = false)
public abstract class WikiSearchDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();

}