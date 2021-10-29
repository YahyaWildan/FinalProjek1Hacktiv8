package org.meicode.finalprojek1.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Data.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract DataDao dataDao();

    public static volatile Database INSTANCE;

    static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, Database.class, "datas.db").build();
                }
            }
        }
        return INSTANCE;
    }

}
