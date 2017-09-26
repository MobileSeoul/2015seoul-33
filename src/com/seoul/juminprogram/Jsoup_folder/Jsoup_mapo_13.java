package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_mapo_13 {

	String jsoupUrl = "http://jachi.mapo.go.kr/JUMIN/main.aspx?", dong_url;
	String click_url = "http://jachi.mapo.go.kr";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "DongCode=1";
			break;
		case 1:
			dong_url = "DongCode=5";
			break;
		case 2:
			dong_url = "DongCode=3";
			break;
		case 3:
			dong_url = "DongCode=11";
			break;
		case 4:
			dong_url = "DongCode=12";
			break;
		case 5:
			dong_url = "DongCode=16";
			break;
		case 6:
			dong_url = "DongCode=8";
			break;
		case 7:
			dong_url = "DongCode=9";
			break;
		case 8:
			dong_url = "DongCode=14";
			break;
		case 9:
			dong_url = "DongCode=15";
			break;
		case 10:
			dong_url = "DongCode=7";
			break;
		case 11:
			dong_url = "DongCode=2";
			break;
		case 12:
			dong_url = "DongCode=13";
			break;
		case 13:
			dong_url = "DongCode=6";
			break;
		case 14:
			dong_url = "DongCode=4";
			break;
		case 15:
			dong_url = "DongCode=10";
			break;

		}
		// 마포구 - 전부 1페이지 - 전부 신청가능
		try {

			Document doc = Jsoup.connect(jsoupUrl + dong_url).timeout(5000).get(); // 웹!!
			Elements contents = doc.select("#box1 td a");

			for (int i = 0; i < contents.size(); i++) {
				HashMap<String, String> data = new HashMap<String, String>();

				data.put("lecture", contents.get(i).text());
				data.put("link", click_url + contents.get(i).attr("href"));

				dataList.add(data);

			}

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return dataList;
	}
}
