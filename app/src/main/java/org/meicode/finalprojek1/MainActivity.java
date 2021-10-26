package org.meicode.finalprojek1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.meicode.finalprojek1.Data.Data;
import org.meicode.finalprojek1.Data.DataBase;
import org.meicode.finalprojek1.Util.AppExecutor;
import org.meicode.finalprojek1.databinding.ActivityMainBinding;
import org.meicode.finalprojek1.databinding.CustomDialogBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    CustomDialogBinding dialogBinding;

    DataBase dataBase;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBase = DataBase.getInstance(this);

        binding.addButton.setOnClickListener(v->{
           shotCustomDialog();

        });
    }

    private void shotCustomDialog() {
    }

    @Override
    protected void onResume(){
        super.onResume();
        AppExecutor.getInstance().DiskIO().execute(()->{
            List<Data> datas = dataBase.dataDao().getAllData();

            runOnUiThread(()->{
                if (datas.size() > 0) {
                    DataAdapter dataAdapter =new DataAdapter(MainActivity.this,datas);

                    binding.tvToDo.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    binding.tvToDo.setAdapter(dataAdapter);
                }
            });
        });
    }
}