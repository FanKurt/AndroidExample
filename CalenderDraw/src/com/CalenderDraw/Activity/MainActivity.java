package com.CalenderDraw.Activity;

import com.CalenderDraw.R;
import com.CalenderDraw.View.CalenderDrawView;
import com.CalenderDraw.View.CalenderDrawView.CalOnClickListener;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {
	private CalenderDrawView layout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout = new CalenderDrawView(this));
        
        layout.setDayBackgroundStyle1(R.drawable.dayblock_normal);
        
        layout.setCalOnClickListener(new CalOnClickListener() {
			public void CalOnClick(CalenderDrawView CV, int x, int y) {
				layout.setDayBackgroundStyle2(R.drawable.dayblock_invalid);
			}
		});
    }
}
