package edu.montgomerycollege.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ServiceDemoActivity extends Activity {
    
	private Button startServiceBtn, stopServiceBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        startServiceBtn = (Button)findViewById(R.id.start_button);
        stopServiceBtn = (Button)findViewById(R.id.stop_button);
        
        startServiceBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(ServiceDemoActivity.this, "Pressed start", 
				//		Toast.LENGTH_SHORT).show();
				Intent i = new Intent(ServiceDemoActivity.this, DownloadIntentService.class);
				startService(i);
			}
        });
        
        stopServiceBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(ServiceDemoActivity.this, "Pressed stop", 
				//		Toast.LENGTH_SHORT).show();	
				Intent i = new Intent(ServiceDemoActivity.this, DownloadIntentService.class);
				stopService(i);
			}
        });
    }
}