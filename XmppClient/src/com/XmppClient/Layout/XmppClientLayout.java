package com.XmppClient.Layout;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.imac.Framework.MainParentLayout;

public class XmppClientLayout extends MainParentLayout{
	private Button send , setting ;
	private EditText edit;
	private ListView list;
	public XmppClientLayout(Context context) {
		super(context);
	}

	protected void init() {
		ButtonSend();
		EditText();
		ButtonSetting();
		ListView();
	}

	private void ButtonSetting() {
		LayoutParams LP =getLayoutParams(MATCH_PARENT, WH.getH(10));
		setting = new Button(context);
		setting.setText("Setting");
		setting.setId(getRandomId());
		setting.setLayoutParams(LP);
		this.addView(setting);
	}

	private void ButtonSend() {
		LayoutParams LP =getLayoutParams(MATCH_PARENT, WH.getH(10));
		LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		send = new Button(context);
		send.setText("Send");
		send.setId(getRandomId());
		send.setLayoutParams(LP);
		this.addView(send);
	}

	private void EditText() {
		LayoutParams LP =getLayoutParams(MATCH_PARENT, WH.getH(10));
		LP.addRule(RelativeLayout.ABOVE ,send.getId());
		edit = new EditText(context);
		edit.setHint("Message");
		edit.setHintTextColor(Color.BLACK);
		edit.setTextColor(Color.BLACK);
		edit.setId(getRandomId());
		edit.setLayoutParams(LP);
		this.addView(edit);
		
	}

	private void ListView() {
		LayoutParams LP =getLayoutParams(MATCH_PARENT, MATCH_PARENT);
		LP.addRule(RelativeLayout.ABOVE ,edit.getId());
		LP.addRule(RelativeLayout.BELOW ,setting.getId());
		list = new ListView(context);
		list.setId(getRandomId());
		list.setLayoutParams(LP);
		this.addView(list);
	}

	public ListView getListView (){
		return list;
	}
	
	public Button getButtonSend(){
		return send;
	}
	public Button getButtonSetting(){
		return setting;
	}
	
	
	public EditText getEdit(){
		return edit;
	}
}
