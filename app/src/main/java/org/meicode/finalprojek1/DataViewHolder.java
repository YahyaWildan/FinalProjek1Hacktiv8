package org.meicode.finalprojek1;

import androidx.recyclerview.widget.RecyclerView;

import org.meicode.finalprojek1.data.Data;
import org.meicode.finalprojek1.databinding.ItemDataBinding;


public class DataViewHolder extends RecyclerView.ViewHolder {

    ItemDataBinding binding;

    public DataViewHolder(ItemDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setDataToView(Data item) {
        binding.itemData.setText(item.getKegiatan());
    }
}
