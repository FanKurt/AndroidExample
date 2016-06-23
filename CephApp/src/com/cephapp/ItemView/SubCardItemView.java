package com.cephapp.ItemView;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IMAC.FrameWork.WH;
import com.example.cephapp.R;

public class SubCardItemView extends RelativeLayout {
	private WH WH ;
	private RelativeLayout CardView1 ,CardView2;
	private TextView message1 , introduce1 , state1 ,message2 , introduce2 , state2  ;
	private View state_view1 , check_view1 ,state_view2 , check_view2;
	public SubCardItemView(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		AbsListView.LayoutParams LP = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
		this.setLayoutParams(LP);
		CardView1();
		CardView2();
	}
	
	private void CardView2() {
		{
			LayoutParams LP = new LayoutParams(WH.getH(25), WH.getH(20));
			LP.addRule(RelativeLayout.RIGHT_OF ,CardView1.getId() );
			LP.topMargin = WH.getH(2);
			LP.leftMargin =  WH.getH(2);
			CardView2 = new RelativeLayout(getContext());
			CardView2.setBackgroundColor(Color.WHITE);
			CardView2.setId(getRandomId());
			CardView2.setLayoutParams(LP);
			this.addView(CardView2);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.CENTER_IN_PARENT);
			message2 = new TextView(getContext());
			message2.setText("62/62");
			message2.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(40));
			message2.setTextColor(Color.BLACK);
			message2.setId(getRandomId());
			message2.setLayoutParams(LP);
			CardView2.addView(message2);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.topMargin = WH.getH(2);
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			LP.addRule(RelativeLayout.BELOW , message2.getId());
			introduce2 = new TextView(getContext());
			introduce2.setText("In & Up");
			introduce2.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			introduce2.setTextColor(Color.BLACK);
			introduce2.setLayoutParams(LP);
			CardView2.addView(introduce2);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(3), WH.getH(3));
			LP.leftMargin =WH.getH(1);
			LP.topMargin =WH.getH(1);
			state_view2 = new View(getContext());
			state_view2.setBackgroundResource(R.drawable.osd2_128);
			state_view2.setId(getRandomId());
			state_view2.setLayoutParams(LP);
			CardView2.addView(state_view2);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.RIGHT_OF , state_view2.getId());
			LP.addRule(RelativeLayout.ALIGN_BOTTOM , state_view2.getId());
			LP.leftMargin = WH.getH(1);
			state2 = new TextView(getContext());
			state2.setText("OSD");
			state2.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(32));
			state2.setTextColor(Color.BLACK);
			state2.setGravity(Gravity.BOTTOM);
			state2.setId(getRandomId());
			state2.setLayoutParams(LP);
			CardView2.addView(state2);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(3), WH.getH(3));
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			LP.rightMargin =WH.getH(1);
			LP.topMargin =WH.getH(1);
			check_view2 = new View(getContext());
			check_view2.setBackgroundResource(R.drawable.ok_128);
			check_view2.setId(getRandomId());
			check_view2.setLayoutParams(LP);
			CardView2.addView(check_view2);
		}
	}
	private void CardView1() {
		{
			LayoutParams LP = new LayoutParams(WH.getH(25), WH.getH(20));
			LP.topMargin = WH.getH(2);
			CardView1 = new RelativeLayout(getContext());
			CardView1.setBackgroundColor(Color.WHITE);
			CardView1.setId(getRandomId());
			CardView1.setLayoutParams(LP);
			this.addView(CardView1);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.CENTER_IN_PARENT);
			message1 = new TextView(getContext());
			message1.setText("62/62");
			message1.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(40));
			message1.setTextColor(Color.BLACK);
			message1.setId(getRandomId());
			message1.setLayoutParams(LP);
			CardView1.addView(message1);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.topMargin = WH.getH(2);
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			LP.addRule(RelativeLayout.BELOW , message1.getId());
			introduce1 = new TextView(getContext());
			introduce1.setText("In & Up");
			introduce1.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			introduce1.setTextColor(Color.BLACK);
			introduce1.setLayoutParams(LP);
			CardView1.addView(introduce1);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(3), WH.getH(3));
			LP.leftMargin =WH.getH(1);
			LP.topMargin =WH.getH(1);
			state_view1 = new View(getContext());
			state_view1.setBackgroundResource(R.drawable.osd2_128);
			state_view1.setId(getRandomId());
			state_view1.setLayoutParams(LP);
			CardView1.addView(state_view1);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.RIGHT_OF , state_view1.getId());
			LP.addRule(RelativeLayout.ALIGN_BOTTOM , state_view1.getId());
			LP.leftMargin = WH.getH(1);
			state1 = new TextView(getContext());
			state1.setText("OSD");
			state1.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(32));
			state1.setTextColor(Color.BLACK);
			state1.setGravity(Gravity.BOTTOM);
			state1.setId(getRandomId());
			state1.setLayoutParams(LP);
			CardView1.addView(state1);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(3), WH.getH(3));
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			LP.rightMargin =WH.getH(1);
			LP.topMargin =WH.getH(1);
			check_view1 = new View(getContext());
			check_view1.setBackgroundResource(R.drawable.ok_128);
			check_view1.setId(getRandomId());
			check_view1.setLayoutParams(LP);
			CardView1.addView(check_view1);
		}
	}
	
	private int getRandomId() {
		return (int) (Math.random() * 1000000);
	}
	
	public View getStateViewL(){
		return state_view1;
	}
	public View getStateViewR(){
		return state_view2;
	}
	
	public View getCheckL(){
		return check_view1;
	}
	public View getCheckR(){
		return check_view2;
	}
	
	public TextView getIntroduceL(){
		return introduce1;
	}
	public TextView getIntroduceR(){
		return introduce2;
	}
	public TextView getStateL(){
		return state1;
	}
	public TextView getStateR(){
		return state2;
	}
	public TextView getMessageL(){
		return message1;
	}
	public TextView getMessageR(){
		return message2;
	}
	
	public RelativeLayout getCardView1(){
		return CardView1;
	}
	
	public RelativeLayout getCardView2(){
		return CardView2;
	}
}
