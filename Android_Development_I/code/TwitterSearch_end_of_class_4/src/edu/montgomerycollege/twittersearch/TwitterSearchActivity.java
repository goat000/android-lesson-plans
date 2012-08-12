package edu.montgomerycollege.twittersearch;

import edu.montgomerycollege.twittersearch.TwitterSearchTask.TwitterSearchListener;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TwitterSearchActivity extends Activity implements TwitterSearchListener {
    
	private Button searchButton;
	private EditText searchInput;
	private TextView searchResult;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        searchButton = (Button)findViewById(R.id.search_button);
        searchInput = (EditText)findViewById(R.id.search_input);
        searchResult = (TextView)findViewById(R.id.search_result);
        
        OnClickListener searchButtonListener = new OnClickListener() {			
			public void onClick(View v) {
				String input = searchInput.getText().toString();
				//Intent resultIntent = 
				//		new Intent(TwitterSearchActivity.this, TwitterSearchResultActivity.class);
				//resultIntent.putExtra("query", input);
				//startActivity(resultIntent);
				Intent browseIntent = 
						new Intent(Intent.ACTION_VIEW, 
								Uri.parse("http://www.montgomerycollege.edu"));
				startActivity(browseIntent);
			}
		};
		
		searchButton.setOnClickListener(searchButtonListener);
        
    }

	public void onSearchResult(String searchResult) {
		//Toast.makeText(TwitterSearchActivity.this, 
		//		searchResult, Toast.LENGTH_SHORT).show();
		this.searchResult.setText(searchResult);
	}
}