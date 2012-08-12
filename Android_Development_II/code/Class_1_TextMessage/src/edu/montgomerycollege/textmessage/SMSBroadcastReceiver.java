package edu.montgomerycollege.textmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SMSBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Got a text message", Toast.LENGTH_SHORT).show();
		intent.getExtras();
	}

}
