package com.example.notesapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NotesStorage {

    private static final String PREF_NAME = "notesPref";
    private static final String KEY_NOTES = "notes";

    public static void saveNote(Context context, String noteName, String noteContent) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> notes = prefs.getStringSet(KEY_NOTES, new HashSet<>());
        notes.add(noteName + "||" + noteContent);
        prefs.edit().putStringSet(KEY_NOTES, notes).apply();
    }

    public static ArrayList<String> getNoteNames(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> notes = prefs.getStringSet(KEY_NOTES, new HashSet<>());
        ArrayList<String> names = new ArrayList<>();

        for (String item : notes) {
            names.add(item.split("\\|\\|")[0]);
        }
        return names;
    }

    public static ArrayList<String> getNotes(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> notes = prefs.getStringSet(KEY_NOTES, new HashSet<>());
        return new ArrayList<>(notes);
    }

    public static void deleteNote(Context context, String noteName) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> notes = prefs.getStringSet(KEY_NOTES, new HashSet<>());

        Set<String> updated = new HashSet<>();
        for (String entry : notes) {
            if (!entry.startsWith(noteName + "||")) {
                updated.add(entry);
            }
        }

        prefs.edit().putStringSet(KEY_NOTES, updated).apply();
    }
}
