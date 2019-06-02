package com.example.android.moviesdb;

import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@android.arch.persistence.room.Database(entities = MovieData.class, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract MoviesDAO moviesDAO();

    private static Database database;
    public static Database getDatabase (Context context) {
        if (database == null){
            database = Room.databaseBuilder(context, Database.class, "Favorite_Movie.db").fallbackToDestructiveMigration().build();
        }
        return database;
    }

}
