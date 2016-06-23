package com.example.logeservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent intentAlert = new Intent(context, AlertActivity.class);
		intentAlert.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intentAlert);
	}

}
