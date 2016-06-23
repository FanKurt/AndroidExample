package com.cephapp.ItemView;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IMAC.FrameWork.WH;
import com.example.cephapp.R;

public class POOLParameterItemView extends RelativeLayout {
	private WH WH ;
	private RelativeLayout CardView  ,mBar ,relativeLayout1 , relativeLayout2 , relativeLayout3 , relativeLayout4;
	private TextView title , item1Title ,item1Content , item2Title ,item2Content , item3Title ,item3Content , item4Title ,item4Content ;
	private View greenView , lineView1 ,lineView2,lineView3;
	private EditText item1EditText , item2EditText , item3EditText , item4EditText;
	private Button item1Button , item2Button , item3Button , item4Button;
	public POOLParameterItemView(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		AbsListView.LayoutParams LP = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
		this.setLayoutParams(LP);
		CardView();
		item1();
		item2();
		item3();
		item4();
	}
	
	private void item4() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			LP.addRule(RelativeLayout.BELOW ,relativeLayout3.getId());
			relativeLayout4 = new RelativeLayout(getContext());
			relativeLayout4.setBackgroundColor(Color.WHITE);
			relativeLayout4.setId(getRandomId());
			relativeLayout4.setLayoutParams(LP);
			CardView.addView(relativeLayout4);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(1.5), LayoutParams.MATCH_PARENT);
			LP.topMargin = WH.getH(0.5);
			LP.bottomMargin = WH.getH(0.5);
			greenView = new View(getContext());
			greenView.setBackgroundColor(Color.rgb(65, 166, 87));
			greenView.setId(getRandomId());
			greenView.setLayoutParams(LP);
			relativeLayout4.addView(greenView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF ,greenView.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item4Title = new TextView(getContext());
			item4Title.setText("skew");
			item4Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			item4Title.setTextColor(Color.rgb(39, 95, 180));
			item4Title.setId(getRandomId());
			item4Title.setLayoutParams(LP);
			relativeLayout4.addView(item4Title);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(6), WH.getH(6));
			LP.rightMargin = WH.getH(1);
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item4Button = new Button(getContext());
			item4Button.setBackgroundResource(R.drawable.error_128);
			item4Button.setId(getRandomId());
			item4Button.setLayoutParams(LP);
			relativeLayout4.addView(item4Button);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(10), LayoutParams.WRAP_CONTENT);
			LP.rightMargin = WH.getH(1);
			LP.addRule(RelativeLayout.LEFT_OF ,item4Button.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item4EditText = new EditText(getContext());
			item4EditText.setText("128");
			item4EditText.setGravity(Gravity.CENTER);
			item4EditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			item4EditText.setTextColor(Color.rgb(39, 95, 180));
			item4EditText.setId(getRandomId());
			item4EditText.setLayoutParams(LP);
			relativeLayout4.addView(item4EditText);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(0.1));
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			LP.addRule(RelativeLayout.ALIGN_LEFT , item4Title.getId());
			lineView3 = new View(getContext());
			lineView3.setBackgroundColor(Color.GRAY);
			lineView3.setLayoutParams(LP);
			relativeLayout4.addView(lineView3);
		}
	}
	private void item3() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			LP.addRule(RelativeLayout.BELOW ,relativeLayout2.getId());
			relativeLayout3 = new RelativeLayout(getContext());
			relativeLayout3.setBackgroundColor(Color.WHITE);
			relativeLayout3.setId(getRandomId());
			relativeLayout3.setLayoutParams(LP);
			CardView.addView(relativeLayout3);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(1.5), LayoutParams.MATCH_PARENT);
			LP.topMargin = WH.getH(0.5);
			LP.bottomMargin = WH.getH(0.5);
			greenView = new View(getContext());
			greenView.setBackgroundColor(Color.rgb(65, 166, 87));
			greenView.setId(getRandomId());
			greenView.setLayoutParams(LP);
			relativeLayout3.addView(greenView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF ,greenView.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item3Title = new TextView(getContext());
			item3Title.setText("latency");
			item3Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			item3Title.setTextColor(Color.rgb(39, 95, 180));
			item3Title.setId(getRandomId());
			item3Title.setLayoutParams(LP);
			relativeLayout3.addView(item3Title);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(6), WH.getH(6));
			LP.rightMargin = WH.getH(1);
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item3Button = new Button(getContext());
			item3Button.setBackgroundResource(R.drawable.error_128);
			item3Button.setId(getRandomId());
			item3Button.setLayoutParams(LP);
			relativeLayout3.addView(item3Button);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(10), LayoutParams.WRAP_CONTENT);
			LP.rightMargin = WH.getH(1);
			LP.addRule(RelativeLayout.LEFT_OF ,item3Button.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item3EditText = new EditText(getContext());
			item3EditText.setText("128");
			item3EditText.setGravity(Gravity.CENTER);
			item3EditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			item3EditText.setTextColor(Color.rgb(39, 95, 180));
			item3EditText.setId(getRandomId());
			item3EditText.setLayoutParams(LP);
			relativeLayout3.addView(item3EditText);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(0.1));
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			LP.addRule(RelativeLayout.ALIGN_LEFT , item3Title.getId());
			lineView3 = new View(getContext());
			lineView3.setBackgroundColor(Color.GRAY);
			lineView3.setLayoutParams(LP);
			relativeLayout3.addView(lineView3);
		}
	}
	private void item2() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			LP.addRule(RelativeLayout.BELOW ,relativeLayout1.getId());
			relativeLayout2 = new RelativeLayout(getContext());
			relativeLayout2.setBackgroundColor(Color.WHITE);
			relativeLayout2.setId(getRandomId());
			relativeLayout2.setLayoutParams(LP);
			CardView.addView(relativeLayout2);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(1.5), LayoutParams.MATCH_PARENT);
			LP.topMargin = WH.getH(0.5);
			LP.bottomMargin = WH.getH(0.5);
			greenView = new View(getContext());
			greenView.setBackgroundColor(Color.rgb(65, 166, 87));
			greenView.setId(getRandomId());
			greenView.setLayoutParams(LP);
			relativeLayout2.addView(greenView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF ,greenView.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item2Title = new TextView(getContext());
			item2Title.setText("addr");
			item2Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			item2Title.setTextColor(Color.rgb(39, 95, 180));
			item2Title.setId(getRandomId());
			item2Title.setLayoutParams(LP);
			relativeLayout2.addView(item2Title);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(6), WH.getH(6));
			LP.rightMargin = WH.getH(1);
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item2Button = new Button(getContext());
			item2Button.setBackgroundResource(R.drawable.error_128);
			item2Button.setId(getRandomId());
			item2Button.setLayoutParams(LP);
			relativeLayout2.addView(item2Button);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(10), LayoutParams.WRAP_CONTENT);
			LP.rightMargin = WH.getH(1);
			LP.addRule(RelativeLayout.LEFT_OF ,item2Button.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item2EditText = new EditText(getContext());
			item2EditText.setText("128");
			item2EditText.setGravity(Gravity.CENTER);
			item2EditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			item2EditText.setTextColor(Color.rgb(39, 95, 180));
			item2EditText.setId(getRandomId());
			item2EditText.setLayoutParams(LP);
			relativeLayout2.addView(item2EditText);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(0.1));
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			LP.addRule(RelativeLayout.ALIGN_LEFT , item2Title.getId());
			lineView2 = new View(getContext());
			lineView2.setBackgroundColor(Color.GRAY);
			lineView2.setLayoutParams(LP);
			relativeLayout2.addView(lineView2);
		}
	}
	private void item1() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			LP.addRule(RelativeLayout.BELOW ,mBar.getId());
			relativeLayout1 = new RelativeLayout(getContext());
			relativeLayout1.setBackgroundColor(Color.WHITE);
			relativeLayout1.setId(getRandomId());
			relativeLayout1.setLayoutParams(LP);
			CardView.addView(relativeLayout1);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(1.5), LayoutParams.MATCH_PARENT);
			LP.topMargin = WH.getH(0.5);
			LP.bottomMargin = WH.getH(0.5);
			greenView = new View(getContext());
			greenView.setBackgroundColor(Color.rgb(65, 166, 87));
			greenView.setId(getRandomId());
			greenView.setLayoutParams(LP);
			relativeLayout1.addView(greenView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF ,greenView.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item1Title = new TextView(getContext());
			item1Title.setText("id");
			item1Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			item1Title.setTextColor(Color.rgb(39, 95, 180));
			item1Title.setId(getRandomId());
			item1Title.setLayoutParams(LP);
			relativeLayout1.addView(item1Title);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(6), WH.getH(6));
			LP.rightMargin = WH.getH(1);
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item1Button = new Button(getContext());
			item1Button.setBackgroundResource(R.drawable.error_128);
			item1Button.setId(getRandomId());
			item1Button.setLayoutParams(LP);
			relativeLayout1.addView(item1Button);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(10), LayoutParams.WRAP_CONTENT);
			LP.rightMargin = WH.getH(1);
			LP.addRule(RelativeLayout.LEFT_OF ,item1Button.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			item1EditText = new EditText(getContext());
			item1EditText.setText("128");
			item1EditText.setGravity(Gravity.CENTER);
			item1EditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			item1EditText.setTextColor(Color.rgb(39, 95, 180));
			item1EditText.setId(getRandomId());
			item1EditText.setLayoutParams(LP);
			relativeLayout1.addView(item1EditText);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(0.1));
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			LP.addRule(RelativeLayout.ALIGN_LEFT , item1Title.getId());
			lineView1 = new View(getContext());
			lineView1.setBackgroundColor(Color.GRAY);
			lineView1.setLayoutParams(LP);
			relativeLayout1.addView(lineView1);
		}
	}
	private void CardView() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			LP.bottomMargin = WH.getH(2);
			CardView = new RelativeLayout(getContext());
			CardView.setBackgroundColor(Color.WHITE);
			CardView.setId(getRandomId());
			CardView.setLayoutParams(LP);
			this.addView(CardView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(6));
			mBar = new RelativeLayout(getContext());
			mBar.setBackgroundColor(Color.rgb(39, 95, 180));
			mBar.setId(getRandomId());
			mBar.setLayoutParams(LP);
			CardView.addView(mBar);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			title = new TextView(getContext());
			title.setText("ceph-node1");
			title.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(32));
			title.setTextColor(Color.WHITE);
			title.setId(getRandomId());
			title.setLayoutParams(LP);
			mBar.addView(title);
		}
	}
	
	private int getRandomId() {
		return (int) (Math.random() * 1000000);
	}
	
	public TextView getMessageL(){
		return title;
	}
	
	public RelativeLayout getCardView(){
		return CardView;
	}
	
	
}
