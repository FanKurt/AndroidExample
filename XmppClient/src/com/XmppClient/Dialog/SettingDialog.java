package com.XmppClient.Dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.XmppClient.Module.XmppClientTag;
import com.imac.Framework.MainParentLayout;

public class SettingDialog  extends MainParentLayout  {
	private RelativeLayout relative ;
	private TextView  title, userText , passText , portText , serviceText , hostText;
	private EditText userEdit , passEdit ,portEdit , serviceEdit , hostEdit;
	private Button okButton;
	public SettingDialog(Context context) {
		super(context);
	}

	protected void init() {
		setParent();
		title();
		host();
		service();
		port();
		user();
		password();
		Button();
		
		relative.setBackgroundColor(Color.BLACK);
	}

	private void Button() {
		LayoutParams LP =getLayoutParams(MATCH_PARENT, WRAP_CONTENT);
		LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		okButton = new Button(context);
		okButton.setText("OK");
		okButton.setId(getRandomId());
		okButton.setLayoutParams(LP);
		relative.addView(okButton);
	}

	private void password() {
		{
			LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
			LP.topMargin = WH.getH(3);
			LP.addRule(RelativeLayout.BELOW , userEdit.getId());
			passText = new TextView(context);
			passText.setText("password");
			passText.setId(getRandomId());
			passText.setLayoutParams(LP);
			relative.addView(passText);
		}
		
		{
			LayoutParams LP =getLayoutParams(WH.getW(60), WH.getH(8));
			LP.addRule(RelativeLayout.BELOW , userEdit.getId());
			LP.addRule(RelativeLayout.RIGHT_OF , passText.getId());
			passEdit = new EditText(context);
			passEdit.setId(getRandomId());
			passEdit.setText(XmppClientTag.PASSWORD);
			passEdit.setLayoutParams(LP);
			relative.addView(passEdit);
		}
		
	}

	private void user() {
		{
			LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
			LP.topMargin = WH.getH(3);
			LP.addRule(RelativeLayout.BELOW , portEdit.getId());
			userText = new TextView(context);
			userText.setText("user");
			userText.setId(getRandomId());
			userText.setLayoutParams(LP);
			relative.addView(userText);
		}
		
		{
			LayoutParams LP =getLayoutParams(WH.getW(60), WH.getH(8));
			LP.addRule(RelativeLayout.BELOW , portEdit.getId());
			LP.addRule(RelativeLayout.RIGHT_OF , userText.getId());
			userEdit = new EditText(context);
			userEdit.setId(getRandomId());
			userEdit.setText(XmppClientTag.USER);
			userEdit.setLayoutParams(LP);
			relative.addView(userEdit);
		}
	}

	private void port() {
		{
			LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
			LP.topMargin = WH.getH(3);
			LP.addRule(RelativeLayout.BELOW , serviceEdit.getId());
			portText = new TextView(context);
			portText.setText("port");
			portText.setId(getRandomId());
			portText.setLayoutParams(LP);
			relative.addView(portText);
		}
		
		{
			LayoutParams LP =getLayoutParams(WH.getW(60), WH.getH(8));
			LP.addRule(RelativeLayout.BELOW , serviceEdit.getId());
			LP.addRule(RelativeLayout.RIGHT_OF , portText.getId());
			portEdit = new EditText(context);
			portEdit.setId(getRandomId());
			portEdit.setText(XmppClientTag.PORT);
			portEdit.setLayoutParams(LP);
			relative.addView(portEdit);
		}
	}

	private void service() {
		{
			LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
			LP.topMargin = WH.getH(3);
			LP.addRule(RelativeLayout.BELOW , hostEdit.getId());
			serviceText = new TextView(context);
			serviceText.setText("service");
			serviceText.setId(getRandomId());
			serviceText.setLayoutParams(LP);
			relative.addView(serviceText);
		}
		
		{
			LayoutParams LP =getLayoutParams(WH.getW(60), WH.getH(8));
			LP.addRule(RelativeLayout.BELOW , hostEdit.getId());
			LP.addRule(RelativeLayout.RIGHT_OF , serviceText.getId());
			serviceEdit = new EditText(context);
			serviceEdit.setId(getRandomId());
			serviceEdit.setText(XmppClientTag.SERVICE);
			serviceEdit.setLayoutParams(LP);
			relative.addView(serviceEdit);
		}
		
	}
	
	private void host() {
		{
			LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
			LP.topMargin = WH.getH(3);
			LP.addRule(RelativeLayout.BELOW , title.getId());
			hostText = new TextView(context);
			hostText.setText("host");
			hostText.setId(getRandomId());
			hostText.setLayoutParams(LP);
			relative.addView(hostText);
		}
		
		{
			LayoutParams LP =getLayoutParams(WH.getW(60), WH.getH(8));
			LP.addRule(RelativeLayout.BELOW , title.getId());
			LP.addRule(RelativeLayout.RIGHT_OF , hostText.getId());
			hostEdit = new EditText(context);
			hostEdit.setId(getRandomId());
			hostEdit.setText(XmppClientTag.HOST);
			hostEdit.setLayoutParams(LP);
			relative.addView(hostEdit);
		}
	}

	private void title() {
		LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
		LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
		title = new TextView(context);
		title.setText("XMPP Settings");
		title.setId(getRandomId());
		title.setLayoutParams(LP);
		relative.addView(title);
	}



	private void setParent() {
		LayoutParams LP =getLayoutParams(WH.getW(80), WH.getH(80));
		LP.addRule(RelativeLayout.CENTER_IN_PARENT);
		relative = new RelativeLayout(context);
		relative.setLayoutParams(LP);
		this.addView(relative);
	}


	
	public EditText getUser(){
		return userEdit;
	}
	public EditText getPass(){
		return passEdit;
	}
	public EditText getPort(){
		return portEdit;
	}
	public EditText getService(){
		return serviceEdit;
	}
	public EditText getHost(){
		return hostEdit;
	}
	public Button getButton(){
		return okButton;
	}
	
	public SettingDialog Show() {
		ViewGroup v = (ViewGroup) ((Activity) context).getWindow()
				.getDecorView().getRootView();
		v.addView(this);
		return this;
	}

	public void Dismiss() {
		ViewGroup v = (ViewGroup) ((Activity) context).getWindow()
				.getDecorView().getRootView();
		v.removeView(this);
	}
}
