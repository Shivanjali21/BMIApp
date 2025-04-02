package com.practice.bmi;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import com.practice.bmi.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.Objects;
import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private NoteDataBaseHelper noteDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        noteDataBaseHelper = NoteDataBaseHelper.getDB(this);
        binding.fabAdd.setOnClickListener(this);

        showNotes();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabAdd) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.layout_add_notes);
            AppCompatEditText etTitle, etDesc;
            AppCompatButton btnAdd;
            etTitle = dialog.findViewById(R.id.tvNoteTitle);
            etDesc = dialog.findViewById(R.id.tvNoteDesc);
            btnAdd = dialog.findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(v1 -> {
                String title = Objects.requireNonNull(etTitle.getText()).toString();
                String desc = Objects.requireNonNull(etDesc.getText()).toString();
                if (!title.isEmpty() && !desc.isEmpty()) {
                    noteDataBaseHelper.notesDao().addNotes(new NotesData(title, desc));
                    showNotes();

                    dialog.dismiss();
                } else {
                    MotionToast.Companion.createColorToast(this, "", "Please enter all fields!!", MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, ResourcesCompat.getFont(this, R.font.atkinson_medium));
                }
            });

            dialog.show();
        }
    }

    public void showNotes() {
        ArrayList<NotesData> arryNotes = (ArrayList<NotesData>) noteDataBaseHelper.notesDao().getNotes();
        if (arryNotes.size() > 0) {
            binding.rvNotes.setVisibility(View.VISIBLE);
            binding.tvNoNotes.setVisibility(View.GONE);

            binding.rvNotes.setLayoutManager(new GridLayoutManager(this, 2));
            binding.rvNotes.setAdapter(new NotesAdapter(this, arryNotes, noteDataBaseHelper));
        } else {
            binding.rvNotes.setVisibility(View.GONE);
            binding.tvNoNotes.setVisibility(View.VISIBLE);
        }
    }
}