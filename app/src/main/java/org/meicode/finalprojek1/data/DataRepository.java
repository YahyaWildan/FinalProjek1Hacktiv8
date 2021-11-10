package org.meicode.finalprojek1.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {
    private NoteDao noteDao;
    private Database database;
    private LiveData<List<Note>> dataList;

    public DataRepository(Application application) {
        database = Database.getDatabase(application);
        noteDao = database.dataDao();
        dataList = noteDao.getAllDataNote();
    }

    public LiveData<List<Note>> getAllDataNote() {
        return noteDao.getAllDataNote();
    }

    public void insertDataNote(final Note note){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.dataDao().insertDataNotes(note);
                return null;
            }
        }.execute();
    }

    public void deleteDataNote(final Note note){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.dataDao().deleteDataNotes(note);
                return null;
            }
        }.execute();
    }

    public void updateDataNote(final Note note){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.dataDao().updateDataNotes(note);
                return null;
            }
        }.execute();
    }
}
