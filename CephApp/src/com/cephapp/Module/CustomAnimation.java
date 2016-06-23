package com.cephapp.Module;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
public class CustomAnimation {
	private Animation myAnimation_Alpha;
	private Animation myAnimation_Scale;
	private Animation myAnimation_Translate;
	private Animation myAnimation_Rotate;

	public CustomAnimation(Context context) {
	}
	public void setAnimationAlpha(float from, float to,
			int duration, int repeatCount, boolean fillAfter, int startOffset) {
		if(myAnimation_Alpha!=null){
			myAnimation_Alpha =null;
		}
		myAnimation_Alpha = new AlphaAnimation(from,to);
		myAnimation_Alpha.setDuration(duration);
		myAnimation_Alpha.setRepeatCount(repeatCount);
		myAnimation_Alpha.setFillAfter(fillAfter);
		myAnimation_Alpha.setStartOffset(startOffset);
	}
	public void setAnimationScale(float fromX, float toX, float fromY, float toY,
			int duration, int repeatCount, boolean fillAfter, int startOffset) {
		if(myAnimation_Scale!=null){
			myAnimation_Scale =null;
		}
		myAnimation_Scale = new ScaleAnimation(fromX, toX, fromY, toY,
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				0f);
		myAnimation_Scale.setDuration(duration);
		myAnimation_Scale.setRepeatCount(repeatCount);
		myAnimation_Scale.setFillAfter(fillAfter);
		myAnimation_Scale.setStartOffset(startOffset);
	}
	
	public void setAnimationTranslate(float fromX, float toX, float fromY, float toY,
			int duration, int repeatCount, boolean fillAfter, int startOffset) {
		if(myAnimation_Translate!=null){
			myAnimation_Translate =null;
		}
		myAnimation_Translate = new TranslateAnimation(fromX, toX, fromY,toY);
		myAnimation_Translate.setDuration(duration);
		myAnimation_Translate.setRepeatCount(repeatCount);
		myAnimation_Translate.setFillAfter(fillAfter);
		myAnimation_Translate.setStartOffset(startOffset);
	}
	
	public void setAnimationRotate(float fromDegrees, float toDegrees,
			int duration, int repeatCount, boolean fillAfter, int startOffset) {
		if(myAnimation_Rotate!=null){
			myAnimation_Rotate =null;
		}
		myAnimation_Rotate = new RotateAnimation(fromDegrees, toDegrees,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		myAnimation_Rotate.setDuration(duration);
		myAnimation_Rotate.setRepeatCount(repeatCount);
		myAnimation_Rotate.setFillAfter(fillAfter);
		myAnimation_Rotate.setStartOffset(startOffset);
	}
	public Animation getAlpha(){
		return myAnimation_Alpha;
	}
	public Animation getRotate(){
		return myAnimation_Rotate;
	}
	public Animation getTranslate(){
		return myAnimation_Translate;
	}
	public Animation getScale(){
		return myAnimation_Scale;
	}
}
