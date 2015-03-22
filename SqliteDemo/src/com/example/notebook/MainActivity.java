package com.example.notebook;

import java.util.ArrayList;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private TextView tv;
	public static final int EDIT_STATE = 1;
	public static final int ALERT_STATE = 2;
	private ListViewAdapter adapter;// ����Դ����
	private DBManager dm = null;// ���ݿ�������
	private Cursor cursor = null;
    private Button addRecordButton;//����
	private Button deleteRecordButton;//ɾ��
	private Button modifyRecordButton;//�޸�
	private int pos=-1;
	private Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView=(ListView)this.findViewById(R.id.lv);
		addRecordButton = (Button)this.findViewById(R.id.newbn);
		modifyRecordButton = (Button)this.findViewById(R.id.editbn);
		deleteRecordButton = (Button)this.findViewById(R.id.delbn);
		tv = (TextView)this.findViewById(R.id.tv);
		dm = new DBManager(this);//���ݿ��������
		initAdapter();//��ʼ��
		listView.setAdapter(adapter);//�Զ�ΪidΪlist��ListView����������
		//���ð�ť������
        addRecordButton.setOnClickListener(new AddRecordListener());//����
        deleteRecordButton.setOnClickListener(new DeleteRecordListener());//ɾ��
        modifyRecordButton.setOnClickListener(new ModifyRecordListener());//�޸�
        listView.setOnItemClickListener 
        ( 
           new AdapterView.OnItemClickListener()  
           { 
               public void onItemClick(AdapterView adapterView, View view,int position, long id) 
               { 
            	// TODO Auto-generated method stub		
//       			Log.v("position", position+"");
//       			Log.v("id", id+"");
       			
       			cursor.moveToPosition(position);
       			pos=position;
       			tv.setText(cursor.getString(cursor.getColumnIndex("title")));
       			
       			//intent.putExtra("state", EDIT_STATE);
       			intent.putExtra("id", cursor.getInt(cursor.getColumnIndex("_id")));
       			intent.putExtra("title", cursor.getString(cursor.getColumnIndex("title")));
       			intent.putExtra("time", cursor.getString(cursor.getColumnIndex("time")));
       			intent.putExtra("content", cursor.getString(cursor.getColumnIndex("content")));
       			intent.putExtra("picture", cursor.getString(cursor.getColumnIndex("pic")));
       		//	cursor.close();
       			dm.close();
       			
       			//intent.setClass(MainActivity.this, NoteActivity.class);
       			//MainActivity.this.startActivity(intent);
               } 
           } 
        ); 
        
	}
	
	
	//��ʼ������Դ
		public void initAdapter(){
	    	
	    	dm.open();//�����ݿ��������
	    	
	    	cursor = dm.selectAll();//��ȡ��������
	    	
	    	cursor.moveToFirst();//���α��ƶ�����һ�����ݣ�ʹ��ǰ�������
	    	
	    	int count = cursor.getCount();//����
	    	ArrayList<String> contents = new ArrayList<String>();//ͼƬ�����м���
	    	ArrayList<String> imgs = new ArrayList<String>();//ͼƬ�����м���
	    	ArrayList<String> items = new ArrayList<String>();//��������м���
	    	ArrayList<String> times = new ArrayList<String>();//ʱ������м���
	    	for(int i= 0; i < count; i++){
	    		contents.add(cursor.getString(cursor.getColumnIndex("content")));
	    		imgs.add(cursor.getString(cursor.getColumnIndex("pic")));
	    		items.add(cursor.getString(cursor.getColumnIndex("title")));
	    		times.add(cursor.getString(cursor.getColumnIndex("time")));
	    		cursor.moveToNext();//���α�ָ����һ��
	    	}
	   // 	cursor.close();
	    	dm.close();//�ر����ݲ�������
	    	adapter = new ListViewAdapter(this,contents,imgs,items,times);//��������Դ
	    }
		
		@Override
		protected void onDestroy() {//����Activity֮ǰ����������
			// TODO Auto-generated method stub
			cursor.close();//�ر��α�
			super.onDestroy();
		}
		
		
		public class AddRecordListener implements OnClickListener{
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.putExtra("state", EDIT_STATE);
				intent.setClass(MainActivity.this, NoteActivity.class);
				MainActivity.this.startActivity(intent);
			}
			
		}
		public class ModifyRecordListener implements OnClickListener{

			public void onClick(View v) {
				intent.putExtra("state", ALERT_STATE);
				intent.setClass(MainActivity.this, NoteActivity.class);
				MainActivity.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
			
		}
		
		public class DeleteRecordListener implements OnClickListener{

			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					if(pos!=-1){
					dm.open();
					cursor.moveToPosition(pos);
					int i = dm.delete(cursor.getInt(cursor.getColumnIndex("_id")));//ɾ������
					dm.close();
					adapter.removeListItem(pos);//ɾ������
					adapter.notifyDataSetChanged();//֪ͨ����Դ�������Ѿ��ı䣬ˢ�½���
					
				//	Log.v("show", "chenggong1" + i);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
			
		}
		

}
