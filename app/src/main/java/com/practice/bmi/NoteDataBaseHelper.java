package com.practice.bmi;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotesData.class}, version = 1, exportSchema = false)
abstract class NoteDataBaseHelper extends RoomDatabase {

    private static NoteDataBaseHelper instance;
    private static final String DB_NAME = "notes.db";

    //manage access of DB sequence wise
    public static synchronized NoteDataBaseHelper getDB(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDataBaseHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();
}

