package com.seoul.juminprogram;

import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.seoul.juminprogram.Java_folder.MyDBHelper;


public class tab_program_click extends Activity {

	SQLiteDatabase db;
	MyDBHelper helper;

	int valueGu, valueDong;
	String clickUrl, aCode;
	String dong, name, period = "", time = "", place = "", price = "", phone = "", intro = "";

	TextView tx1, tx2, tx3, tx4, tx5, tx6;
	ImageButton bkmarkBtn, callBtn, webBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_program_click);

		Intent intent = getIntent();
		valueGu = intent.getExtras().getInt("gu");
		valueDong = intent.getExtras().getInt("dong");
		clickUrl = intent.getStringExtra("click_url").toString();
		// url 확인
		System.out.println(valueGu + valueDong + clickUrl);

		helper = new MyDBHelper(this);
		db = helper.getWritableDatabase();

		tx1 = (TextView) findViewById(R.id.textView1);
		tx2 = (TextView) findViewById(R.id.textView2);
		tx3 = (TextView) findViewById(R.id.textView3);
		tx4 = (TextView) findViewById(R.id.textView4);
		tx5 = (TextView) findViewById(R.id.textView5);
		tx6 = (TextView) findViewById(R.id.textView6);

		bkmarkBtn = (ImageButton) findViewById(R.id.d_bkmark_btn);
		bkmarkBtn.setOnClickListener(bkmarkBtnClickListener);

		callBtn = (ImageButton) findViewById(R.id.d_call_btn);
		callBtn.setOnClickListener(callBtnClickListener);

		webBtn = (ImageButton) findViewById(R.id.d_web_btn);
		webBtn.setOnClickListener(webBtnClickListener);

		// Start getLecture_detail
		new getLecture_detail().execute();
	}

	// Button ClickListener
	// .......................................................................................................................................
	private OnClickListener bkmarkBtnClickListener = new OnClickListener() {
		public void onClick(View v) {

			try {
				db.execSQL("INSERT INTO myLecture VALUES(null, '" + dong + "'" + "," + "'" + name + "'" + "," + "'"
						+ time + "'" + "," + "'" + clickUrl + "');");
				Toast.makeText(tab_program_click.this, "즐겨찾기에 추가되었습니다.", 1).show();
			} catch (Exception e) {
				Toast.makeText(tab_program_click.this, e.getMessage(), 1).show();
			}
		}
	};
	private OnClickListener callBtnClickListener = new OnClickListener() {
		public void onClick(View v) {
			// uses-permission !!!!!!!!!!!!!!!!

			try {

				aCode = phone.substring(0, 2);
				if (!aCode.equals("02")) {
					phone = "02-" + phone;
				}

				Intent callIntent = new Intent(Intent.ACTION_DIAL); // Intent.ACTION_CALL
				callIntent.setData(Uri.parse("tel:" + phone));
				startActivity(callIntent);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(tab_program_click.this, e.getMessage(), 1).show();
			}
		}
	};
	private OnClickListener webBtnClickListener = new OnClickListener() {
		public void onClick(View v) {
			Uri uri = Uri.parse(clickUrl);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
	};

	// thread
	// ...................................................................................................................................
	private class getLecture_detail extends AsyncTask<Void, Void, Void> {

		ProgressDialog pd;

		// before doInBackground()
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			// Dialog start
			pd = new ProgressDialog(getParent());
			pd.setMessage("잠시만 기다려주세요");
			pd.setIndeterminate(false);
			pd.show();
		}

		// Perform an operation on a background thread
		@Override
		protected Void doInBackground(Void... arg0) {
			// detailPage Pasing
			try {
				Elements contents, content;
				StringTokenizer st;
				String[] dongArray, phoneArray;

				if (valueGu == 11) {
					dongArray = getResources().getStringArray(R.array.dongdm_array);
					dong = dongArray[valueDong];

					st = new StringTokenizer(clickUrl, "^");

					name = st.nextToken();
					place = st.nextToken();
					time = st.nextToken();
					price = st.nextToken();
					phone = st.nextToken();
				} else if (valueGu == 21) {
					dongArray = getResources().getStringArray(R.array.yongsan_array);
					dong = dongArray[valueDong];

					st = new StringTokenizer(clickUrl, "^");
					name = st.nextToken();
					period = st.nextToken();
					price = st.nextToken();
					clickUrl = st.nextToken();
				}

				else {
					Document doc = Jsoup.connect(clickUrl).timeout(5000).get(); // 웹!!

					switch (valueGu) {
					case 1:

						dongArray = getResources().getStringArray(R.array.gangnam_array);
						dong = dongArray[valueDong];
						phoneArray = getResources().getStringArray(R.array.gangnam_phone_array);
						phone = phoneArray[valueDong];
						contents = doc.select("#lect_view table.pglist span");

						name = contents.get(2).text();
						period = contents.get(4).text();
						time = contents.get(5).text();
						place = contents.get(3).text();
						price = contents.get(8).text();

						intro = contents.get(12).text();
						break;

					case 3:

						dongArray = getResources().getStringArray(R.array.gangbuk_array);
						dong = dongArray[valueDong];

						contents = doc.select("#forPrint span");

						name = contents.get(2).text();
						period = contents.get(5).text();
						time = contents.get(6).text();
						place = contents.get(10).text();
						price = contents.get(7).text();
						phone = contents.get(11).text();
						intro = contents.get(14).text();
						break;

					case 4:

						dongArray = getResources().getStringArray(R.array.gangseo_array);
						dong = dongArray[valueDong];

						content = doc.select("strong.tit");
						contents = doc.select("#content span");

						name = content.get(0).text();
						time = contents.get(16).text();
						place = contents.get(12).text();
						price = contents.get(3).text();
						phone = contents.get(14).text();
						st = new StringTokenizer(time, "(");
						period = st.nextToken();
						time = st.nextToken();
						StringTokenizer stz = new StringTokenizer(time, ")");
						time = stz.nextToken();
						intro = contents.get(17).text();
						break;

					case 5:
						dongArray = getResources().getStringArray(R.array.gwanak_array);
						dong = dongArray[valueDong];

						contents = doc.select(".board tr td");
						content = doc.select("#view_con_1 p");

						// System.out.println("--------" + content.text());
						// for (int i = 0; i < contents.size(); i++) {
						// System.out.println(i + "--------" +
						// contents.get(i).text());
						// }
						st = new StringTokenizer(contents.get(0).text(), " ");
						name = st.nextToken();

						st = new StringTokenizer(contents.get(2).text(), " ");

						period = st.nextToken();
						time = st.nextToken();
						time = time + st.nextToken();

						st = new StringTokenizer(contents.get(7).text(), ",");
						phone = st.nextToken();
						place = contents.get(3).text();
						price = contents.get(4).text();
						intro = content.text();
						break;
					case 6:

						dongArray = getResources().getStringArray(R.array.gwangjin_array);
						dong = dongArray[valueDong];

						contents = doc.select(".tdtxtAlign");

						name = contents.get(0).text();
						period = contents.get(5).text();
						time = contents.get(2).text() + contents.get(3).text();
						place = contents.get(7).text();
						price = contents.get(3).text();
						phone = contents.get(8).text();
						intro = contents.get(11).text();
						break;
					case 7:

						dongArray = getResources().getStringArray(R.array.guro_array);
						dong = dongArray[valueDong];

						contents = doc.select("#ctl00_ContentPlaceHolder1_FormView1 .bbs1_a1");

						name = contents.get(2).text();
						period = contents.get(4).text();
						time = contents.get(5).text();
						place = contents.get(6).text();
						price = contents.get(8).text();
						phone = contents.get(11).text();
						intro = contents.get(13).text();
						break;
					case 8:

						dongArray = getResources().getStringArray(R.array.geumcheon_array);
						dong = dongArray[valueDong];

						content = doc.select(".tbl-vfir");
						contents = doc.select(".tblH02 td");

						name = content.get(0).text();
						period = contents.get(2).text();
						time = contents.get(4).text();
						place = contents.get(3).text();
						price = contents.get(6).text();
						phone = contents.get(11).text();
						intro = contents.get(22).text();
						break;
					case 9:

						dongArray = getResources().getStringArray(R.array.nowon_array);
						dong = dongArray[valueDong];

						contents = doc.select("table td td");

						name = contents.get(2).text();
						period = contents.get(26).text();

						time = contents.get(30).text();
						st = new StringTokenizer(time, "▶");
						time = st.nextToken();
						place = st.nextToken();

						price = contents.get(19).text();
						phone = contents.get(6).text();
						intro = contents.get(36).text();
						break;

					case 10:

						dongArray = getResources().getStringArray(R.array.dobong_array);
						dong = dongArray[valueDong];

						contents = doc.select("#ContentPlaceHolder1_FormView1 tr td td");

						name = contents.get(5).text();
						period = contents.get(8).text();
						time = contents.get(10).text();
						place = contents.get(12).text();
						price = contents.get(15).text();
						phone = contents.get(20).text();
						intro = contents.get(23).text();
						break;
					case 12:

						dongArray = getResources().getStringArray(R.array.dongjak_array);
						dong = dongArray[valueDong];

						contents = doc.select("#ctl00_ContentPlaceHolder1_FormView1 .bbs1_a1");

						name = contents.get(2).text();
						period = contents.get(4).text();
						time = contents.get(5).text();
						place = contents.get(6).text();
						price = contents.get(8).text();
						phone = contents.get(11).text();
						intro = contents.get(13).text();
						break;
					case 13:

						dongArray = getResources().getStringArray(R.array.mapo_array);
						dong = dongArray[valueDong];

						contents = doc.select("#ctl00_ContentPlaceHolder1_FormView1 tr tr td");

						name = contents.get(8).text();
						period = contents.get(13).text();
						time = contents.get(16).text();
						place = contents.get(19).text();
						price = contents.get(24).text();
						phone = contents.get(32).text();
						intro = contents.get(37).text();
						break;
					case 14:

						dongArray = getResources().getStringArray(R.array.sdmoon_array);
						dong = dongArray[valueDong];

						contents = doc.select("#ctl00_ContentPlaceHolder1_FormView1 .bbs1_a1");

						name = contents.get(2).text();
						period = contents.get(4).text();
						time = contents.get(5).text();
						place = contents.get(6).text();
						price = contents.get(8).text();
						st = new StringTokenizer(contents.get(11).text(), ",");
						phone = st.nextToken();
						intro = contents.get(13).text();
						break;
					case 15:

						dongArray = getResources().getStringArray(R.array.seocho_array);
						dong = dongArray[valueDong];

						content = doc.select(".con-title1");
						name = content.text();
						contents = doc.select(".view tr td");
						for (int i = 0; i < contents.size(); i++) {
							System.out.println(i + "--------" + contents.get(i).text());
						}

						period = contents.get(5).text();
						time = contents.get(7).text();
						place = contents.get(8).text();
						price = contents.get(4).text();
						phone = contents.get(13).text();
						content = doc.select("#tab1 div");
						intro = content.text();
						break;
					case 16:

						dongArray = getResources().getStringArray(R.array.seongdong_array);
						dong = dongArray[valueDong];

						contents = doc.select("#ctl00_ContentPlaceHolder1_FormView1 .bbs1_a1");

						name = contents.get(2).text();
						period = contents.get(4).text();
						time = contents.get(5).text();
						place = contents.get(6).text();
						price = contents.get(8).text();
						st = new StringTokenizer(contents.get(11).text(), ",");
						phone = st.nextToken();
						intro = contents.get(13).text();
						break;
					case 20:

						dongArray = getResources().getStringArray(R.array.yeongdpo_array);
						dong = dongArray[valueDong];
						phoneArray = getResources().getStringArray(R.array.yeongdpo_phone_array);
						phone = phoneArray[valueDong];
						contents = doc.select(".bbswrite tbody tr td");
						for (int i = 0; i < contents.size(); i++) {
							System.out.println(i + "--------" + contents.get(i).text());
						}
						name = contents.get(2).text();
						period = contents.get(12).text();
						time = contents.get(14).text();
						place = contents.get(8).text();
						price = contents.get(16).text();
						intro = contents.get(22).text();
						break;
					case 22:

						dongArray = getResources().getStringArray(R.array.eunpyeong_array);
						dong = dongArray[valueDong];

						contents = doc.select(".left_td");

						name = contents.get(0).text();
						period = contents.get(13).text();
						time = contents.get(3).text() + contents.get(2).text();
						place = contents.get(6).text();
						price = contents.get(8).text();
						phone = contents.get(9).text();
						intro = contents.get(14).text();
						break;
					case 23:
						dongArray = getResources().getStringArray(R.array.jongno_array);
						dong = dongArray[valueDong];

						contents = doc.select(".bbs1_a1");

						name = contents.get(2).text();
						period = contents.get(4).text();
						time = contents.get(5).text();
						place = contents.get(6).text();
						price = contents.get(8).text();
						phone = contents.get(11).text();
						intro = contents.get(13).text();
						break;
					case 24:
						dongArray = getResources().getStringArray(R.array.junggu_array);
						dong = dongArray[valueDong];

						contents = doc.select(".tableType2 tr td");
						for (int i = 0; i < contents.size(); i++) {
							System.out.println(i + "--------" + contents.get(i).text());
						}
						name = contents.get(0).text();
						period = contents.get(7).text();
						time = contents.get(9).text() + contents.get(10).text();
						place = contents.get(5).text();
						price = contents.get(3).text();
						phone = contents.get(6).text();
						intro = contents.get(11).text();
						break;

					case 25:
						dongArray = getResources().getStringArray(R.array.jungnang_array);
						dong = dongArray[valueDong];

						content = doc.select(".boardview tr th");
						contents = doc.select(".boardview tr td");
						for (int i = 0; i < contents.size(); i++) {
							System.out.println(i + "--------" + contents.get(i).text());
						}
						name = content.get(0).text();
						period = contents.get(2).text();
						time = contents.get(6).text() + contents.get(7).text();
						place = contents.get(1).text();
						price = contents.get(8).text();

						st = new StringTokenizer(contents.get(13).text(), ",");
						for (int t = 0; t <= st.countTokens(); t++) {
							phone = st.nextToken();
						}
						intro = contents.get(14).text();
						break;
					}

				}

			} catch (

			Exception e)

			{
				Log.d("Exception", e.toString());

			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Void... arg0) {

		}

		// After doInBackground()
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (name != null) {
				tx1.setText(name);
				tx2.setText("강의 기간 :  " + period);
				tx3.setText("강의 시간 :  " + time);
				tx4.setText("강의 장소 :  " + place);
				tx5.setText(" 강 의 료  :  " + price);
				tx6.setText(intro);
				pd.dismiss();

			} else {
				pd.dismiss();
				Toast.makeText(tab_program_click.this, "인터넷 연결이 불안정합니다..", 1).show();
			}

		}

		@Override
		protected void onCancelled() {
			// cancel 메소드 호출 시
		}

	}
}