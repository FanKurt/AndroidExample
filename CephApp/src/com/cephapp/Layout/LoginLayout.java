package com.cephapp.Layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IMAC.FrameWork.MainParentLayout;
import com.cephapp.View.CheckView;
import com.example.cephapp.R;

public class LoginLayout extends MainParentLayout{
	private RelativeLayout actionBar , loginBackground;
	private CheckView mCheckView ; 
	private View icon_view;
	private TextView login_text;
	private EditText url_edit , user_edit , paw_edit;
	private Button signin_button;
	public LoginLayout(Context context) {
		super(context);
	}

	protected void init() {
		this.setBackgroundColor(Color.rgb(200, 204, 215));
		Bar();
		Login();
		URL();
		USER();
		PASSWORD();
		CHECK();
		SIGNIN();
		Verion();
	}

	private void Verion() {
		{
			LayoutParams LP = getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
			LP.bottomMargin = WH.getH(1);
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			TextView mTextView = new TextView(context);
			mTextView.setText("Copyright 2014-2015 i.m.a.c. All rights reserved.");
			mTextView.setTextSize(PX, WH.getTextSize(26));
			mTextView.setTextColor(Color.BLACK);
			mTextView.setLayoutParams(LP);
			this.addView(mTextView);
		}		
	}

	private void SIGNIN() {
		{
			LayoutParams LP = getLayoutParams(WH.getW(30), WH.getH(6));
			LP.addRule(RelativeLayout.ALIGN_BOTTOM , mCheckView.getId());
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			LP.topMargin = WH.getH(2);
			LP.rightMargin = WH.getH(2);
			signin_button = new Button(context);
			signin_button.setBackgroundColor(Color.rgb(45, 120, 177));
			signin_button.setTextColor(Color.WHITE);
			signin_button.setTextSize(PX, WH.getTextSize(32));
			signin_button.setText("Sign In");
			signin_button.setId(getRandomId());
			signin_button.setLayoutParams(LP);
			loginBackground.addView(signin_button);
		}
	}

	private void CHECK() {
		{
			LayoutParams LP = getLayoutParams(WH.getH(4), WH.getH(4));
			LP.addRule(RelativeLayout.BELOW , paw_edit.getId());
			LP.addRule(RelativeLayout.ALIGN_LEFT , paw_edit.getId());
			LP.topMargin = WH.getH(8);
			LP.leftMargin = WH.getH(1);
			mCheckView = new CheckView(context);
			mCheckView.setIVStart(R.drawable.uncheck_logo);
			mCheckView.setIVEnd(R.drawable.check_logo);
			mCheckView.setId(getRandomId());
			mCheckView.setLayoutParams(LP);
			loginBackground.addView(mCheckView);
		}
		{
			LayoutParams LP = getLayoutParams(WRAP_CONTENT,  WH.getH(4));
			LP.addRule(RelativeLayout.ALIGN_BOTTOM , mCheckView.getId());
			LP.addRule(RelativeLayout.RIGHT_OF ,mCheckView.getId() );
			LP.leftMargin = WH.getH(1);
			TextView mTextView = new TextView(context);
			mTextView.setText("Remember Me");
			mTextView.setTextSize(PX, WH.getTextSize(28));
			mTextView.setGravity(Gravity.CENTER_VERTICAL);
			mTextView.setTextColor(Color.rgb(113, 113, 113));
			mTextView.setLayoutParams(LP);
			loginBackground.addView(mTextView);
		}
	}

