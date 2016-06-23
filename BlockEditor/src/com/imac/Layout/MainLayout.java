package com.imac.Layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.blockeditor.R;
import com.imac.Framework.MainParentLayout;
import com.imac.ViewGroup.SingleTouchView;

public class MainLayout extends MainParentLayout  {
	private SingleTouchView singleTouchView;
	private Button buttonADD ;
	private RelativeLayout relativeLayout;
	private Spinner spinner;
	public MainLayout(Context context) {
		super(context);
	}
	protected void init() {
		Spinner();
		Button();
		setRelative();
		SingleTouchView();
		
	}

	
	private void setRelative() {
		LayoutParams LP =getLayoutParams(MATCH_PARENT , MATCH_PARENT);
		LP.addRule(RelativeLayout.ABOVE , buttonADD.getId());
		relativeLayout = new RelativeLayout(context );
		relativeLayout.setLayoutParams(LP);
		this.addView(relativeLayout);                             
	}

	private void SingleTouchView() {
		LayoutParams LP =getLayoutParams(WH.getH(15) , WH.getH(15));
		singleTouchView = new SingleTouchView(context);
		singleTouchView.setImageResource(R.drawable.ic_launcher);
		singleTouchView.setFrameColor(Color.BLUE);
		singleTouchView.setLayoutParams(LP);
		relativeLayout.addView(singleTouchView);
	}


	private void Button() {
		LayoutParams LP =getLayoutParams(MATCH_PARENT, WH.getH(10));
		LP.addRule(RelativeLayout.LEFT_OF,spinner.getId());
		LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		buttonADD = new Button(context);
		buttonADD.setText("ADD");
		buttonADD.setId(getRandomId());
		buttonADD.setLayoutParams(LP);
		this.addView(buttonADD);
	}
	
	
	private void Spinner() {
		LayoutParams LP =getLayoutParams(WH.getH(15) , WH.getH(10));
		LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		spinner = new Spinner(context);
		spinner.setGravity(Gravity.CENTER);
		spinner.setId(getRandomId());
		spinner.setLayoutParams(LP);
		this.addView(spinner);   
	}
	public SingleTouchView getSingleTouchView(){
		return singleTouchView;
	}
	
	public Button getButton_Add(){
		return buttonADD;
	}
	public Spinner getSpinner(){
		return spinner;
	}
	
	public RelativeLayout getRelativeLayout(){
		return relativeLayout;
	}

}
