package org.meicode.finalprojek1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.finalprojek1.data.Data;
import org.meicode.finalprojek1.databinding.ItemDataBinding;

import java.util.List;


public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {

    private List<Data> dataList;
    private Context context;
    private ItemClicked itemClicked;

    public DataAdapter(ItemClicked itemClicked) {
        this.itemClicked = itemClicked;
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
        String isiKegiatan = item.getKegiatan();

        holder.setDataToView(item);
        holder.binding.viewItemData.setText(isiKegiatan);
        holder.binding.menuOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view, item);
            }
        });
    }

    public void showPopup(View view, final Data item){
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.menu_options);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.delete:
                        itemClicked.deleteClicked(item);
                        Toast.makeText(context, "Delete Clicked", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.update:
                        itemClicked.updateClicked(item);
                        Toast.makeText(context, "Update Clicked", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public interface ItemClicked{
        void updateClicked(Data data);
        void deleteClicked(Data data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
