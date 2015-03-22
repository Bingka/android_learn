package com.example.notebook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	
	private List<String> listItems;
	private List<String> listItemTimes;
	private List<String> listImgs;
	private List<String> listContent;
	//private HashMap<String,String> listItems;
	
	private LayoutInflater inflater;
	
	public ListViewAdapter(Context context,List<String> listcontents,List<String> listImgs,List<String> listItems, List<String> times){
		this.listImgs=listImgs;
		this.listItems = listItems;
		this.listItemTimes = times;
		this.listContent = listcontents;
		inflater = (LayoutInflater) 
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	/**
	 * ���б������Ŀ
	 * @param item
	 */
	public void addListItem(String pic,String item, String time,String conn){
		listImgs.add(pic);
		listItems.add(item);
		listItemTimes.add(time);
		listContent.add(conn);
		
	}
	
	/**
	 * ɾ��ָ��λ�õ�����
	 * @param position
	 */
	public void removeListItem(int position){
		listImgs.remove(position);
		listItems.remove(position);
		listItemTimes.remove(position);
		listContent.remove(position);
	}

	
	/**
	 * ��ȡ�б������
	 */
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	/**
	 * ����������ȡ�б��Ӧ����������
	 */
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listItems.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * ͨ���ú�����ʾ����
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.note_list,null);
		}
		
		ImageView pic = (ImageView)convertView.findViewById(R.id.listImage);
		Bitmap bitmap = getLoacalBitmap(listImgs.get(position));
		pic.setImageBitmap(bitmap);
		TextView text = (TextView)convertView.findViewById(R.id.listItem);
		text.setText(listContent.get(position));
		
		TextView time = (TextView)convertView.findViewById(R.id.listItemTime);
		String datetime = DateFormat.format("yyyy-MM-dd kk:mm:ss", 
				Long.parseLong(listItemTimes.get(position))).toString();
		time.setText(datetime);
		
		return convertView;
	}


	public static Bitmap getLoacalBitmap(String url) {
		// TODO Auto-generated method stub
		 try {
             FileInputStream fis = new FileInputStream(url);
             return BitmapFactory.decodeStream(fis);  ///����ת��ΪBitmapͼƬ        

          } catch (FileNotFoundException e) {
             e.printStackTrace();
             return null;
        }
	}

}
