package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.util.Log;

public class Jsoup_seongdong_16 {

	String jsoupUrl = "https://jachi.sd.go.kr/JUMIN/sub02/sub02_01.aspx?", dong_url;
	// "https://jachi.sd.go.kr/jumin/SSODefault.aspx?"
	String click_url = "https://jachi.sd.go.kr/JUMIN/sub02/";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "DongCode=1";
			break;
		case 1:
			dong_url = "DongCode=2";
			break;
		case 2:
			dong_url = "DongCode=3";
			break;
		case 3:
			dong_url = "DongCode=4";
			break;
		case 4:
			dong_url = "DongCode=5";
			break;
		case 5:
			dong_url = "DongCode=6";
			break;
		case 6:
			dong_url = "DongCode=7";
			break;
		case 7:
			dong_url = "DongCode=8";
			break;
		case 8:
			dong_url = "DongCode=9";
			break;
		case 9:
			dong_url = "DongCode=10";
			break;
		case 10:
			dong_url = "DongCode=11";
			break;
		case 11:
			dong_url = "DongCode=13";
			break;
		case 12:
			dong_url = "DongCode=12";
			break;
		case 13:
			dong_url = "DongCode=14";
			break;
		case 14:
			dong_url = "DongCode=15";
			break;
		case 15:
			dong_url = "DongCode=16";
			break;
		case 16:
			dong_url = "DongCode=17";
			break;

		}
		// 성동구 - 페이지post값 오류
		try {

			Document doc = Jsoup.connect(jsoupUrl + dong_url).timeout(5000).post(); // 웹!!
	
			Elements e = doc.select(".GridViewRow td a");
			Elements contents = e.select("span");

			// if (contents.size() == 0)
			// break;
			for (int i = 0; i < e.size() / 2; i++) {
				HashMap<String, String> data = new HashMap<String, String>();
				String url = e.get(2 * i).attr("href");
				String str = e.get(2 * i + 1).text();

				if (!str.equals("마감")) {

					data.put("lecture", contents.get(i).text());
					data.put("link", click_url + url);

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
