package edu.montgomerycollege.webviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        WebView webView = (WebView)findViewById(R.id.test_webview);
        webView.loadUrl("http://m.google.com");
        
        webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setBuiltInZoomControls(true);
        
        WebViewClient client = new WebViewClient() {
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView wView, String url) {
        		Toast.makeText(WebViewTestActivity.this, "You clicked a link.", Toast.LENGTH_SHORT).show();
        		return true;
        	}
        };
        
        webView.setWebViewClient(client);
        
    }
}