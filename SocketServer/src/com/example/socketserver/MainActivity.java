package com.example.socketserver;

import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private boolean OutServer = false;
	private ServerSocket server;
	private final int ServerPort = 8765;// �n�ʱ���port
	private Handler handler;
	TextView tx;
	Button btn;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		tx = (TextView) findViewById(R.id.message);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			    tx.setText("���A���w�Ұ� !");
				SocketServer();
				handler = new Handler();
				handler.post(runnable);
			}
		});
	
	}

	private void SocketServer() {
		try {
			server = new ServerSocket(ServerPort);

		} catch (java.io.IOException e) {
			Log.e("Socket�Ұʦ����D !","Socket�Ұʦ����D !");
			Log.e("IOException :" , e.toString());
		}
	}
	
	private Runnable runnable = new Runnable() {
		public void run() {
	        Socket socket;
	        java.io.BufferedInputStream in;
	    
	        Log.e("���A���w�Ұ� !","���A���w�Ұ� !");
	        while (!OutServer) {
	            socket = null;
	            try {
	                synchronized (server) {
	                    socket = server.accept();
	                }
	                Log.e("���o�s�u : InetAddress = ", ""+socket.getInetAddress());
	                // TimeOut�ɶ�
	                socket.setSoTimeout(15000);
	 
	                in = new java.io.BufferedInputStream(socket.getInputStream());
	                byte[] b = new byte[1024];
	                String data = "";
	                int length;
	                while ((length = in.read(b)) > 0)// <=0���ܴN�O�����F
	                {
	                    data += new String(b, 0, length);
	                }
	         
	                Log.e("�ڨ��o����:" ,"" +data);
	                in.close();
	                in = null;
	                socket.close();
	                OutServer = true;
	                
	                tx.setText("�ڨ��o����:"+data);
	            } catch (java.io.IOException e) {
	                Log.e("Socket�s�u�����D !","Socket�s�u�����D !");
	                Log.e("IOException :", e.toString());
	            }
	 
	        }
	        
	        
	    }
	};
	protected void onDestroy() {
		if(handler!=null){
			handler.removeCallbacks(null);
			handler = null;
		}
	
		super.onDestroy();
	}
	
	
	
}
