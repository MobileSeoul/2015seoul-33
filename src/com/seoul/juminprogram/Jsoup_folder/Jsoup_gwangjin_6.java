package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_gwangjin_6 {

	String jsoupUrl = "http://www.gwangjin.go.kr/dc/edu-program-list.do?layoutId=030201&", dong_url;
	String click_url = "http://www.gwangjin.go.kr";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "dongId=09";
			break;
		case 1:
			dong_url = "dongId=06";
			break;
		case 2:
			dong_url = "dongId=07";
			break;
		case 3:
			dong_url = "dongId=08";
			break;
		case 4:
			dong_url = "dongId=16";
			break;
		case 5:
			dong_url = "dongId=05";
			break;
		case 6:
			dong_url = "dongId=10";
			break;
		case 7:
			dong_url = "dongId=11";
			break;
		case 8:
			dong_url = "dongId=12";
			break;
		case 9:
			dong_url = "dongId=17";
			break;
		case 10:
			dong_url = "dongId=01";
			break;
		case 11:
			dong_url = "dongId=02";
			break;
		case 12:
			dong_url = "dongId=03";
			break;
		case 13:
			dong_url = "dongId=04";
			break;
		case 14:
			dong_url = "dongId=15";
			break;

		}
		// 광진구 - 교육종료 제외한 나머지 가져오기
		try {

			int n = 1;
			while (true) {

				Document doc = Jsoup.connect(jsoupUrl + dong_url + "&targetPage=" + n).timeout(5000).get();// 웹!!

				Elements ing = doc.select("tbody td img");
				Elements contents = doc.select("tbody tr a");

				if (contents.size() == 0)
					break;
				for (int i = 0; i < ing.size(); i++) {
					String str = ing.get(i).attr("alt");
					if (!str.equals("교육종료")) {
						HashMap<String, String> data = new HashMap<String, String>();
						data.put("lecture", contents.get(i).text());
						data.put("link", click_url + contents.get(i).attr("href"));

						dataList.add(data);
					}

				}

				n++;
			}

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return dataList;
	}
}
