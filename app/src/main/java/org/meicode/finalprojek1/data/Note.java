package org.meicode.finalprojek1.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "data_activty")
    private String dataActivity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDataActivity() {
        return dataActivity;
    }

    public void setDataActivity(String dataActivity) {
        this.dataActivity = dataActivity;
    }
}
