package org.meicode.finalprojek1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.meicode.finalprojek1.data.Data;
import org.meicode.finalprojek1.databinding.ActivityMainBinding;
import org.meicode.finalprojek1.databinding.ItemDataBinding;
import org.meicode.finalprojek1.viewmodel.ViewModel;

import java.text.BreakIterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataAdapter.ItemClicked {
    ActivityMainBinding binding;

    ViewModel dataViewModel;

    DataAdapter dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        dataAdapter = new DataAdapter(MainActivity.this,this);
        binding.rvData.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.rvData.setLayoutManager(new LinearLayoutManager(this));
        dataViewModel.getALlData().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                if (data.size() > 0) {
                    dataAdapter.setData(data);
                    binding.rvData.setAdapter(dataAdapter);
                }
            }
        });


        binding.fabAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(MainActivity.this);
            }
        });
    }

    public void addData(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View viewDialog = getLayoutInflater().inflate(R.layout.item_add_data, null);

        builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText edData = viewDialog.findViewById(R.id.etAddData);
                Data data = new Data();
                data.setKegiatan(edData.getText().toString());
                dataViewModel.insertData(data);
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setView(viewDialog);
        builder.setTitle("Add a New Task \nWhat Do You To Do Next?");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void updateData(Data data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View viewDialog = getLayoutInflater().inflate(R.layout.item_add_data, null);
        EditText edData = viewDialog.findViewById(R.id.etAddData);
        edData.setText(data.getKegiatan());

        builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.setKegiatan(edData.getText().toString());
                dataViewModel.insertData(data);
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setView(viewDialog);
        builder.setTitle("Add a New Task \nWhat Do You To Do Next?");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void updateClicked(Data data) {
        updateData(data);
    }

    @Override
    public void deleteClicked(Data data) {
        dataViewModel.deleteData(data);

    }
}