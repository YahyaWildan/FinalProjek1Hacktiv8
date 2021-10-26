package org.meicode.finalprojek1.Data;

import static androidx.room.OnConflictStrategy.REPLACE;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDao {

    @Query("SELECT * FROM data")
    List<Data>getAllData();

    @Insert(onConflict = REPLACE)
    Long insert(Data data);

    @Update
    int update(Data data);

    @Delete
    int delete(Data data);
}
