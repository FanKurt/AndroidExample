package com.example.testhistogram;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class HistogramController {
	private Context context;
	HistogramLayout HVL;
	public HistogramController(Context context ,HistogramLayout HVL ) {
		this.context =context;
		this.HVL =HVL;
		init();
	}
	private void init(){
		HVL.getButton().setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				HVL.getHV().i =0;
				HVL.getHV().spacing =0;
				HVL.getHV().count =1;
				HVL.getHV().invalidate();
			}
		});
	}
}
