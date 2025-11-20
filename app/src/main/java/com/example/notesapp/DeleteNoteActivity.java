package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {

    private Spinner spinnerNotes;
    private Button btnDelete;
    private ArrayList<String> noteNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spinnerNotes = findViewById(R.id.spinnerNotes);
        btnDelete = findViewById(R.id.btnDelete);

        loadNoteNames();

        btnDelete.setOnClickListener(v -> deleteSelectedNote());
    }

    private void loadNoteNames() {
        noteNames = NotesStorage.getNoteNames(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, noteNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerNotes.setAdapter(adapter);
    }

    private void deleteSelectedNote() {
        String name = spinnerNotes.getSelectedItem().toString();
        NotesStorage.deleteNote(this, name);
        Toast.makeText(this, "Note deleted!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
