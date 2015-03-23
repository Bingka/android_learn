package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class DBHelper extends SQLiteOpenHelper{
    private static final int VERSION=1;
    /** 
     * ��SQLiteOpenHelper�����൱�У������иù��캯�� 
     * @param context   �����Ķ��� 
     * @param name      ���ݿ����� 
     * @param factory 
     * @param version   ��ǰ���ݿ�İ汾��ֵ���������������ǵ�����״̬ 
     */
    public DBHelper(Context context,String name,CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    public DBHelper(Context context, String name, int version){  
        this(context,name,null,version);  
    }  
  
    public DBHelper(Context context, String name){  
        this(context,name,VERSION);  
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
         // ���ݿ��״ι���ʱ������øú��������������ﹹ����������ȵ� 
        System.out.println("create a database");  
        //execSQL����ִ��SQL���  
        db.execSQL("create table notebook(_id integer primary key autoincrement,title varchar(20),content text,time long)");
        
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ��������ĵ�ǰ���ݿ�汾�����������ݿ�汾�����øú���
        System.out.println("upgrade a database");
    }  

}
