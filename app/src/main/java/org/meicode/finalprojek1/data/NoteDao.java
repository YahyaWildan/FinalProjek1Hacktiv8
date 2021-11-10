package org.meicode.finalprojek1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insertDataNotes(Note note);

    @Delete
    void deleteDataNotes(Note note);

    @Update
    void updateDataNotes(Note note);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllDataNote();
}
