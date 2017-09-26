package com.seoul.juminprogram;

import java.util.ArrayList;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tab_map_tabhost extends ActivityGroup {
	 public static tab_map_tabhost MapTabHGroup;
	 private ArrayList<View> history;
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  history = new ArrayList<View>();
	  MapTabHGroup = this;
	  
	  callmain();
	  
	  
	 }
	 
	
	 
	

	public void callmain() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(tab_map_tabhost.this, tab_map.class);
		  View view = getLocalActivityManager().startActivity("map_tab", intent
		          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		  replaceView(view);
	}

	public void refresh() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(tab_map_tabhost.this, tab_map.class);
		  View view = getLocalActivityManager().startActivity("map_tab", intent
		          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		  replaceView(view);
	}



	// 새로운 Level의 Activity를 추가하는 경우
	 public void replaceView(View view) {
	  history.add(view);
	  setContentView(view);
	 }
	 
	 // Back Key가 눌러졌을 경우에 대한 처리
	 public void back() {
		// setContentView(history.get(history.size()+1));
	  if(history.size() > 0) {
	   history.remove(history.size()-1);
	   if(history.size() ==  0)//{
	    refresh();
	   else
		   refresh();
	  } 
	  else
	  {
	   finish();
	  }
	 }
	 
	
	 
	 // Back Key에 대한 Event Handler
	 @Override
	 public void onBackPressed() {
		 MapTabHGroup.back();
	  return ;
	 }
	}

