package com.example.Layout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.IMAC.MainParentLayout;
import com.example.View.BitmapView;
import com.example.Activity.R;

public class TopLayerLayout extends MainParentLayout {
	BitmapView leftdrawer, home;
	TextView title;
	static String TitleName;
	private LinearLayout LIN;

	public TopLayerLayout(Context context) {
		super(context);
	}

	protected void init() {
		
		LinearLayout.LayoutParams LPP = new LinearLayout.LayoutParams(
				MATCH_PARENT, MATCH_PARENT);
		LIN = new LinearLayout(context);
		LIN.setPadding(WH.getH(1), 0, WH.getH(1), 0);
		LIN.setOrientation(LinearLayout.HORIZONTAL);
		LIN.setLayoutParams(LPP);
		this.addView(LIN);
		LeftDrawer();
		Title();
	}

	private void LeftDrawer() {
		LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(MATCH_PARENT, WH.getH(7.5));
		LP.weight = (float) 1.5;
		LP.gravity = Gravity.CENTER_VERTICAL;
		leftdrawer = new BitmapView(context);
		leftdrawer.setId(getRandomId());
		leftdrawer.setBitmap(R.drawable.exit);
		leftdrawer.setLayoutParams(LP);
		LIN.addView(leftdrawer);
	}

	@Override
	public Drawable getBackground() {
		return super.getBackground();
	}

	private void Title() {
		LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
		LP.weight = (float) 0.4;
		LP.gravity = Gravity.CENTER;
		title = new TextView(context);
		title.setTextSize(PX, WH.getTextSize(55));
		title.setTextColor(Color.WHITE);
		title.setText(TitleName);
		title.setGravity(Gravity.CENTER);
		title.setTypeface(WH.getTypeface());
		title.setLayoutParams(LP);
		LIN.addView(title);
	}

	public BitmapView getHome() {
		return home;
	}

	public BitmapView getDrawer() {
		return leftdrawer;
	}

	public TextView getTitle() {
		return title;
	}

	public static void SetTitle(String value) {
		TitleName = value;
	}

}
