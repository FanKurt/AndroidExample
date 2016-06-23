package com.XmppClient.Item;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.imac.Framework.MainParentLayout;

public class MessageItem extends MainParentLayout{
	private TextView textMessage;
	public MessageItem(Context context) {
		super(context);
	}

	protected void init() {
		LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
		textMessage = new TextView(context);
		textMessage.setTextColor(Color.RED);
		textMessage.setLayoutParams(LP);
		this.addView(textMessage);
	}
	public TextView getTextView(){
		return textMessage;
	}
}
