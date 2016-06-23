package com.HeadSet.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.HeadSet.Controller.HeadSetController;
import com.HeadSet.Layout.HeadSetLayout;

public class HeadSetActivity extends Activity {
	private HeadSetLayout HSL;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(HSL =new HeadSetLayout(this));
		new HeadSetController(this, HSL);
	}

}
