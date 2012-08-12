package edu.montgomerycollege.twittersearch;

import java.util.List;

import edu.montgomerycollege.twittersearch.TwitterSearchTask.TwitterSearchListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TwitterSearchResultActivity extends Activity implements TwitterSearchListener {
	
	private ListView searchResultList;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        searchResultList = (ListView)findViewById(R.id.search_result);
        
        OnItemClickListener resultClickListener = new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View itemView, int position,
					long id) {
				Toast.makeText(TwitterSearchResultActivity.this, "Item clicked: " + position, Toast.LENGTH_SHORT).show();
			}
        };
        
        searchResultList.setOnItemClickListener(resultClickListener);
        
        String query = getIntent().getStringExtra("query");
        //Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        
		TwitterSearchTask searchTask = 
				new TwitterSearchTask(this, this);
		searchTask.execute(query);
	}

	public void onSearchResult(List<String> searchResult) {
		// TODO Auto-generated method stub
		//if (searchResult != null && searchResult.size() > 0) {
		//	this.searchResult.setText(searchResult.get(0));
		//}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searchResult);
		searchResultList.setAdapter(adapter);
	}
}
