package me.shuoshi.ringman;
import me.shuoshi.ringman.R;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
//import android.database.Cursor;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;

public class RingmanActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ringman);
		if (!RingmanService.isInstanceStarted()) {
			this.startService(new Intent(this, RingmanService.class));
		}
		
		//this.initContacts();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Intent intent= new Intent(Intent.ACTION_VIEW, Contacts.CONTENT_URI);
        this.startActivity(intent);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ringman, menu);
		return true;
	}
	
	private void initContacts() {
		ListView contacts = new ListView(this);
		Cursor cursor = this.getContentResolver().query(CommonDataKinds.Phone.CONTENT_URI, null, null, null, CommonDataKinds.Phone.SORT_KEY_PRIMARY);
		//Cursor cursor = this.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"_id", "address"}, "0=0) group by (address", null, "address desc");  
		if (cursor != null) {
			ListAdapter listAdapter = new SimpleCursorAdapter(this, 
					R.layout.list_contact, 
	                cursor,
	                new String[]{CommonDataKinds.Phone.DISPLAY_NAME}, 
	                new int[]{R.id.contact_name},
	                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
			contacts.setAdapter(listAdapter);
			setContentView(contacts);
		}
	}*/
	
}