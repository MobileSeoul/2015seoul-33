package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_dongjak_12 {

	String jsoupUrl = "http://jachi.dongjak.go.kr/JUMIN/main.aspx?DongCode=", dong_url;
	String click_url = "http://jachi.dongjak.go.kr";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "1";
			break;
		case 1:
			dong_url = "2";
			break;
		case 2:
			dong_url = "13";
			break;
		case 3:
			dong_url = "8";
			break;
		case 4:
			dong_url = "9";
			break;
		case 5:
			dong_url = "10";
			break;
		case 6:
			dong_url = "11";
			break;
		case 7:
			dong_url = "12";
			break;
		case 8:
			dong_url = "3";
			break;
		case 9:
			dong_url = "4";
			break;
		case 10:
			dong_url = "5";
			break;
		case 11:
			dong_url = "6";
			break;
		case 12:
			dong_url = "14";
			break;
		case 13:
			dong_url = "15";
			break;
		case 14:
			dong_url = "7";
			break;

		}

		// 동작구 - 전부1페이지
		try {
			Document doc = Jsoup.connect(jsoupUrl + dong_url).timeout(5000).get(); // 웹!!
			// url 확인
			System.out.println(jsoupUrl + dong_url);

			Elements link = doc.select("#mainprogram tbody tbody tr td a");
			Elements contents = doc.select("#mainprogram tbody tbody tr td span");

			for (int i = 0; i < link.size(); i++) {
				HashMap<String, String> data = new HashMap<String, String>();
				if (!contents.get(i * 8 + 7).text().equals("마감")) {

					data.put("lecture", contents.get(i * 8).text());
					data.put("link", click_url + link.get(i).attr("href"));

					dataList.add(data);
				}

			}
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return dataList;
	}
}
