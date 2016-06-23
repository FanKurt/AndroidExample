package com.guidercare.Layout;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.guidercare.R;
import com.imac.Framework.MainParentLayout;

public class GuiderCareLayout extends MainParentLayout{
	private TextView mTextView1 ,mTextView2 ,mTextView3,mTextView4;
	private RelativeLayout mRelativeLayout;
	public GuiderCareLayout(Context context) {
		super(context);
	}

	protected void init() {
		this.setBackgroundResource(R.drawable.background);
		
		
//		{
//			LayoutParams LP =getLayoutParams(WRAP_CONTENT, WH.getH(5));
//			mTextView1 = new TextView(context);
//			mTextView1.setText("Null");
//			mTextView1.setId(getRandomId());
//			mTextView1.setLayoutParams(LP);
//			this.addView(mTextView1);
//		}
//		
//		{
//			LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//			LP.addRule(BELOW , mTextView1.getId());
//			mTextView2 = new TextView(context);
//			mTextView2.setText("Null");
//			mTextView2.setId(getRandomId());
//			mTextView2.setLayoutParams(LP);
//			this.addView(mTextView2);
//		}
//		
//		{
//			LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//			LP.addRule(BELOW , mTextView2.getId());
//			mTextView3 = new TextView(context);
//			mTextView3.setText("Null");
//			mTextView3.setId(getRandomId());
//			mTextView3.setLayoutParams(LP);
//			this.addView(mTextView3);
//		}
//		
//		{
//			LayoutParams LP =getLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//			LP.addRule(BELOW , mTextView3.getId());
//			mTextView4 = new TextView(context);
//			mTextView4.setText("Null");
//			mTextView4.setId(getRandomId());
//			mTextView4.setLayoutParams(LP);
//			this.addView(mTextView4);
//		}
		
	}

//	public TextView getTextView1(){
//		return mTextView1;
//	}
//	
//	public TextView getTextView2(){
//		return mTextView2;
//	}
//	
//	public TextView getTextView3(){
//		return mTextView3;
//	}
//	
//	public TextView getTextView4(){
//		return mTextView4;
//	}
	
	
	public RelativeLayout getRelativeLayout(){
		return this;
	}
	
}
