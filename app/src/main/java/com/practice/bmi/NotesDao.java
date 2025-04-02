package com.practice.bmi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
interface NotesDao {
    @Query("SELECT * FROM note")
    List<NotesData> getNotes();

    @Insert
    void addNotes(NotesData notesData);

    @Delete
    void deleteNotes(NotesData notesData);
}
