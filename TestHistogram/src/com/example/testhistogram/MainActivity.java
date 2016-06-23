package com.example.testhistogram;

import android.os.Bundle;

public class MainActivity extends Activity {
	HistogramLayout HVL;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(HVL =new HistogramLayout(this));
        new HistogramController(this, HVL);
    }

}
