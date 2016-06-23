package com.XmppClient.Adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListViewAdapter extends BaseAdapter{
	private ArrayList<View> arrayList;
	public ListViewAdapter(ArrayList<View> arrayList){
		this.arrayList = arrayList;
	}
	
	public int getCount() {
		return arrayList.size();
	}

	
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	
	public long getItemId(int position) {
		return 0;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
		return arrayList.get(position);
	}

}
