package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private EditText txtName, txtContent;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        txtName = findViewById(R.id.txtName);
        txtContent = findViewById(R.id.txtContent);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveNote());
    }

    private void saveNote() {
        String name = txtName.getText().toString().trim();
        String content = txtContent.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (content.isEmpty()) {
            Toast.makeText(this, "Content is required", Toast.LENGTH_SHORT).show();
            return;
        }

        NotesStorage.saveNote(this, name, content);
        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
