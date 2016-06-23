package com.cephapp.DialogView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.IMAC.FrameWork.MainParentLayout;
import com.example.cephapp.R;

public class OSDDialogView extends MainParentLayout implements
		OnClickListener {
	public interface OnReportListener {
		public void onClick(OSDDialogView mOSDDialogView, View v);
	}

	private TextView text_title ,severityTextViewy , detailTextView , warnTextView  
					, errorTextView , warn_message , error_message ,closeTextView;
	private View iconView  ,lineView ,lineView2 , lineView3;
	private Button btn_information;
	private RelativeLayout mainParent;
	private OnReportListener mOnReportListener;
	private ListView mListView;
	public OSDDialogView(Context context) {
		super(context);
	}

	protected void init() {
		this.setTag("dismiss");
		this.setOnClickListener(null);
		this.setBackgroundColor(Color.BLACK);
		this.getBackground().setAlpha(180);
		
		setRelativeLayout();
		titleBar();
		LogTitle();
//		WARN();
//		ERROR();
		Close();
		ListView();
	}

	private void Close() {
		{
			LayoutParams LP = getLayoutParams(MATCH_PARENT , WRAP_CONTENT);
			LP.bottomMargin = WH.getH(2);
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			closeTextView = new TextView(context);
			closeTextView.setId(getRandomId());
			closeTextView.setTextSize(PX, WH.getTextSize(40));
			closeTextView.setText("Close");
			closeTextView.setGravity(Gravity.CENTER_HORIZONTAL);
			closeTextView.setLayoutParams(LP);
			closeTextView.setOnClickListener(this);
			closeTextView.setTextColor(Color.BLACK);
			mainParent.addView(closeTextView);
		}
		{
			LayoutParams LP = getLayoutParams(MATCH_PARENT,WH.getH(0.1));
			LP.addRule(RelativeLayout.ABOVE , closeTextView.getId());
			LP.bottomMargin = WH.getH(2);
			lineView3 = new View(context);
			lineView3.setId(getRandomId());
			lineView3.setBackgroundColor(Color.GRAY);
			lineView3.setLayoutParams(LP);
			mainParent.addView(lineView3);
		}
		
	}
	
	private void ListView() {
			
		LayoutParams LP =new LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT );
		LP.topMargin = WH.getH(2);
		LP.addRule(RelativeLayout.ABOVE , lineView3.getId());
		LP.addRule(RelativeLayout.BELOW , lineView2.getId());
		mListView  = new ListView(getContext());
		mListView.setPadding(WH.getH(2), WH.getH(2), WH.getH(2), WH.getH(2));
		mListView.setVerticalScrollBarEnabled(false);
		mListView.setBackgroundColor(Color.rgb(200, 204, 215));
		mListView.setLayoutParams(LP);
		mainParent.addView(mListView);
		
	}

	private void ERROR() {
		{
			LayoutParams LP = getLayoutParams(WH.getW(WRAP_CONTENT),
					WH.getH(WRAP_CONTENT));
			LP.topMargin = WH.getH(3);
			LP.addRule(RelativeLayout.BELOW , warnTextView.getId());
			LP.addRule(RelativeLayout.ALIGN_LEFT ,warnTextView.getId());
			errorTextView = new TextView(context);
			errorTextView.setId(getRandomId());
			errorTextView.setTextSize(PX, WH.getTextSize(40));
			errorTextView.setText("ERROR");
			errorTextView.setLayoutParams(LP);
			errorTextView.setTextColor(Color.rgb(206, 44, 44));
			mainParent.addView(errorTextView);
		}
		{
			LayoutParams LP = getLayoutParams(WH.getW(WRAP_CONTENT),
					WH.getH(WRAP_CONTENT));
			LP.addRule(RelativeLayout.RIGHT_OF , errorTextView.getId());
			LP.addRule(RelativeLayout.ALIGN_LEFT ,detailTextView.getId());
			LP.addRule(RelativeLayout.ALIGN_TOP ,errorTextView.getId());
			error_message = new TextView(context);
			error_message.setId(getRandomId());
			error_message.setTextSize(PX, WH.getTextSize(36));
			error_message.setText("noout flag(s) set");
			error_message.setLayoutParams(LP);
			error_message.setTextColor(Color.rgb(46, 46, 46));
			mainParent.addView(error_message);
		}
	}

	private void WARN() {
		{
			LayoutParams LP = getLayoutParams(WH.getW(WRAP_CONTENT),
					WH.getH(WRAP_CONTENT));
			LP.addRule(RelativeLayout.BELOW , lineView2.getId());
			LP.leftMargin = WH.getH(2);
			LP.topMargin = WH.getH(2);
			warnTextView = new TextView(context);
			warnTextView.setId(getRandomId());
			warnTextView.setTextSize(PX, WH.getTextSize(40));
			warnTextView.setText("WARN");
			warnTextView.setLayoutParams(LP);
			warnTextView.setTextColor(Color.rgb(244, 211, 132));
			mainParent.addView(warnTextView);
		}
		{
			LayoutParams LP = getLayoutParams(WH.getW(WRAP_CONTENT),
					WH.getH(WRAP_CONTENT));
			LP.addRule(RelativeLayout.RIGHT_OF , warnTextView.getId());
			LP.addRule(RelativeLayout.ALIGN_LEFT ,detailTextView.getId());
			LP.addRule(RelativeLayout.ALIGN_TOP ,warnTextView.getId());
			warn_message = new TextView(context);
			warn_message.setId(getRandomId());
			warn_message.setTextSize(PX, WH.getTextSize(36));
			warn_message.setText("noout flag(s) set");
			warn_message.setLayoutParams(LP);
			warn_message.setTextColor(Color.rgb(46, 46, 46));
			mainParent.addView(warn_message);
		}
	}

	private void LogTitle() {
		{
			LayoutParams LP = getLayoutParams(WH.getW(WRAP_CONTENT),
					WH.getH(WRAP_CONTENT));
			LP.addRule(RelativeLayout.BELOW , lineView.getId());
			LP.leftMargin = WH.getH(2);
			LP.topMargin = WH.getH(2);
			severityTextViewy = new TextView(context);
			severityTextViewy.setId(getRandomId());
			severityTextViewy.setTextSize(PX, WH.getTextSize(40));
			severityTextViewy.setText("KEY");
			severityTextViewy.setLayoutParams(LP);
			severityTextViewy.setTextColor(Color.rgb(11, 11, 11));
			mainParent.addView(severityTextViewy);
		}
		
		{
			LayoutParams LP = getLayoutParams(WH.getW(WRAP_CONTENT),
					WH.getH(WRAP_CONTENT));
			LP.addRule(RelativeLayout.BELOW , lineView.getId());
			LP.addRule(RelativeLayout.RIGHT_OF , severityTextViewy.getId());
			LP.leftMargin = WH.getH(10);
			LP.topMargin = WH.getH(2);
			detailTextView = new TextView(context);
			detailTextView.setId(getRandomId());
			detailTextView.setTextSize(PX, WH.getTextSize(40));
			detailTextView.setText("VALUES");
			detailTextView.setLayoutParams(LP);
			detailTextView.setTextColor(Color.rgb(11, 11, 11));
			mainParent.addView(detailTextView);
		}
		{
			LayoutParams LP = getLayoutParams(MATCH_PARENT,WH.getH(0.1));
			LP.addRule(RelativeLayout.BELOW , severityTextViewy.getId());
			LP.addRule(RelativeLayout.ALIGN_LEFT , severityTextViewy.getId());
			LP.topMargin = WH.getH(2);
			lineView2 = new View(context);
			lineView2.setId(getRandomId());
			lineView2.setBackgroundColor(Color.GRAY);
			lineView2.setLayoutParams(LP);
			mainParent.addView(lineView2);
		}
	}

	private void setRelativeLayout() {
		LayoutParams LP = new LayoutParams(WH.getW(80), WH.getH(50));
		LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
		LP.addRule(RelativeLayout.CENTER_VERTICAL);
		mainParent = new RelativeLayout(context);
		mainParent.setBackgroundColor(Color.WHITE);
		mainParent.setLayoutParams(LP);
		
		this.addView(mainParent);
	}

	private void titleBar() {
		{
			LayoutParams LP = getLayoutParams(WH.getH(4),WH.getH(4));
			LP.leftMargin = WH.getH(2);
			LP.topMargin = WH.getH(2);
			iconView = new View(context);
			iconView.setId(getRandomId());
			iconView.setBackgroundResource(R.drawable.osd2_128);
			iconView.setLayoutParams(LP);
			mainParent.addView(iconView);
		}
		{
			LayoutParams LP = getLayoutParams(WH.getW(WRAP_CONTENT),
					WH.getH(WRAP_CONTENT));
			LP.addRule(RelativeLayout.RIGHT_OF , iconView.getId());
			LP.addRule(RelativeLayout.ALIGN_BOTTOM , iconView.getId());
			LP.leftMargin = WH.getH(2);
			text_title = new TextView(context);
			text_title.setId(getRandomId());
			text_title.setTextSize(PX, WH.getTextSize(44));
			text_title.setText("SEVERITY");
			text_title.setLayoutParams(LP);
			text_title.setTextColor(Color.rgb(66, 139, 202));
			mainParent.addView(text_title);
		}
		
		
		{
			LayoutParams LP = getLayoutParams(MATCH_PARENT,WH.getH(0.1));
			LP.addRule(RelativeLayout.BELOW , iconView.getId());
			LP.topMargin = WH.getH(2);
			lineView = new View(context);
			lineView.setId(getRandomId());
			lineView.setBackgroundColor(Color.GRAY);
			lineView.setLayoutParams(LP);
			mainParent.addView(lineView);
		}
	
	}


	public OSDDialogView setOnReportListener(OnReportListener mReportListener) {
		this.mOnReportListener = mReportListener;
		return this;
	}

	public OSDDialogView Show() {
		ViewGroup v = (ViewGroup) ((Activity) context).getWindow()
				.getDecorView().getRootView();
		v.addView(this);
		return this;
	}

	public void Dismiss() {
		ViewGroup v = (ViewGroup) ((Activity) context).getWindow()
				.getDecorView().getRootView();
		v.removeView(this);
		System.gc();
	}

	public void onClick(View v) {
		if (mOnReportListener != null)
			mOnReportListener.onClick(this, v);
	}
	
	public TextView getCloseTextView(){
		return closeTextView;
	}

}
