package com.example.sipharddemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CoreService extends Service{
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("wzb","tr069 CoreService onCreate");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		Log.d("wzb","tr069 CoreService onStartCommand");
		String url=intent.getStringExtra("acs_url");
		WApplication.ACS_URL=url;
		SipTerminal.getInstance().reConnectionWithACS();
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("wzb","tr069 CoreService onDestroy");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
