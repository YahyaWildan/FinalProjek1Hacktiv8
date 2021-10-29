package org.meicode.finalprojek1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.finalprojek1.data.Data;
import org.meicode.finalprojek1.databinding.ItemDataBinding;

import java.util.List;


public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {

    private List<Data> dataList;
    private Context context;

    public DataAdapter() {
    }

    public void setData(List<Data> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDataBinding binding = ItemDataBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Data item = dataList.get(position);
        holder.setDataToView(item);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
