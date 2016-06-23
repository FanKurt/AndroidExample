package com.example.Layout;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.Activity.R;
import com.example.IMAC.MainParentLayout;

public class BottomLayout extends MainParentLayout {
	Button btn;
	public BottomLayout(Context context) {
		super(context);
	}

	protected void init() {
		LayoutParams LP = getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
		LP.addRule(RelativeLayout.CENTER_IN_PARENT);
		btn = new Button(context);
		btn.setLayoutParams(LP);
		this.addView(btn);
	}

	public Button getButton(){
		return btn;
	}

}
