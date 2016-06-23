package com.cephapp.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.cephapp.Activity.FragmentActivity;
import com.cephapp.Layout.LoginLayout;


public class LoginController {
	private Context context;
	private LoginLayout mLayout;
	public LoginController(Context contex , LoginLayout mLayout){
		this.context =contex;
		this.mLayout = mLayout;
		init();
	}
	private void init(){
		mLayout.getButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context,FragmentActivity.class );
				context.startActivity(intent);
			}
		});
	}
}
