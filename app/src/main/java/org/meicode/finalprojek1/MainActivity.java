package org.meicode.finalprojek1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.meicode.finalprojek1.Data.Data;
import org.meicode.finalprojek1.Data.DataBase;
import org.meicode.finalprojek1.Util.AppExecutor;
import org.meicode.finalprojek1.databinding.ActivityMainBinding;
import org.meicode.finalprojek1.databinding.CustomDialogBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    DataBase dataBase;
    String kegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBase = DataBase.getInstance(this);

        binding.addButton.setOnClickListener(v -> {
            shotCustomDialog();

        });
    }

    private void shotCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        CustomDialogBinding customDialog = CustomDialogBinding.inflate(layoutInflater);
        builder.setView(customDialog.getRoot());

                builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    Data newData = new Data();
                    kegiatan = customDialog.edtKegiatan.getText().toString();
                    newData.setKegiatan(kegiatan);

                    AppExecutor.getInstance().DiskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Long result = DataBase.dataDao().insert(newData);
                            runOnUiThread(()->{
                                if (result!=0){
                                    Toast.makeText(MainActivity.this,"Sukses menambahkan"+newData.getKegiatan(),Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this,"Gagal menambahkan"+newData.getKegiatan(),Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setTitle("Add a New Task \nWhat Do You To Do Next?");
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppExecutor.getInstance().DiskIO().execute(() -> {
            List<Data> datas = dataBase.dataDao().getAllData();

            runOnUiThread(() -> {
                if (datas.size() > 0) {
                    DataAdapter dataAdapter = new DataAdapter(MainActivity.this, datas);

                    binding.tvToDo.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    binding.tvToDo.setAdapter(dataAdapter);
                }
            });
        });
    }
}