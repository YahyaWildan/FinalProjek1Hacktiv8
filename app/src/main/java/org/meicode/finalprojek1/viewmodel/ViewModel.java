package org.meicode.finalprojek1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.meicode.finalprojek1.data.Note;
import org.meicode.finalprojek1.data.NoteRepository;

import java.util.List;


public class ViewModel extends AndroidViewModel {

    NoteRepository noteRepository;
    LiveData<List<Note>> dataList;


    public ViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        dataList = noteRepository.getAllDataNote();
    }

    public LiveData<List<Note>> getALlDataNote(){
        return noteRepository.getAllDataNote();
    }

    public void insertDataNote(Note note){
        noteRepository.insertDataNote(note);
    }

    public void deleteDataNote(Note note){
        noteRepository.deleteDataNote(note);}

    public void updateDataNote(Note note){
        noteRepository.updateDataNote(note);}
}
