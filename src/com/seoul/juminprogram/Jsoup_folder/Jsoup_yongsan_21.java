package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_yongsan_21 {

	String jsoupUrl = "http://jachi.yongsan.go.kr/Jumin/main.aspx?", dong_url;
	String click_url = "https://jachi.yongsan.go.kr";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "DongCode=3";
			break;
		case 1:
			dong_url = "DongCode=16";
			break;
		case 2:
			dong_url = "DongCode=15";
			break;
		case 3:
			dong_url = "DongCode=8";
			break;
		case 4:
			dong_url = "DongCode=2";
			break;
		case 5:
			dong_url = "DongCode=5";
			break;
		case 6:
			dong_url = "DongCode=6";
			break;
		case 7:
			dong_url = "DongCode=10";
			break;
		case 8:
			dong_url = "DongCode=11";
			break;
		case 9:
			dong_url = "DongCode=12";
			break;
		case 10:
			dong_url = "DongCode=13";
			break;
		case 11:
			dong_url = "DongCode=4";
			break;
		case 12:
			dong_url = "DongCode=9";
			break;
		case 13:
			dong_url = "DongCode=14";
			break;
		case 14:
			dong_url = "DongCode=7";
			break;
		case 15:
			dong_url = "DongCode=1";
			break;

		}
		// 용산구 - 모두1페이지 - 마감 아닌 정보 읽어옴
		try {
			Document doc = Jsoup.connect(jsoupUrl + dong_url).timeout(5000).get(); // 웹!!
			// url 확인
			System.out.println(jsoupUrl + dong_url);

			Elements contents = doc.select("#programTable_1 td");
			Elements e = doc.select("#programTable_1 td a");
			int j = 0;
			for (int i = 0; i < 20; i++) { // contents.size() / 6;
				HashMap<String, String> data = new HashMap<String, String>();

				if (!contents.get(i * 6 + 5).text().equals("마감")) {

					data.put("lecture", contents.get(i * 6).text());
					data.put("link", contents.get(i * 6).text() + "^" + contents.get(i * 6 + 3).text() + "^"
							+ contents.get(i * 6 + 4).text() + "^" + click_url + e.get(j).attr("href"));

					dataList.add(data);
					System.out.println(j + "===============" + e.get(j).attr("href"));
					j = j + 1;
				} else {
					j = j + 2;
				}
			}

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return dataList;
	}
}