	private void PASSWORD() {
		{
			LayoutParams LP = getLayoutParams(WH.getW(80), WH.getH(7));
			LP.addRule(RelativeLayout.BELOW , user_edit.getId());
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			LP.topMargin = WH.getH(2);
			paw_edit = new EditText(context);
			paw_edit.setHint("Password");
			paw_edit.setHintTextColor(Color.rgb(204, 204, 206));
			paw_edit.setId(getRandomId());
			paw_edit.setLayoutParams(LP);
			loginBackground.addView(paw_edit);
		}
		{
			LayoutParams LP = getLayoutParams(WH.getH(4), WH.getH(4));
			LP.addRule(RelativeLayout.ALIGN_BOTTOM ,paw_edit.getId() );
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT ,paw_edit.getId() );
			LP.bottomMargin = WH.getH(1.5);
			LP.rightMargin = WH.getH(3);
			View view  = new View(context);
			view.setId(getRandomId());
			view.setBackgroundResource(R.drawable.passwrod_128);
			view.setLayoutParams(LP);
			loginBackground.addView(view);
		}
	}

	private void USER() {
		{
			LayoutParams LP = getLayoutParams(WH.getW(80), WH.getH(7));
			LP.addRule(RelativeLayout.BELOW , url_edit.getId());
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			LP.topMargin = WH.getH(2);
			user_edit = new EditText(context);
			user_edit.setHint("User");
			user_edit.setHintTextColor(Color.rgb(204, 204, 206));
			user_edit.setId(getRandomId());
			user_edit.setLayoutParams(LP);
			loginBackground.addView(user_edit);
		}
		{
			LayoutParams LP = getLayoutParams(WH.getH(4), WH.getH(4));
			LP.addRule(RelativeLayout.ALIGN_BOTTOM ,user_edit.getId() );
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT ,user_edit.getId() );
			LP.bottomMargin = WH.getH(1.5);
			LP.rightMargin = WH.getH(3);
			View view  = new View(context);
			view.setId(getRandomId());
			view.setBackgroundResource(R.drawable.user_128);
			view.setLayoutParams(LP);
			loginBackground.addView(view);
		}
	}

	private void URL() {
		{
			LayoutParams LP = getLayoutParams(WH.getW(80), WH.getH(7));
			LP.addRule(RelativeLayout.BELOW , login_text.getId());
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			LP.topMargin = WH.getH(2);
			url_edit = new EditText(context);
			url_edit.setHint("http://localhost:5100");
			url_edit.setHintTextColor(Color.rgb(204, 204, 206));
			url_edit.setId(getRandomId());
			url_edit.setLayoutParams(LP);
			loginBackground.addView(url_edit);
		}
		{
			LayoutParams LP = getLayoutParams(WH.getH(4), WH.getH(4));
			LP.addRule(RelativeLayout.ALIGN_BOTTOM ,url_edit.getId() );
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT ,url_edit.getId() );
			LP.bottomMargin = WH.getH(1.5);
			LP.rightMargin = WH.getH(3);
			View view  = new View(context);
			view.setId(getRandomId());
			view.setBackgroundResource(R.drawable.ipadress2_128);
			view.setLayoutParams(LP);
			loginBackground.addView(view);
		}
	}

	private void Login() {
		{
			LayoutParams LP = getLayoutParams(WH.getW(85), WH.getH(55));
			LP.topMargin = WH.getH(27);
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			loginBackground = new RelativeLayout(context);
			loginBackground.setPadding(WH.getH(2), 0, WH.getH(2), 0);
			loginBackground.setBackgroundColor(Color.WHITE);
			loginBackground.setLayoutParams(LP);
			this.addView(loginBackground);
		}
		{
			LayoutParams LP = getLayoutParams(WH.getH(14), WH.getH(14));
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			LP.topMargin = WH.getH(20);
			icon_view = new View(context);
			icon_view.setId(getRandomId());
			icon_view.setBackgroundResource(R.drawable.login_icon);
			icon_view.setLayoutParams(LP);
			this.addView(icon_view);
		}
		{
			LayoutParams LP = getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
			LP.topMargin = WH.getH(8);
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			login_text = new TextView(context);
			login_text.setText("Sign in to start your session");
			login_text.setTextSize(PX, WH.getTextSize(32));
			login_text.setTextColor(Color.rgb(91, 91, 91));
			login_text.setLayoutParams(LP);
			login_text.setId(getRandomId());
			loginBackground.addView(login_text);
		}
	}

	private void Bar() {
		{
			LayoutParams LP = getLayoutParams(MATCH_PARENT, WH.getH(10));
			actionBar = new RelativeLayout(context);
			actionBar.setBackgroundColor(Color.rgb(170, 37, 38));
			actionBar.setLayoutParams(LP);
			this.addView(actionBar);
		}
		{
			LayoutParams LP = getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
			LP.addRule(RelativeLayout.CENTER_IN_PARENT);
			TextView mTextView = new TextView(context);
			mTextView.setText("Login");
			mTextView.setTextSize(PX, WH.getTextSize(40));
			mTextView.setTextColor(Color.WHITE);
			mTextView.setLayoutParams(LP);
			actionBar.addView(mTextView);
		}
		
	}

	public EditText getUser(){
		return user_edit;
	}
	public EditText getURL(){
		return user_edit;
	}
	public EditText getPassword(){
		return user_edit;
	}
	public CheckView getCheckView(){
		return mCheckView;
	}
	
	public Button getButton(){
		return signin_button;
	}
}
