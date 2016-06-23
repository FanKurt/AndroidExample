package com.example.logeservicedemo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class Servicetest extends Service {
	private MyReceiver receiver;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onCreate() {
		super.onCreate();
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.cloay.receiver");
		registerReceiver(receiver, filter);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				KeyguardManager mKeyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
				if (!mKeyguardManager.inKeyguardRestrictedInputMode()) {
					Log.e("AlertNotifi", "Screen is on.....");

				} else {
					Log.e("AlertNotifi", "Screen is off.....");
					Intent intentReceiver = new Intent("com.cloay.receiver");
					sendBroadcast(intentReceiver);
				}
			}
		}, 3 * 1000, 3 * 1000); // send a new message every 10 seconds.
	}

	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

}
