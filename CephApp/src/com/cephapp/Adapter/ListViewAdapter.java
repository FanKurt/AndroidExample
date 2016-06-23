package com.cephapp.Adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListViewAdapter extends BaseAdapter{
	
	private ArrayList<View> mArrayList;
	public ListViewAdapter (ArrayList<View> mArrayList){
		this.mArrayList = mArrayList;
	}
	public int getCount() {
		return mArrayList.size();
	}
	public Object getItem(int position) {
		return mArrayList.get(position);
	}
	public long getItemId(int position) {
		return mArrayList.get(position).getId();
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		return mArrayList.get(position);
	}

}
