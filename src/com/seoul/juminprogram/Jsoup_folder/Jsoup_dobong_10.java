package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_dobong_10 {

	String jsoupUrl = "http://jachi.dobong.go.kr/JUMIN/sub03/sub03_01.aspx?", dong_url;
	String click_url = "http://jachi.dobong.go.kr/JUMIN/sub03/";

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
			dong_url = "DongCode=12";
			break;
		case 12:
			dong_url = "DongCode=13";
			break;
		case 13:
			dong_url = "DongCode=14";
			break;

		}

		try {

			Document doc = Jsoup.connect(jsoupUrl + dong_url).timeout(5000).get(); // 웹!!
			// url 확인
			System.out.println(jsoupUrl + dong_url);

			Elements link = doc.select(".textAlignCenter table td a");
			Elements contents = doc.select("#ContentPlaceHolder1_GridView1 td  span");

			for (int i = 0; i <= link.size() / 2; i++) {
				HashMap<String, String> data = new HashMap<String, String>();

				if (!contents.get(i * 10 + 9).text().equals("마감")) {

					data.put("lecture", contents.get(i * 10 + 3).text());
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
