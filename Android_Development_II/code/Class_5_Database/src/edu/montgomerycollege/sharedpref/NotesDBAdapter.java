package edu.montgomerycollege.sharedpref;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDBAdapter {
	private static final String DATABASE_NAME = "db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NOTES = "notes";
	
	public static final String KEY_ID = "_id";
	public static final String KEY_NOTE_TEXT = "note_text";
	
	private static final String DB_CREATE = "CREATE TABLE notes " +
			"(_id INTEGER PRIMARY KEY AUTOINCREMENT, note_text text NOT NULL)";
	
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	
	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
		
	}
	
	public NotesDBAdapter open(Context context) throws SQLException {
		dbHelper = new DatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
		return this;
		
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public Cursor getAllNotes() {
		String[] columnsWeWant = new String[] {KEY_ID, KEY_NOTE_TEXT};
		return db.query(TABLE_NOTES, columnsWeWant, null, null, null, null, null);
	}
	
	public long insertNote(String noteText) {
		ContentValues newNote = new ContentValues();
		newNote.put(KEY_NOTE_TEXT, noteText);
		return db.insert(TABLE_NOTES, null, newNote);
	}
	
}
