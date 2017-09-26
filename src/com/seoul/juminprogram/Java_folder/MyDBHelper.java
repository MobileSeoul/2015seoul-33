package com.seoul.juminprogram.Java_folder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
	private static final String DBName = "lectureDB.db";
	private static final int DBVersion = 1;

	public MyDBHelper(Context context) {
		super(context, DBName, null, DBVersion);
	}

	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE myLecture (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "dong TEXT,name TEXT, time TEXT, url TEXT);");

	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//
		// db.execSQL("DROP TABLE IF EXISTS myLecture");
		// onCreate(db);

	}

}