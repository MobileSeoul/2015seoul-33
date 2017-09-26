package com.seoul.juminprogram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.seoul.juminprogram.Java_folder.MyDBHelper;
import com.seoul.juminprogram.Java_folder.SwipeAdapter;
import com.seoul.juminprogram.Java_folder.SwipeListView;


public class tab_favorite extends Activity {
	private SwipeListView mListView;
	SQLiteDatabase db;
	MyDBHelper helper;
	List<HashMap<String, String>> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_favorite);

		callBookmark();
	}

	private void initView() {
		mListView = (SwipeListView) findViewById(R.id.listview);
		SwipeAdapter adapter = new SwipeAdapter(this,
				dataList, mListView.getRightViewWidth(),
				new SwipeAdapter.IOnItemRightClickListener() {
					@Override
					public void onRightClick(View v, int position) {
						// TODO Auto-generated method stub

						HashMap<String, String> data = new HashMap<String, String>();
						data = dataList.get(position);

						deleteSQL(data.get("index"));

					}
				});
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				HashMap<String, String> data = new HashMap<String, String>();
				data = dataList.get(position);

				Uri uri = Uri.parse(data.get("url"));

				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}
		});
	}
	
	

	public void deleteSQL(String index) {
		db.delete("myLecture", "_id='" + index + "'", null);
		callBookmark();
	}
	
	

	public void callBookmark() {
		helper = new MyDBHelper(this);
		db = helper.getReadableDatabase();

		Cursor cur = db.rawQuery("SELECT* FROM myLecture", null);
		dataList = new ArrayList<HashMap<String, String>>();

		while (cur.moveToNext()) {

			HashMap<String, String> data = new HashMap<String, String>();

			data.put("index", cur.getString(0).toString());
			data.put("dong", cur.getString(1).toString());
			data.put("name", cur.getString(2).toString());
			data.put("time", cur.getString(3).toString());
			data.put("url", cur.getString(4).toString());

			dataList.add(data);

		}

		initView();
	}
}
