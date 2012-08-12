package edu.montgomerycollege.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class DownloadIntentService extends IntentService {

	public DownloadIntentService() {
		super("intentservice");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		download("http://www.fakesite.com");
	}
	
	private void download(String url) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ie) {
			//ignore
		}	
		
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {
			public void run() {
				Toast.makeText(DownloadIntentService.this, 
						"File Downloaded From IntentService", Toast.LENGTH_SHORT).show();	
			}		
		});
	}

}
