package me.shuoshi.ringman;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class RingmanService extends Service {
	
	private static RingmanService instance = null;

	public static boolean isInstanceStarted() { 
		return instance != null; 
	}
	
	@Override  
	public IBinder onBind(Intent intent) {  
	    return null;  
	}  

	@Override  
	public void onCreate() {  
	    super.onCreate();
	}  

	@Override  
	public void onStart(Intent intent, int startId) {
	    instance = this;
	    super.onStart(intent, startId);  
	    SMSContentObserver observer = new SMSContentObserver(this, new Handler());  
        //×¢²áÄÚÈÝ¹Û²ìÕß  
		this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, observer);
	}  

	@Override  
	public int onStartCommand(Intent intent, int flags, int startId) {  
	    return super.onStartCommand(intent, flags, startId);  
	}  
	
	@Override 
	public void onDestroy() {
		instance = null;
	}
}
