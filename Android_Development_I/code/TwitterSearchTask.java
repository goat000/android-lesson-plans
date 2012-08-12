package edu.montgomerycollege.yourpackagename;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class TwitterSearchTask extends AsyncTask<String, Void, String> {

	interface TwitterSearchListener {
		public void onSearchResult(String searchResult);
	}
	
	private Context context;
	private ProgressDialog pDog;
	private TwitterSearchListener listener;
	
	public TwitterSearchTask(Context context, TwitterSearchListener listener) {
		this.context = context;		
		this.listener = listener;
	}
	
	@Override 
	protected void onPreExecute() {
		pDog = ProgressDialog.show(context, "Searching...", "", true);
	}
	
	@Override
	protected String doInBackground(String... params) {
		String query = "http://search.twitter.com/search.json?q=" + Uri.encode(params[0]);
		HttpURLConnection urlConnection = null;
		String topTweet = null;
		try {
			URL searchUrl = new URL(query);
			urlConnection = (HttpURLConnection) searchUrl.openConnection();

			InputStream in = urlConnection.getInputStream();
			String sResult = convertStreamToString(in);
			JSONObject jResult = new JSONObject(sResult);
			JSONArray results = jResult.getJSONArray("results");
			topTweet = results.getJSONObject(0).getString("text");
		} catch (Exception e) {
			Log.e("TwitterSearchTask", "Error grabbing Twitter result", e);
		} finally {
			if (urlConnection != null) urlConnection.disconnect();			
		}


		return topTweet;
	}
	
	@Override
	protected void onPostExecute(String result) {
		if (pDog != null && pDog.isShowing()) pDog.dismiss();
		listener.onSearchResult(result);
	}

	
	//courtesy of http://www.kodejava.org/examples/266.html
	private String convertStreamToString(InputStream is)
			throws IOException {
		/*
		 * To convert the InputStream to String we use the
		 * Reader.read(char[] buffer) method. We iterate until the
		 * Reader return -1 which means there's no more data to
		 * read. We use the StringWriter class to produce the string.
		 */
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
			return "";
		}
	}
}
