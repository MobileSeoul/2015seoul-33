package com.seoul.juminprogram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.seoul.juminprogram.Jsoup_folder.Jsoup_ddmoon_11;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_dobong_10;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_dongjak_12;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_eunpyeong_22;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_gangbuk_3;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_gangdong_2;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_gangnam_1;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_gangseo_4;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_geumcheon_8;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_guro_7;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_gwanak_5;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_gwangjin_6;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_jongno_23;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_junggu_24;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_jungnang_25;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_mapo_13;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_nowon_9;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_sdmoon_14;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_seocho_15;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_seongbuk_17;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_seongdong_16;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_songpa_18;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_yangcheon_19;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_yeongdpo_20;
import com.seoul.juminprogram.Jsoup_folder.Jsoup_yongsan_21;



public class tab_map_program extends Activity {
	int valueDong, valueGu;
	Uri uri;
	List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
	HashMap<String, String> data;
	programAdapter listAdapter;
	ListView list;

	ArrayList<String> lectureList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_map_program);

		Intent intent = getIntent();
		valueDong = intent.getExtras().getInt("dong");
		valueGu = intent.getExtras().getInt("gu");

		new getLecture().execute();
	}

	public class programAdapter extends ArrayAdapter<String> {
		Context cnt;
		private ArrayList<String> list;

		public programAdapter(Context context, int listResource,
				ArrayList<String> list) {
			super(context, listResource, list);
			this.cnt = context;
			this.list = list;
		}

		@Override
		public View getView(int position, View v, ViewGroup parent) {
			View view = null;

			if (v == null) {
				LayoutInflater vi = (LayoutInflater) cnt
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				view = vi.inflate(R.layout.tab_program_item, null);

			} else {

				view = v;
			}
			String p = list.get(position);

			if (p != null) {
				TextView programTxt = (TextView) view
						.findViewById(R.id.textView1);
				programTxt.setText(p);
			}
			return view;
		}
	}

	// thread
	// .....................................................................................................................................
	private class getLecture extends AsyncTask<Void, Void, Void> {

		ProgressDialog pd;

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			dataList = new ArrayList<HashMap<String, String>>();
			lectureList = new ArrayList<String>();
			// Dialog start
			pd = new ProgressDialog(getParent());
			pd.setMessage("잠시만 기다려주세요");
			pd.setIndeterminate(false);
			pd.show();

		}

		// Perform an operation on a background thread
		@Override
		protected Void doInBackground(Void... arg0) {
			// seoul Gu page Pasing
			try {
				if (valueGu == 17) {

					Jsoup_seongbuk_17 paser17 = new Jsoup_seongbuk_17();
					String url = paser17.link_Url(valueDong);

					if (url.length() > 0) {
						uri = Uri.parse(url);

						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					} else {
						lectureList.add("신청 가능한 강좌 정보가 없습니다.");
					}

				} else {
					switch (valueGu) {
					case 1:
						Jsoup_gangnam_1 paser1 = new Jsoup_gangnam_1();
						dataList = paser1.parse(valueDong);
						break;
					case 2:
						Jsoup_gangdong_2 paser2 = new Jsoup_gangdong_2();
						dataList = paser2.parse(valueDong);
						break;
					case 3:
						Jsoup_gangbuk_3 paser3 = new Jsoup_gangbuk_3();
						dataList = paser3.parse(valueDong);
						break;
					case 4:
						Jsoup_gangseo_4 paser4 = new Jsoup_gangseo_4();
						dataList = paser4.parse(valueDong);
						break;
					case 5:
						Jsoup_gwanak_5 paser5 = new Jsoup_gwanak_5();
						dataList = paser5.parse(valueDong);
						break;
					case 6:
						Jsoup_gwangjin_6 paser6 = new Jsoup_gwangjin_6();
						dataList = paser6.parse(valueDong);
						break;
					case 7:
						Jsoup_guro_7 paser7 = new Jsoup_guro_7();
						dataList = paser7.parse(valueDong);
						break;
					case 8:
						Jsoup_geumcheon_8 paser8 = new Jsoup_geumcheon_8();
						dataList = paser8.parse(valueDong);
						break;
					case 9:
						Jsoup_nowon_9 paser9 = new Jsoup_nowon_9();
						dataList = paser9.parse(valueDong);
						break;
					case 10:
						Jsoup_dobong_10 paser10 = new Jsoup_dobong_10();
						dataList = paser10.parse(valueDong);
						break;
					case 11:
						Jsoup_ddmoon_11 paser11 = new Jsoup_ddmoon_11();
						dataList = paser11.parse(valueDong);
						break;
					case 12:
						Jsoup_dongjak_12 paser12 = new Jsoup_dongjak_12();
						dataList = paser12.parse(valueDong);
						break;
					case 13:
						Jsoup_mapo_13 paser13 = new Jsoup_mapo_13();
						dataList = paser13.parse(valueDong);
						break;
					case 14:
						Jsoup_sdmoon_14 paser14 = new Jsoup_sdmoon_14();
						dataList = paser14.parse(valueDong);
						break;
					case 15:
						Jsoup_seocho_15 paser15 = new Jsoup_seocho_15();
						dataList = paser15.parse(valueDong);
						break;
					case 16:
						Jsoup_seongdong_16 paser16 = new Jsoup_seongdong_16();
						dataList = paser16.parse(valueDong);
						break;

					case 18:
						Jsoup_songpa_18 paser18 = new Jsoup_songpa_18();
						dataList = paser18.parse(valueDong);
						break;
					case 19:
						Jsoup_yangcheon_19 paser19 = new Jsoup_yangcheon_19();
						dataList = paser19.parse(valueDong);
						break;
					case 20:
						Jsoup_yeongdpo_20 paser20 = new Jsoup_yeongdpo_20();
						dataList = paser20.parse(valueDong);
						break;
					case 21:
						Jsoup_yongsan_21 paser21 = new Jsoup_yongsan_21();
						dataList = paser21.parse(valueDong);
						break;
					case 22:
						Jsoup_eunpyeong_22 paser22 = new Jsoup_eunpyeong_22();
						dataList = paser22.parse(valueDong);
						break;
					case 23:
						Jsoup_jongno_23 paser23 = new Jsoup_jongno_23();
						dataList = paser23.parse(valueDong);
						break;
					case 24:
						Jsoup_junggu_24 paser24 = new Jsoup_junggu_24();
						dataList = paser24.parse(valueDong);

						break;
					case 25:
						Jsoup_jungnang_25 paser25 = new Jsoup_jungnang_25();
						dataList = paser25.parse(valueDong);
						break;
					}
					for (int i = 0; i < dataList.size(); i++) {
						data = new HashMap<String, String>();

						data = dataList.get(i);
						String getLec = data.get("lecture");

						lectureList.add(getLec);

					}
					if (lectureList.size() == 0) {
						lectureList.add("선택 가능 강좌 없습니다.");
					}
				}

			} catch (Exception e) {
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

			listAdapter = new programAdapter(tab_map_program.this,
					R.layout.tab_program_item, lectureList);
			list = (ListView) findViewById(R.id.list_lecture);
			list.setAdapter(listAdapter);

			pd.dismiss();
			list.setOnItemClickListener(listView_ClickListener);

		}

		@Override
		protected void onCancelled() {

		}

		// listview Click
		// ...........................................................................................................................
		private OnItemClickListener listView_ClickListener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (dataList.size() == 0) {
					;
				} else {
					data = new HashMap<String, String>();
					data = dataList.get(position);
					String getLink = data.get("link");

					if (getLink != null) {
						Intent Int = new Intent(tab_map_program.this,
								tab_program_click.class);
						Int.putExtra("click_url", getLink);
						Int.putExtra("gu", valueGu);
						Int.putExtra("dong", valueDong);

						view = tab_map_tabhost.MapTabHGroup
								.getLocalActivityManager()
								.startActivity(
										"Activity_list_lecture_click",
										Int.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
								.getDecorView();

						tab_map_tabhost.MapTabHGroup.replaceView(view);

					} else {
						;
					}

				}

			}
		};
	}

}