package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_gangnam_1 {
	String click_url = "http://gfac.gncity.or.kr/lecture/lect01_view.aspx?pgmseq=";
	String jsoupUrl = "http://gfac.gncity.or.kr/lecture/lecture01.aspx?classroom=all&center=", dong_url;

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {

		case 0:
			dong_url = "100012";
			break;
		case 1:
			dong_url = "100002";
			break;
		case 2:
			dong_url = "100004";
			break;
		case 3:
			dong_url = "100010";
			break;
		case 4:
			dong_url = "100008";
			break;
		case 5:
			dong_url = "100011";
			break;
		case 6:
			dong_url = "100009";
			break;
		case 7:
			dong_url = "100042";
			break;
		case 8:
			dong_url = "100017";
			break;
		case 9:
			dong_url = "100006";
			break;
		case 10:
			dong_url = "100007";
			break;
		case 11:
			dong_url = "100013";
			break;
		case 12:
			dong_url = "100041";
			break;
		case 13:
			dong_url = "100034";
			break;
		case 14:
			dong_url = "100015";
			break;
		case 15:
			dong_url = "100016";
			break;
		case 16:
			dong_url = "100005";
			break;
		case 17:
			dong_url = "100003";
			break;
		}

		//강남구 - 1페이지
		try {
			Document doc = Jsoup.connect(jsoupUrl + dong_url).timeout(5000).get(); // 웹!!
			// url 확인
			System.out.println(jsoupUrl + dong_url);

			Elements e = doc.select("tbody");
			Elements link = e.select("input");
			Elements contents = e.select("h3");
			System.out.println(contents.size());

			// i=1시작(첫번째 값 문화센터명)
			for (int i = 1; i < contents.size(); i++) {
				HashMap<String, String> data = new HashMap<String, String>();
				data.put("lecture", contents.get(i).text());
				data.put("link", click_url + link.get(i).attr("value"));

				dataList.add(data);
			}
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return dataList;
	}
}
