package edu.montgomerycollege.networkdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class NetworkTask extends AsyncTask<String, Void, String> {

	private ProgressDialog pDog;
	private NetworkDemoActivity activity;
	
	public NetworkTask(NetworkDemoActivity activity) {
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		pDog = ProgressDialog.show(activity, "", "Loading...");
	}
	
	@Override
	protected String doInBackground(String... params) {
		InputStream in = null;
	    HttpURLConnection urlConnection = null;
		String sResult = null;
	    
		try {
			URL searchUrl = new URL("http://www.gamesover.com/walkthroughs/Android%20Pinball.txt");
			urlConnection = (HttpURLConnection) searchUrl.openConnection();
			in = urlConnection.getInputStream();
			sResult = convertStreamToString(in);
		} catch (Exception e) {
			Log.e("NetworkError", "Error grabbing result", e);
		} finally {
			try {
				if (in != null) in.close();
			} catch (Exception e) {
				//don't care
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		
		return sResult;
	}
	
	@Override
	protected void onPostExecute(String result) {
		if (pDog != null && pDog.isShowing()) {
			pDog.dismiss();
		}
		//Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
		activity.setText(result);
	}
	
	//courtesy of http://www.kodejava.org/examples/266.html
	private String convertStreamToString(InputStream is)
			throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(
						new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {  
			Log.d("NetworkError", "No stream found in convertStreamToString");
			return "";
		}
	}

}
