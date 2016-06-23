package com.example.testhistogram;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class HistogramLayout extends RelativeLayout {
	Context context;
	HistogramView HV;
	Button btn;

	public HistogramLayout(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init() {
		{
			LayoutParams LP = new LayoutParams(1000, 1000);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			HV = new HistogramView(context);
			int [] per = {870 , 760 , 960};
			for(int i =0;i<per.length;i++){
				HV.setPercent(per[i]);
			}
			HV.setLayoutParams(LP);
			this.addView(HV);
		}

		{
			LayoutParams LP = new LayoutParams(200, 200);
			btn = new Button(context);
			btn.setLayoutParams(LP);
			this.addView(btn);
		}
	}

	public HistogramView getHV() {
		return HV;
	}

	public Button getButton() {
		return btn;
	}
}
