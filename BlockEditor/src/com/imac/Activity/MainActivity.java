package com.imac.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.imac.Controller.MainController;
import com.imac.Layout.MainLayout;

public class MainActivity extends Activity {
	private MainLayout ML;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(ML = new MainLayout(this));
		new MainController(this, ML);
	}

}
