package edu.montgomerycollege.sharedpref;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedprefActivity extends Activity {
    
	private EditText input;
	private Button saveButton;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        input = (EditText)findViewById(R.id.input);
        saveButton = (Button)findViewById(R.id.save_button);
        
        final SharedPreferences prefs = 
				SharedprefActivity.this.getSharedPreferences("sharedprefs", MODE_PRIVATE);
        
        OnClickListener ocl = new OnClickListener() {
			public void onClick(View v) {
				String sInput = input.getText().toString();
								
				SharedPreferences.Editor prefEditor = prefs.edit();
				prefEditor.putString("saved_value", sInput);
				prefEditor.commit();				
			}
        };        
        saveButton.setOnClickListener(ocl);
        
        String savedValue = prefs.getString("saved_value", "");
        Toast.makeText(this, savedValue, Toast.LENGTH_SHORT).show();
    }
}