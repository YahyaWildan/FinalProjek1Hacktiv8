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

import org.meicode.finalprojek1.data.Note;
import org.meicode.finalprojek1.databinding.ActivityMainBinding;
import org.meicode.finalprojek1.viewmodel.ViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.ItemClicked {
    ActivityMainBinding binding;

    ViewModel dataViewModel;

    NoteAdapter noteAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        noteAdapter = new NoteAdapter(MainActivity.this,this);
        binding.rvData.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.rvData.setLayoutManager(new LinearLayoutManager(this));
        dataViewModel.getALlDataNote().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> data) {
                if (data.size() > 0) {
                    noteAdapter.setDataNote(data);
                    binding.rvData.setAdapter(noteAdapter);
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
                Note note = new Note();
                note.setDataActivity(edData.getText().toString());
                dataViewModel.insertDataNote(note);
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

    public void updateData(Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View viewDialog = getLayoutInflater().inflate(R.layout.item_add_data, null);
        EditText edData = viewDialog.findViewById(R.id.etAddData);
        edData.setText(note.getDataActivity());

        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                note.setDataActivity(edData.getText().toString());
                dataViewModel.updateDataNote(note);
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setView(viewDialog);
        builder.setTitle("Update Your Activity");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void updateClicked(Note note) {
        updateData(note);
    }

    @Override
    public void deleteClicked(Note note) {
        dataViewModel.deleteDataNote(note);
    }
}