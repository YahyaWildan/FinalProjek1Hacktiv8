package org.meicode.finalprojek1.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract NoteDao dataDao();

    public static volatile Database INSTANCE;

    static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, Database.class, "notes.db").build();
                }
            }
        }
        return INSTANCE;
    }

}
