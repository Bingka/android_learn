package com.example.broadcastdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BroadcastService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		//��������ʱ�����ȵ��ø÷���
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//����ÿ��intent����������Ϣ�����ж�����ʾ��ͬ��Ϣ
		switch(intent.getFlags()){
			case 1:{
				Toast.makeText(getApplicationContext(), "��̬ע��", Toast.LENGTH_SHORT).show();
				break;
			}
			case 2:{
				Toast.makeText(getApplicationContext(), "��̬ע��", Toast.LENGTH_SHORT).show();
				break;
			}
			case 3:{
				Toast.makeText(getApplicationContext(), "��ͨ�㲥", Toast.LENGTH_SHORT).show();
				break;
			}
			case 4:{
				Toast.makeText(getApplicationContext(), "����㲥", Toast.LENGTH_SHORT).show();
				break;
			}
		}
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		// ֹͣservice�����ô˷���
		Log.d("TEST", "destroy");
		super.onDestroy();
	}

}
