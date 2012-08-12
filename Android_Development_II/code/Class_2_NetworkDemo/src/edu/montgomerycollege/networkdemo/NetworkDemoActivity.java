package edu.montgomerycollege.networkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NetworkDemoActivity extends Activity {
	
	private Button launchButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        launchButton = (Button)findViewById(R.id.launch_task_button);
        
        OnClickListener ocl = new OnClickListener() {
			public void onClick(View v) {
				NetworkTask networkTask = new NetworkTask(NetworkDemoActivity.this);
				networkTask.execute();
			}        
        };
        
        launchButton.setOnClickListener(ocl);
    }
    
    public void setText(String result) {
    	TextView resultTextView = (TextView)findViewById(R.id.result_text);
    	resultTextView.setText(result);
    }
}