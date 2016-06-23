package com.example.testsurfaceview;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
public class MainActivity extends Activity {
	MySurfaceLayout MSL;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(MSL=new MySurfaceLayout(this));
        MSL.getButton().setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				MSL.getMSV().startHandler();
				MSL.getMSV().sweepAngle=0;
				MSL.getMSV().i =0;
			}
		});
       
    }
}