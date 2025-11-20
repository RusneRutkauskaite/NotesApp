package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNotes = findViewById(R.id.listNotes);
        loadNotes();
    }

    private void loadNotes() {
        ArrayList<String> notes = NotesStorage.getNotes(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, notes);
        listNotes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_add) {
            startActivity(new Intent(this, AddNoteActivity.class));
        }

        if (item.getItemId() == R.id.menu_delete) {
            startActivity(new Intent(this, DeleteNoteActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
