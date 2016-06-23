package com.imac.Controller;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout.LayoutParams;

import com.example.blockeditor.R;
import com.imac.Activity.MainActivity;
import com.imac.Framework.WH;
import com.imac.Layout.MainLayout;
import com.imac.ViewGroup.SingleTouchView;

public class MainController  {
	private Context context;
	private MainLayout mainLayout;
	private WH WH ;
	private ArrayList<String> spinnerList;
	private int VIEWCOUNT =0;
	public MainController(Context context, MainLayout mainLayout) {
		this.context = context;
		this.mainLayout = mainLayout;
		WH = new WH(context);
		spinnerList = new ArrayList<String>();
		
		init();
	}

	private void init(){
		
		upadteSpinner();
		
		mainLayout.getButton_Add().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				VIEWCOUNT++;
				upadteSpinner();
				addSingleTouchView();
			}
		});
		
		mainLayout.getSpinner().setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				setINVISIBLE();
				((SingleTouchView)mainLayout.getRelativeLayout().getChildAt(position)).setEditable(true);
			
			}
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		
	}
	
	
	private void addSingleTouchView() {
		LayoutParams LP =new LayoutParams(WH.getH(15) , WH.getH(15));
		SingleTouchView singleTouchView = new SingleTouchView(context);
		singleTouchView.setImageResource(R.drawable.ic_launcher);
		singleTouchView.setFrameColor(Color.BLUE);
		singleTouchView.setEditable(false);
		singleTouchView.setLayoutParams(LP);
		mainLayout.getRelativeLayout().addView(singleTouchView);
		
		mainLayout.getSpinner().setSelection(VIEWCOUNT);
	}


	private void setINVISIBLE() {
		for(int i=0 ; i<mainLayout.getRelativeLayout().getChildCount();i++){
			((SingleTouchView)mainLayout.getRelativeLayout().getChildAt(i)).setEditable(false);
		}
	}

	private void upadteSpinner() {
		spinnerList.add(""+VIEWCOUNT);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(((MainActivity)context), android.R.layout.simple_spinner_item, spinnerList);  
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
		mainLayout.getSpinner().setAdapter(adapter);
	}
}
