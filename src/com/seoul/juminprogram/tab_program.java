package com.seoul.juminprogram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.seoul.juminprogram.R;
import com.seoul.juminprogram.Jsoup_folder.*;
import com.seoul.juminprogram.Java_folder.*;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class tab_program extends Activity {

	boolean action = false, actdong = false;
	int selectdDong, selectdGu;
	Uri uri;

	List<HashMap<String, String>> dataList;
	HashMap<String, String> data;
	ArrayList<String> lectureList;
	programAdapter listAdapter;

	ListView list;
	Spinner spinnerGu, spinnerDong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_program);

		dataList = new ArrayList<HashMap<String, String>>();

		spinnerGu = (Spinner) findViewById(R.id.spinner_gu_select);
		set_gu_Spinners();

		spinnerDong = (Spinner) findViewById(R.id.spinner_dong_select);
		ArrayAdapter<CharSequence> dadapter = ArrayAdapter
				.createFromResource(this, R.array.empty_array,
						android.R.layout.simple_spinner_item);
		dadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDong.setAdapter(new defaultSpinnerAdapter(dadapter,
				R.layout.spinner_dong_no_select, tab_program.this));

		spinnerGu.setOnItemSelectedListener(gu_selectedlistener);
		spinnerDong.setOnItemSelectedListener(dong_selectedListener);

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
				if (selectdGu == 17) {

					Jsoup_seongbuk_17 paser17 = new Jsoup_seongbuk_17();
					String url = paser17.link_Url(selectdDong);

					if (url.length() > 0) {
						uri = Uri.parse(url);

						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					} else {
						lectureList.add("강좌 정보 xxx");
					}

				} else {
					switch (selectdGu) {
					case 1:
						Jsoup_gangnam_1 paser1 = new Jsoup_gangnam_1();
						dataList = paser1.parse(selectdDong);
						break;
					case 2:
						Jsoup_gangdong_2 paser2 = new Jsoup_gangdong_2();
						dataList = paser2.parse(selectdDong);
						break;
					case 3:
						Jsoup_gangbuk_3 paser3 = new Jsoup_gangbuk_3();
						dataList = paser3.parse(selectdDong);
						break;
					case 4:
						Jsoup_gangseo_4 paser4 = new Jsoup_gangseo_4();
						dataList = paser4.parse(selectdDong);
						break;
					case 5:
						Jsoup_gwanak_5 paser5 = new Jsoup_gwanak_5();
						dataList = paser5.parse(selectdDong);
						break;
					case 6:
						Jsoup_gwangjin_6 paser6 = new Jsoup_gwangjin_6();
						dataList = paser6.parse(selectdDong);
						break;
					case 7:
						Jsoup_guro_7 paser7 = new Jsoup_guro_7();
						dataList = paser7.parse(selectdDong);
						break;
					case 8:
						Jsoup_geumcheon_8 paser8 = new Jsoup_geumcheon_8();
						dataList = paser8.parse(selectdDong);
						break;
					case 9:
						Jsoup_nowon_9 paser9 = new Jsoup_nowon_9();
						dataList = paser9.parse(selectdDong);
						break;
					case 10:
						Jsoup_dobong_10 paser10 = new Jsoup_dobong_10();
						dataList = paser10.parse(selectdDong);
						break;
					case 11:
						Jsoup_ddmoon_11 paser11 = new Jsoup_ddmoon_11();
						dataList = paser11.parse(selectdDong);
						break;
					case 12:
						Jsoup_dongjak_12 paser12 = new Jsoup_dongjak_12();
						dataList = paser12.parse(selectdDong);
						break;
					case 13:
						Jsoup_mapo_13 paser13 = new Jsoup_mapo_13();
						dataList = paser13.parse(selectdDong);
						break;
					case 14:
						Jsoup_sdmoon_14 paser14 = new Jsoup_sdmoon_14();
						dataList = paser14.parse(selectdDong);
						break;
					case 15:
						Jsoup_seocho_15 paser15 = new Jsoup_seocho_15();
						dataList = paser15.parse(selectdDong);
						break;
					case 16:
						Jsoup_seongdong_16 paser16 = new Jsoup_seongdong_16();
						dataList = paser16.parse(selectdDong);
						break;

					case 18:
						Jsoup_songpa_18 paser18 = new Jsoup_songpa_18();
						dataList = paser18.parse(selectdDong);
						break;
					case 19:
						Jsoup_yangcheon_19 paser19 = new Jsoup_yangcheon_19();
						dataList = paser19.parse(selectdDong);
						break;
					case 20:
						Jsoup_yeongdpo_20 paser20 = new Jsoup_yeongdpo_20();
						dataList = paser20.parse(selectdDong);
						break;
					case 21:
						Jsoup_yongsan_21 paser21 = new Jsoup_yongsan_21();
						dataList = paser21.parse(selectdDong);
						break;
					case 22:
						Jsoup_eunpyeong_22 paser22 = new Jsoup_eunpyeong_22();
						dataList = paser22.parse(selectdDong);
						break;
					case 23:
						Jsoup_jongno_23 paser23 = new Jsoup_jongno_23();
						dataList = paser23.parse(selectdDong);
						break;
					case 24:
						Jsoup_junggu_24 paser24 = new Jsoup_junggu_24();
						dataList = paser24.parse(selectdDong);

						break;
					case 25:
						Jsoup_jungnang_25 paser25 = new Jsoup_jungnang_25();
						dataList = paser25.parse(selectdDong);
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

			listAdapter = new programAdapter(tab_program.this,
					R.layout.tab_program_item, lectureList);
			list = (ListView) findViewById(R.id.list_lecture);
			list.setAdapter(listAdapter);
			list.setOnItemClickListener(listView_ClickListener);
			pd.dismiss();

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
				System.out.println("itemcli");
				if (dataList.size() == 0) {
					;
				} else {
					System.out.println(dataList.size());
					data = new HashMap<String, String>();
					data = dataList.get(position);
					String getLink = data.get("link");

					if (getLink != null) {
						Intent Int = new Intent(tab_program.this,
								tab_program_click.class);
						Int.putExtra("click_url", getLink);
						Int.putExtra("gu", selectdGu);
						Int.putExtra("dong", selectdDong);

						view = tab_program_tabhost.FirstTabHGroup
								.getLocalActivityManager()
								.startActivity(
										"Activity_list_lecture_click",
										Int.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
								.getDecorView();

						tab_program_tabhost.FirstTabHGroup.replaceView(view);

					} else {
						;
					}

				}

			}
		};
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

	// spinner setting
	// .................................................................................................................................

	private void set_gu_Spinners() {
		ArrayAdapter<CharSequence> gAdapter = ArrayAdapter.createFromResource(
				this, R.array.seoulGu_array,
				android.R.layout.simple_spinner_item);
		gAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerGu.setAdapter(new defaultSpinnerAdapter(gAdapter,
				R.layout.spinner_gu_no_select, this));

	}

	private void set_dong_Spinners(int itemNum) {

		ArrayAdapter<CharSequence> dadapter;
		dadapter = ArrayAdapter.createFromResource(this, itemNum,
				android.R.layout.simple_spinner_item);
		// dadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// spinnerDong.setAdapter(dadapter);
		//
		dadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDong.setAdapter(new defaultSpinnerAdapter(dadapter,
				R.layout.spinner_dong_no_select, tab_program.this));
	}

	// spinner change
	// ..................................................................................................................................
	private OnItemSelectedListener dong_selectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			selectdDong = position - 1;
			if (actdong) {

				// Start getLecture_detail
				new getLecture().execute();

			} else {
				actdong = true;
			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub // No action
		}

	};

	private OnItemSelectedListener gu_selectedlistener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			if (action) {
				actdong = false;
				selectdGu = position;

				switch (position) {
				case 1:
					set_dong_Spinners(R.array.gangnam_array);
					break;
				case 2:
					set_dong_Spinners(R.array.gangdong_array);
					break;
				case 3:
					set_dong_Spinners(R.array.gangbuk_array);
					break;
				case 4:
					set_dong_Spinners(R.array.gangseo_array);
					break;
				case 5:
					set_dong_Spinners(R.array.gwanak_array);
					break;
				case 6:
					set_dong_Spinners(R.array.gwangjin_array);
					break;
				case 7:
					set_dong_Spinners(R.array.guro_array);
					break;
				case 8:
					set_dong_Spinners(R.array.geumcheon_array);
					break;
				case 9:
					set_dong_Spinners(R.array.nowon_array);
					break;
				case 10:
					set_dong_Spinners(R.array.dobong_array);
					break;
				case 11:
					set_dong_Spinners(R.array.dongdm_array);
					break;
				case 12:
					set_dong_Spinners(R.array.dongjak_array);
					break;
				case 13:
					set_dong_Spinners(R.array.mapo_array);
					break;
				case 14:
					set_dong_Spinners(R.array.sdmoon_array);
					break;
				case 15:
					set_dong_Spinners(R.array.seocho_array);
					break;
				case 16:
					set_dong_Spinners(R.array.seongdong_array);
					break;
				case 17:
					set_dong_Spinners(R.array.seongbuk_array);
					break;
				case 18:
					set_dong_Spinners(R.array.songpa_array);
					break;
				case 19:
					set_dong_Spinners(R.array.yangcheon_array);
					break;
				case 20:
					set_dong_Spinners(R.array.yeongdpo_array);
					break;
				case 21:
					set_dong_Spinners(R.array.yongsan_array);
					break;
				case 22:
					set_dong_Spinners(R.array.eunpyeong_array);
					break;
				case 23:
					set_dong_Spinners(R.array.jongno_array);
					break;
				case 24:
					set_dong_Spinners(R.array.junggu_array);
					break;
				case 25:
					set_dong_Spinners(R.array.jungnang_array);
					break;
				}
			} else {
				action = true;
			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub // No action

		}

	};
}