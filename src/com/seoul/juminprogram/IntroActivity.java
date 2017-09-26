package com.seoul.juminprogram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



public class IntroActivity extends Activity {

	Handler handler; //핸들러 선언
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		new Thread(new Runnable() {
			public void run() {
			// TODO Auto-generated method stub
			try {
			Thread.sleep(2000);
			} catch (Throwable ex) {
			ex.printStackTrace();
			}
			Intent i = new Intent(IntroActivity.this, MainActivity.class);
			startActivity(i);
			finish();
			}
			}).start();
			}
			}
