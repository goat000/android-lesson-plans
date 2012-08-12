package edu.montgomerycollege.textmessage;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TextMessageActivity extends Activity {
	
	private EditText inputWidget;
	private Button sendButton;
	
	private SMSBroadcastReceiver receiver;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        inputWidget = (EditText)findViewById(R.id.message_input);
        sendButton = (Button)findViewById(R.id.send_button);
        
        OnClickListener ocl = new OnClickListener() {
			public void onClick(View v) {
				String message = inputWidget.getText().toString();
				
				SmsManager smsMgr = SmsManager.getDefault();
				smsMgr.sendTextMessage("5556", null, message, null, null);
			}        	
        };
        sendButton.setOnClickListener(ocl);
        
        receiver = new SMSBroadcastReceiver();
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
    	registerReceiver(receiver, filter);
    }
    
    public void onStop() {
    	super.onStop();
    	unregisterReceiver(receiver);
    }
}