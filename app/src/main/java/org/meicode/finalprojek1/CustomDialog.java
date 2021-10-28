package org.meicode.finalprojek1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import org.meicode.finalprojek1.Data.Data;
import org.meicode.finalprojek1.Data.DataBase;
import org.meicode.finalprojek1.Util.AppExecutor;
import org.meicode.finalprojek1.databinding.CustomDialogBinding;


public class CustomDialog extends AppCompatActivity {
    CustomDialogBinding binding;

    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = CustomDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBase = DataBase.getInstance(this);

//        binding.btnAdd.setOnClickListener(v->{
//            Data newData = new Data();
//
//            newData.setKegiatan(binding.edtKegiatan.getText().toString());
//
//            AppExecutor.getInstance().DiskIO().execute(new Runnable() {
//                @Override
//                public void run() {
//                    Long result = dataBase.dataDao().insert(newData);
//                    runOnUiThread(()->{
//                        if (result!=0){
//                            Toast.makeText(CustomDialogActivity.this,"Sukses menambahkan"+newData.getKegiatan(),Toast.LENGTH_LONG).show();
//                        } else{
//                            Toast.makeText(CustomDialogActivity.this,"Gagal menambahkan"+newData.getKegiatan(),Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//            });
//        });


    }
}
