package edu.montgomerycollege.notepad;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NotepadActivity extends Activity {
    
	private EditText noteInput;
	private Button saveButton;
	private ListView notesList;
	private CursorAdapter notesAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        noteInput = (EditText)findViewById(R.id.note_input);
        saveButton = (Button)findViewById(R.id.save_note_button);
        notesList = (ListView)findViewById(R.id.note_list);
        
        saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String input = noteInput.getText().toString();
				if (input.length() > 0) {
					saveNote(input);
				} else {
					Toast.makeText(NotepadActivity.this, getString(R.string.nothing_to_save), Toast.LENGTH_SHORT).show();
				}
			}     	
        });
        
        loadNotes();
        
    }
    
	private void loadNotes() {
		// TODO load the saved notes into notesList
		Cursor notesCursor = null;
		
		//This method take care of deactivating the cursor when the Activity pauses, requerying it when the Activity resumes,
		//and closing the cursor when it is Destroyed
		//startManagingCursor(notesCursor);

        //String[] from = new String[] { "" /* TODO: column name goes here*/ };
        //int[] to = new int[] { android.R.id.text1 };
        

        //Link our Cursor to our ListView
        //notesAdapter =
        //    new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, notesCursor, from, to);
        //notesList.setAdapter(notesAdapter);
	}

	private void saveNote(String input) {
		//TODO: save the user's input into our database
		Toast.makeText(NotepadActivity.this, getString(R.string.saved_note), Toast.LENGTH_SHORT).show();
		noteInput.setText("");
		//Tell our list adapter to refresh its data from the database
		//notesAdapter.getCursor().requery();
	}   
}