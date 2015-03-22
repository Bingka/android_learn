package com.example.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ColdReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ת��service��
		intent = new Intent("android.intent.action.BroadcastService"); 
		intent.addFlags(1);
		//����service
		context.startService(intent);
		//��־��ӡ
		Log.d("TEST","��̬ע��");
	}

}
