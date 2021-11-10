package org.meicode.finalprojek1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.meicode.finalprojek1.data.Note;
import org.meicode.finalprojek1.data.DataRepository;

import java.util.List;


public class ViewModel extends AndroidViewModel {

    DataRepository dataRepository;
    LiveData<List<Note>> dataList;


    public ViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        dataList = dataRepository.getAllDataNote();
    }

    public LiveData<List<Note>> getALlDataNote(){
        return dataRepository.getAllDataNote();
    }

    public void insertDataNote(Note note){
        dataRepository.insertDataNote(note);
    }

    public void deleteDataNote(Note note){dataRepository.deleteDataNote(note);}

    public void updateDataNote(Note note){dataRepository.updateDataNote(note);}
}
