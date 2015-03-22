package com.example.notebook;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DBManager {
	
private Context mContext = null;
	
	private SQLiteDatabase mSQLiteDatabase = null;//���ڲ������ݿ�Ķ���
	private DBHelper dh = null;//���ڴ������ݿ�Ķ���
	
	private String dbName = "note.db";
	private int dbVersion = 1;
	public DBManager(Context context){
		mContext = context;
	}
	
public void open(){
		try{
			dh = new DBHelper(mContext, dbName, null, dbVersion);//�������ݿ�
			if(dh == null){
				Log.v("msg", "is null");
				return ;
			}
			mSQLiteDatabase = dh.getWritableDatabase();//�Կɶ���ʽ�����ݿ�
			//dh.onOpen(mSQLiteDatabase);
		}catch(SQLiteException se){
			se.printStackTrace();
		}
	}
public void close(){
	
	mSQLiteDatabase.close();
	dh.close();
	
}
public Cursor selectAll(){
	Cursor cursor = null;
	try{
		String sql = "select * from notebook";
		cursor = mSQLiteDatabase.rawQuery(sql, null);
	}catch(Exception ex){
		ex.printStackTrace();
		cursor = null;
	}
	return cursor;
}
public Cursor selectById(int id){
	
	//String result[] = {};
	Cursor cursor = null;
	try{
		String sql = "select * from notebook where _id='" + id +"'";
		cursor = mSQLiteDatabase.rawQuery(sql, null);
	}catch(Exception ex){
		ex.printStackTrace();
		cursor = null;
	}
	
	return cursor;
}
public long insert(String title, String content,String pic){
	
	long datetime = System.currentTimeMillis();
	long l = -1;
	try{
		ContentValues cv = new ContentValues();
		cv.put("title", title);
		cv.put("content", content);
		cv.put("time", datetime);
		cv.put("pic", pic);
		l = mSQLiteDatabase.insert("notebook", null, cv);
	//	Log.v("datetime", datetime+""+l);
	}catch(Exception ex){
		ex.printStackTrace();
		l = -1;
	}
	return l;
	
}
public int delete(int id){
	int affect = 0;
	try{
		affect = mSQLiteDatabase.delete("notebook", "_id=?", new String[]{String.valueOf(id)});
	}catch(Exception ex){
		ex.printStackTrace();
		affect = -1;
	}
	
	return affect;
}
public int update(int id, String title, String content,String pic){
	int affect = 0;
	try{
		  	
		ContentValues cv = new ContentValues();
		cv.put("title", title);
		cv.put("content", content);
		cv.put("pic", pic);
		String w[] = {String.valueOf(id)};
		affect = mSQLiteDatabase.update("notebook", cv, "_id=?", w);
	}catch(Exception ex){
		ex.printStackTrace();
		affect = -1;
	}
	return affect;
}

}

