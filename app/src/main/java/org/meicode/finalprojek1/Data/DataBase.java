package org.meicode.finalprojek1.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Data.class}, version = 1)
public abstract class DataBase extends RoomDatabase{
    public abstract DataDao dataDao();

    public static DataBase instance;

    public static DataBase getInstance(Context context){
        if(instance == null){
            synchronized (DataBase.class){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        DataBase.class,"datadb").build();
            }
        }
    return instance;
    }
}