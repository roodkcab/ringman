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
	 * ����������Uri�����ı�ʱ���ͻ�ص��˷���
	 * 
	 * @param selfChange  ��ֵ���岻�� һ������¸ûص�ֵfalse
	 */
    @Override
	public void onChange(boolean selfChange){
    	// TODO Auto-generated method stub  
        super.onChange(selfChange);  
        //��ȡ�ռ�����ָ������Ķ���
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