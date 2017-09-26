package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_seocho_15 {

	String jsoupUrl = "http://www.seocho.go.kr/site/seocho/ex/lecture/List.do?scOrg=", dong_url;
	String click_url = "http://www.seocho.go.kr/site/seocho/ex/lecture/View.do?clIdx=";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "29190000";
			break;
		case 1:
			dong_url = "29180000";
			break;
		case 2:
			dong_url = "29220000";
			break;
		case 3:
			dong_url = "29060000";
			break;
		case 4:
			dong_url = "29070000";
			break;
		case 5:
			dong_url = "29080000";
			break;
		case 6:
			dong_url = "29090000";
			break;
		case 7:
			dong_url = "29100000"; // 반포4동
			break;
		case 8:
			dong_url = "29110000";
			break;
		case 9:
			dong_url = "29120000";
			break;
		case 10:
			dong_url = "29130000";
			break;
		case 11:
			dong_url = "29140000";
			break;
		case 12:
			dong_url = "29150000"; // 방배4동
			break;
		case 13:
			dong_url = "29200000";
			break;
		case 14:
			dong_url = "29230000";
			break;
		case 15:
			dong_url = "29010000";
			break;
		case 16:
			dong_url = "29020000";
			break;
		case 17:
			dong_url = "29030000";
			break;
		case 18:
			dong_url = "29040000";
			break;
		case 19:
			dong_url = "29160000";
			break;
		case 20:
			dong_url = "29170000";
			break;
		case 21:
			dong_url = "29050000";
			break;

		}

		try {
			int n = 1;
			while (true) {
				Document doc = Jsoup.connect(jsoupUrl + dong_url + "&pageIndex=" + n).timeout(5000).get();

				System.out.println(jsoupUrl + dong_url + "&pageIndex=" + n);

				Elements link = doc.select(".board li a");
				Elements contents = doc.select(".title");
				Elements ing = doc.select(".reserve-status");

				int close = 0;
				for (int i = 0; i < link.size(); i++) {
					if (!ing.get(i).text().equals("강좌종료")) {
						HashMap<String, String> data = new HashMap<String, String>();

						StringTokenizer st = new StringTokenizer(link.get(i).attr("href"), "'");
						String s = st.nextToken();
						s = st.nextToken();

						data.put("lecture", contents.get(i).text());
						data.put("link", click_url + s);
						dataList.add(data);
					} else
						close++;

				}
				if (contents.size() == 8 && close < 8)
					n++;
				else
					break;

			}

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList;
	}
}
