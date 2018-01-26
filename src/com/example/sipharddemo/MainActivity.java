package com.example.sipharddemo;




import android.os.Bundle;
import android.text.TextUtils;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import junit.framework.Test;

public class MainActivity extends Activity {
	
	private boolean send;
	private BroadcastReceiver mBroadcastRecv;
	
	EditText et_server;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//MessageFactory.createXml(MessageType.Inform,null);
		et_server=(EditText)findViewById(R.id.et_conn_url);
		et_server.setText("http://192.168.0.191:80");
		registerBroadcast();
		
		//test
		test();
	
	}
	
	void test(){
		String string=MessageFactory.wcreateInformXml(null);
		Log.e("wzb","info="+string);
	}
	
	
	
	public void onClick(View view){
		String ser_url=et_server.getText().toString();
		if(TextUtils.isEmpty(ser_url)){
			Toast.makeText(this, "请输入有效的服务器地址", Toast.LENGTH_SHORT).show();
			return;
		}
		android.os.SystemProperties.set("tr_server_url", ser_url);
		WApplication.ACS_URL=ser_url;
		if(!send){
			SipTerminal.getInstance().startConnectionWithACS();
			send = true;
			Toast.makeText(this, "已经发送请等待", Toast.LENGTH_SHORT).show();
		}else{
			
			Toast.makeText(this, "请不要重复连接", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	private void registerBroadcast() {

		mBroadcastRecv = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
//				Log.i("", msg);
				send =false;
				
				Toast.makeText(MainActivity.this, "成功收到注册广播", Toast.LENGTH_SHORT).show();
			}
		};

		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.fanvil.Settings.intent.action.REGISTER");
		registerReceiver(mBroadcastRecv, intentFilter);
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mBroadcastRecv);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}

}
