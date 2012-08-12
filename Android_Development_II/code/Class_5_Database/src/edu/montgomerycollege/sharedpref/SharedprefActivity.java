package edu.montgomerycollege.sharedpref;

import android.app.Activity;
import android.content.SharedPreferences;
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

public class SharedprefActivity extends Activity {
    
	private EditText input;
	private Button saveButton;
	private ListView notesList;
	
	private NotesDBAdapter dbAdapter;
	private CursorAdapter notesAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dbAdapter = new NotesDBAdapter().open(this);
        
        input = (EditText)findViewById(R.id.input);
        saveButton = (Button)findViewById(R.id.save_button);
        notesList = (ListView)findViewById(R.id.notes_list);
        
        //final SharedPreferences prefs = 
		//		SharedprefActivity.this.getSharedPreferences("sharedprefs", MODE_PRIVATE);
        
        OnClickListener ocl = new OnClickListener() {
			public void onClick(View v) {
				String sInput = input.getText().toString();
				saveNote(sInput);
				//SharedPreferences.Editor prefEditor = prefs.edit();
				//prefEditor.putString("saved_value", sInput);
				//prefEditor.commit();				
			}
        };        
        saveButton.setOnClickListener(ocl);
        
        loadNotes();
        //String savedValue = prefs.getString("saved_value", "");
        //Toast.makeText(this, savedValue, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	dbAdapter.close();
    }
    
    private void loadNotes() {
		// TODO load the saved notes into notesList
		Cursor notesCursor = dbAdapter.getAllNotes();
				
		
		//This method take care of deactivating the cursor when the Activity pauses, requerying it when the Activity resumes,
		//and closing the cursor when it is Destroyed
		startManagingCursor(notesCursor);

        String[] from = new String[] { NotesDBAdapter.KEY_NOTE_TEXT };
        int[] to = new int[] { android.R.id.text1 };
        

        //Link our Cursor to our ListView
        notesAdapter =
            new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, notesCursor, from, to);
        notesList.setAdapter(notesAdapter);
	}

	private void saveNote(String input) {
		Toast.makeText(SharedprefActivity.this, "Saved note", Toast.LENGTH_SHORT).show();
		this.input.setText("");
		dbAdapter.insertNote(input);
		//Tell our list adapter to refresh its data from the database
		notesAdapter.getCursor().requery();
	}
}