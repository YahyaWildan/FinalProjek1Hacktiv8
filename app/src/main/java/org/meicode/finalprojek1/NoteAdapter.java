package org.meicode.finalprojek1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.finalprojek1.data.Note;
import org.meicode.finalprojek1.databinding.ItemDataBinding;

import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    public interface ItemClicked {
        void updateClicked(Note note);

        void deleteClicked(Note note);
    }

    private List<Note> noteList;
    private Context context;
    private ItemClicked itemClicked;


    public NoteAdapter(Context context, ItemClicked itemClicked) {
        this.itemClicked = itemClicked;
        this.context = context;
    }

    public void setDataNote(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDataBinding binding = ItemDataBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note item = noteList.get(position);
        String isiKegiatan = item.getDataActivity();

        holder.setDataToView(item);
        holder.binding.tvItemData.setText(isiKegiatan);
        holder.binding.ivMenuOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view, item);
            }
        });
    }

    public void showPopup(View view, final Note item) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.menu_options);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.delete:
                        itemClicked.deleteClicked(item);
                        break;

                    case R.id.update:
                        itemClicked.updateClicked(item);
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
