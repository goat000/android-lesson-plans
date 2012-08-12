package edu.montgomerycollege.twittersearch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TwitterSearchActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button searchButton = (Button)findViewById(R.id.search_button);
        
        OnClickListener searchButtonListener = new OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		};
        
    }
}