package com.cephapp.View;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class CheckView extends View{
	private boolean flag = false;
	private int start ,end;
	public CheckView(Context context) {
		super(context);
		this.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_UP){
					setcheck(!flag);
				}
				return true;
			}
		});
	}
	public void setIVStart(int start){
		this.start = start;
		setcheck(flag);
	}
	public void setIVEnd(int end){
		this.end = end;
	}
	
	public void setcheck(boolean check){
		this.setBackgroundResource((this.flag = check)?end:start);
	}
	
	public boolean isChecked(){
		return flag;
	}
}
