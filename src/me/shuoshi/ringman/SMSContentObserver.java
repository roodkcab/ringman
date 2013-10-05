package me.shuoshi.ringman;

import java.net.URI;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.ContentObserver;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Contacts;
import android.util.Log;

public class SMSContentObserver extends ContentObserver {
    private static String TAG = "SMSContentObserver";
    private Context mContext = null;
    
    public SMSContentObserver(Context context, Handler handler) {  
        super(handler);  
        mContext = context;
    }  
	
	/**
	 * 当所监听的Uri发生改变时，就会回调此方法
	 * 
	 * @param selfChange  此值意义不大 一般情况下该回调值false
	 */
    @Override
	public void onChange(boolean selfChange){
    	// TODO Auto-generated method stub  
        super.onChange(selfChange);  
        //读取收件箱中指定号码的短信
        Cursor cursor = mContext.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"_id", "address", "read"}, " read=?", new String[]{"0"}, "date desc limit 1");  
        if (cursor.moveToFirst()) {
            String address = cursor.getString(1);
            cursor = mContext.getContentResolver().query(CommonDataKinds.Phone.CONTENT_URI, new String[]{CommonDataKinds.Phone.CUSTOM_RINGTONE}, CommonDataKinds.Phone.NORMALIZED_NUMBER+"=?", new String[]{address}, null);
            if (cursor.moveToFirst()) {
            	String tongUri = cursor.getString(0);
            	if (tongUri != null) {
            		Ringtone r = RingtoneManager.getRingtone(mContext, Uri.parse(tongUri));  
                	r.play();
            	}
            }
        }  
	}
}