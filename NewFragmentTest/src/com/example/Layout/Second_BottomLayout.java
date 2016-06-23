package com.example.Layout;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.IMAC.MainParentLayout;

public class Second_BottomLayout extends MainParentLayout {
	Button btn;
	public Second_BottomLayout(Context context) {
		super(context);
	}

	protected void init() {
		LayoutParams LP = getLayoutParams(MATCH_PARENT, WRAP_CONTENT);
		LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		btn = new Button(context);
		btn.setLayoutParams(LP);
		this.addView(btn);
	}

	public Button getButton(){
		return btn;
	}

}
