package edu.montgomerycollege.twittersearch;

import edu.montgomerycollege.twittersearch.TwitterSearchTask.TwitterSearchListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TwitterSearchResultActivity extends Activity implements TwitterSearchListener {

	private TextView searchResult;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        searchResult = (TextView)findViewById(R.id.search_result);
        
        String query = getIntent().getStringExtra("query");
        //Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        
		TwitterSearchTask searchTask = 
				new TwitterSearchTask(this, this);
		searchTask.execute(query);
	}

	public void onSearchResult(String searchResult) {
		// TODO Auto-generated method stub
		this.searchResult.setText(searchResult);
	}
}
