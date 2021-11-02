package org.meicode.finalprojek1.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {
    private DataDao dataDao;
    private Database database;
    private LiveData<List<Data>> dataList;

    public DataRepository(Application application) {
        database = Database.getDatabase(application);
        dataDao = database.dataDao();
        dataList = dataDao.getAllData();
    }

    public LiveData<List<Data>> getAllData() {
        return dataDao.getAllData();
    }

    public void insertData(final Data data){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.dataDao().insertData(data);
                return null;
            }
        }.execute();
    }

    public void deleteData(final Data data){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.dataDao().deleteData(data);
                return null;
            }
        }.execute();
    }

    public void updateData(final Data data){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.dataDao().updateData(data);
                return null;
            }
        }.execute();
    }
}
