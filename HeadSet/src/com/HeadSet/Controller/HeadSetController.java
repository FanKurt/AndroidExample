package com.HeadSet.Controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.HeadSet.Layout.HeadSetLayout;

public class HeadSetController {
	private Context context;
	private HeadSetLayout HSL;
	public HeadSetController(Context contex , HeadSetLayout HSL){
		this.context = contex;
		this.HSL = HSL;
		init();
	}
	private void init(){
		HSL.getButton().setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.e("1", "1");
			}
		});
	}
}
