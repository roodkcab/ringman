package me.shuoshi.ringman;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {

	 static final String ACTION = "android.intent.action.BOOT_COMPLETED";
	 
	 @Override
	 public void onReceive(Context context, Intent intent) {
		 Log.i("test", "service");
		 if (intent.getAction().equals(ACTION)){
			 Intent ringmanIntent = new Intent(context, RingmanService.class);
			 ringmanIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_EXCLUDE_STOPPED_PACKAGES);
			 context.startService(ringmanIntent);
		 }
	 }
}
