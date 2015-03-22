package com.example.broadcastdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


public class MainActivity extends Activity {

	private HotReceiver receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��̬ע��㲥
		//�½�һ���㲥����
	    receiver = new HotReceiver();  
	    //�½�һ��intent������ƣ��������Ƕ�������й��ˣ�ֻ��ȡ��Ҫ����Ϣ��
		IntentFilter filter = new IntentFilter(); 
		//��Ӱ�������ֻ��ȡ�ö�������Ϣ��
		filter.addAction("android.intent.action.HOT_BROADCAST");          
		//��㲥�󶨣�����ע��
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		//ȡ��ע�ᣬһ��Ҫ�ǵã���Ȼϵͳ�ᱨ����
		unregisterReceiver(receiver);
		stopService(new Intent("android.intent.action.BroadcastService"));
		super.onDestroy();
	}

}
