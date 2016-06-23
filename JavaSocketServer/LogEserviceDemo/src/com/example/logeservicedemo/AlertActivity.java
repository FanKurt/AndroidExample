package com.example.logeservicedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AlertActivity extends Activity {
	private TextView titleTextV;
	private TextView msgTextV;

	private Button cancleBtn;
	private Button okBtn;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		setContentView(R.layout.alert);

		titleTextV = (TextView) findViewById(R.id.msg_title_textV);
		titleTextV.setText("New message: ");
		msgTextV = (TextView) findViewById(R.id.msg_content_textV);
		msgTextV.setText("Hi, I am !");
	}
}
