package org.meicode.finalprojek1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.meicode.finalprojek1.Data.Data;
import org.meicode.finalprojek1.databinding.ItemDataBinding;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {
    private List<Data> data;
    private Context context;

    public DataAdapter(Context context, List<Data> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ItemDataBinding binding = ItemDataBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position){
        Data item =data.get(position);
        holder.setDataToView(item);
    }

    @Override
    public int getItemCount(){return data.size();}

}
