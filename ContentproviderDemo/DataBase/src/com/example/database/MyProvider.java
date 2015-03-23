package com.example.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyProvider extends ContentProvider {

	private DBHelper dh = null;// ���ݿ�������
	private SQLiteDatabase db;//��ȡ���е����ݿ�
	//UriMatcher:Creates the root node of the URI tree.
	//���չٷ����ͣ�UriMatcher��һ��Uri������Ȼ������addURI()�������������֦�ɣ�ͨ��match()����������֦�ɡ�
	private static final UriMatcher MATCHER = new UriMatcher(
			UriMatcher.NO_MATCH);
	//�趨ƥ����
	private static final int NOTEBOOK = 1;
	private static final int NOTEBOOKS = 2;
	static {
		//���֦�ɣ��������Ǽ���Ψһ��ƥ���룬�Է������
		//���match()����ƥ��content://com.example.database/notebook·��������ƥ����Ϊ1
		MATCHER.addURI("com.example.database", "notebook", NOTEBOOKS);
		//���match()����ƥ��content://com.example.database/notebook/#·��������ƥ����Ϊ2
		//����#��Ϊͨ�����
		MATCHER.addURI("com.example.database", "notebook/#", NOTEBOOK);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dh = new DBHelper(this.getContext(),"note.db");// ���ݿ��������
		db = dh.getReadableDatabase();
		return false;
	}

	/** 
	 * ��ѯ������Cursor
	 **/
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		//ͨ��match��������ȡƥ����
		switch (MATCHER.match(uri)) {
		case NOTEBOOKS:
			//�������ݿ�����Ľ��
			return db.query("notebook", projection, selection, selectionArgs,
					null, null, sortOrder);
		case NOTEBOOK:
			//��Ϊ��� ��id,����Ҫ��id�ӵ�where������
			long id = ContentUris.parseId(uri);
			String where = "_id=" + id;
			if (selection != null && !"".equals(selection)) {
				where = selection + " and " + where;
			}
			return db.query("notebook", projection, where, selectionArgs, null,
					null, sortOrder);
		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	//��ȡUri������
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (MATCHER.match(uri)) {  
        case NOTEBOOKS:  
            return "com.example.Database.all/notebook";  
  
        case NOTEBOOK:  
            return "com.example.Database.item/notebook";  
  
        default:  
            throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());  
        }
	}

	//��������
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		switch (MATCHER.match(uri)) {  
        case NOTEBOOKS:  
        	//�������ݿ�Ĳ��빦��
            // �ر�˵һ�µڶ��������ǵ�title�ֶ�Ϊ��ʱ�����Զ�����һ��NULL��  
            long rowid = db.insert("notebook", "title", values);  
            Uri insertUri = ContentUris.withAppendedId(uri, rowid);// �õ�����������¼��Uri  
            this.getContext().getContentResolver().notifyChange(uri, null);  
            return insertUri;  
  
        default:  
            throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());  
        }
	}

	//ɾ������
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		int count;
		switch (MATCHER.match(uri)) {  
        case NOTEBOOKS:  
            count = db.delete("notebook", selection, selectionArgs);  
            return count;  
  
        case NOTEBOOK:  
            long id = ContentUris.parseId(uri);  
            String where = "_id=" + id;  
            if (selection != null && !"".equals(selection)) {  
                where = selection + " and " + where;  
            }  
            count = db.delete("notebook", where, selectionArgs);  
            return count;  
  
        default:  
            throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());  
        }
	}

	//��������
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int count = 0;  
        switch (MATCHER.match(uri)) {  
        case NOTEBOOKS:  
            count = db.update("notebook", values, selection, selectionArgs);  
            return count;  
        case NOTEBOOK:  
            long id = ContentUris.parseId(uri);  
            String where = "_id=" + id;  
            if (selection != null && !"".equals(selection)) {  
                where = selection + " and " + where;  
            }  
            count = db.update("notebook", values, where, selectionArgs);  
            return count;  
        default:  
            throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());  
        } 
	}

}
