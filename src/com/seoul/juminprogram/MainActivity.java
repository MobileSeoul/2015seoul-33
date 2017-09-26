package com.seoul.juminprogram;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;



public class MainActivity extends TabActivity {
	
	
	public TabHost tabHost = null;

	   LayoutInflater inflater;
	   View layout1, layout2, layout3, layout4, layout5;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window win = getWindow();
		win.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        tabHost = (TabHost) findViewById(android.R.id.tabhost);

        inflater = getLayoutInflater();
        layout1 = inflater.inflate(R.layout.tab1, null);
        layout2 = inflater.inflate(R.layout.tab2, null);
        layout3 = inflater.inflate(R.layout.tab3, null);
        layout4 = inflater.inflate(R.layout.tab4, null);
        inflater = getLayoutInflater();

        tabHost.addTab(
              tabHost.newTabSpec("tab1").setIndicator(layout1).setContent(new Intent(this, tab_favorite.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(layout2)
              .setContent(new Intent(this, tab_program_tabhost.class)));
        tabHost.addTab(
              tabHost.newTabSpec("tab3").setIndicator(layout3).setContent(new Intent(this, tab_map_tabhost.class)));
        tabHost.addTab(
              tabHost.newTabSpec("tab5").setIndicator(layout4).setContent(new Intent(this, tab_notice.class)));

        tabHost.setCurrentTab(0);
		
		
		}
		
		
	
    }
