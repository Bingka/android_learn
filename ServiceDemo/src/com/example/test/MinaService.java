package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MinaService extends Service{
	private String msg = "service bind success";
	
	private final IBinder mBinder = new LocalBinder();

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("TEST", "onbind");
		//���ڰ�Activity
		return mBinder;
	}

	@Override
	public void onCreate() {
		//��������ʱ�����ȵ��ø÷���
		Log.d("TEST", "onCreate");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//start serviceʱ����onCreate����ø÷���
		Log.d("TEST", "start");
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		// ֹͣservice�����ô˷���
		Log.d("TEST", "destroy");
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		//ȡ��Activity��service��ʱҪ���ô˷���
		Log.d("TEST", "onunbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		// ���°�ʱ���ô˷���
		Log.d("TEST", "onrebind");
		super.onRebind(intent);
	}

	
	/**
	 * @author cpacm
	 * ͨ��Binder����service�����õ�Activity��
	 */
	public class LocalBinder extends Binder {
		MinaService getService() {
			return MinaService.this;
        }
	}
	
	//��ͨ������֤��service�ں�̨����
	public String getMsg(){
		return msg;
	}
	public void setMsg(String m){
		this.msg = m;
	}
	
}
