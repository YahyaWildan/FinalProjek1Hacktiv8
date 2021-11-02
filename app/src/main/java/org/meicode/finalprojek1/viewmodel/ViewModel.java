package org.meicode.finalprojek1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.meicode.finalprojek1.data.Data;
import org.meicode.finalprojek1.data.DataRepository;

import java.util.List;


public class ViewModel extends AndroidViewModel {

    DataRepository dataRepository;
    LiveData<List<Data>> dataList;


    public ViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        dataList = dataRepository.getAllData();
    }

    public LiveData<List<Data>> getALlData(){
        return dataRepository.getAllData();
    }

    public void insertData(Data data){
        dataRepository.insertData(data);
    }

    public void deleteData(Data data){dataRepository.deleteData(data);}

    public void updateData(Data data){dataRepository.updateData(data);}
}
