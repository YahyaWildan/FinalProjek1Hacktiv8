package org.meicode.finalprojek1;

import androidx.recyclerview.widget.RecyclerView;

import org.meicode.finalprojek1.data.Note;
import org.meicode.finalprojek1.databinding.ItemDataBinding;


public class NoteViewHolder extends RecyclerView.ViewHolder {

    ItemDataBinding binding;

    public NoteViewHolder(ItemDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setDataToView(Note item) {
        binding.tvItemData.setText(item.getDataActivity());
    }
}
