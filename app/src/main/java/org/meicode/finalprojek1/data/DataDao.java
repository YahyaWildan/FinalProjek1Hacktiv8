package org.meicode.finalprojek1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insertData(Data data);

    @Delete
    void deleteData(Data data);

    @Update
    void updateData(Data data);

    @Query("SELECT * FROM datas")
    LiveData<List<Data>> getAllData();
}
