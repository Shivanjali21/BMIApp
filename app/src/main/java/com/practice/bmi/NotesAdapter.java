package com.practice.bmi;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<NotesData> arrayLNotes;
    NoteDataBaseHelper noteDataBaseHelper;

    public NotesAdapter(Context context, ArrayList<NotesData> arrayNotes, NoteDataBaseHelper dataBaseHelper) {
      this.context = context;
      this.arrayLNotes = arrayNotes;
      this.noteDataBaseHelper = dataBaseHelper;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_layout_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
      holder.tvTitle.setText(arrayLNotes.get(position).getTitle());
      holder.tvDesc.setText(arrayLNotes.get(position).getDescr());
      holder.cvNotes.setOnLongClickListener(v -> {
          AlertDialog alertDialog = new AlertDialog.Builder(context)
                  .setTitle("Delete")
                  .setMessage("Are you sure you want to delete")
                  .setPositiveButton("Yes", (dialog, which) -> {

                     noteDataBaseHelper.notesDao().deleteNotes(new NotesData(arrayLNotes.get(position).getId(),
                             arrayLNotes.get(position).getTitle(), arrayLNotes.get(position).getDescr()));

                      ((MainActivity)context).showNotes();
                  })
                  .setNegativeButton("No", (dialog, which) -> dialog.dismiss()).show();
          alertDialog.show();

          return true;
      });

    }

    @Override
    public int getItemCount() {
      return arrayLNotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       TextView tvTitle, tvDesc;
       CardView cvNotes;
       public ViewHolder(@NonNull View itemView){
         super(itemView);

         tvTitle = itemView.findViewById(R.id.tvNTitle);
         tvDesc = itemView.findViewById(R.id.tvNDesc);
         cvNotes = itemView.findViewById(R.id.cvNotes);
       }
    }
}
