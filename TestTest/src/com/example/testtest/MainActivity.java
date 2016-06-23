package com.example.testtest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btn1, btn2, btn3;
	private String path = "/mnt/sdcard/Txt1029";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					UpdateText("1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					UpdateText("2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btn3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					UpdateText("3");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		});
		
		createMkdir();
	}

	private void createMkdir() {
		try {
			File root = new File(path);
			if (!root.exists()) {
				root.mkdirs();
			}
			File gpxfile = new File(root, "SceneNum.txt");
			FileWriter writer = new FileWriter(gpxfile);
			writer.append("1234");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void UpdateText(String data) throws IOException{
		File file = new File(path+"/SceneNum.txt");
		file.delete();
		File root = new File(path);
		if (!root.exists()) {
			root.mkdirs();
		}
		File gpxfile = new File(root, "SceneNum.txt");
		FileWriter writer = new FileWriter(gpxfile);
		writer.append(data);
		writer.flush();
		writer.close();
	}
}