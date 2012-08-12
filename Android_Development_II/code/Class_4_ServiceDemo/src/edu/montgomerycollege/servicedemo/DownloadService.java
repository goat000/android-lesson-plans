package edu.montgomerycollege.servicedemo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

public class DownloadService extends Service {

	Timer timer = new Timer();
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}		

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, 
				"Started service", Toast.LENGTH_SHORT).show();
		//new BackgroundDownloadTask().execute("http://www.fakesite.com");
					
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				download("http://www.fakesite.com");				
			}			
		};
		
		timer.scheduleAtFixedRate(timerTask, 0, 15000);
		
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(this, 
				"Stopped service", Toast.LENGTH_SHORT).show();
		
		if (timer != null) {
			timer.cancel();
		}
		
		super.onDestroy();
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
				Toast.makeText(DownloadService.this, 
						"File Downloaded From Timer", Toast.LENGTH_SHORT).show();	
			}		
		});
	}
	
	private class BackgroundDownloadTask extends AsyncTask <String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			download(params[0]);
			return null;
		}
		
		@Override 
		protected void onPostExecute(Void result) {
			Toast.makeText(DownloadService.this, 
					"File Downloaded", Toast.LENGTH_SHORT).show();	
		}
		
	}
}
