package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_gangseo_4 {

	String jsoupUrl = "http://www.gangseo.seoul.kr/site/ccenter/app/c3/page2.jsp?lec_st=A&inst_type=1&", dong_url;
	String click_url = "http://www.gangseo.seoul.kr";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "inst_cd=14";
			break;
		case 1:
			dong_url = "inst_cd=15";
			break;
		case 2:
			dong_url = "inst_cd=16";
			break;
		case 3:
			dong_url = "inst_cd=19";
			break;
		case 4:
			dong_url = "inst_cd=2";
			break;
		case 5:
			dong_url = "inst_cd=3";
			break;
		case 6:
			dong_url = "inst_cd=4";
			break;
		case 7:
			dong_url = "inst_cd=17";
			break;
		case 8:
			dong_url = "inst_cd=20";
			break;
		case 9:
			dong_url = "inst_cd=21";
			break;
		case 10:
			dong_url = "inst_cd=22";
			break;
		case 11:
			dong_url = "inst_cd=1";
			break;
		case 12:
			dong_url = "inst_cd=10";
			break;
		case 13:
			dong_url = "inst_cd=5";
			break;
		case 14:
			dong_url = "inst_cd=6";
			break;
		case 15:
			dong_url = "inst_cd=7";
			break;
		case 16:
			dong_url = "inst_cd=8";
			break;
		case 17:
			dong_url = "inst_cd=9";
			break;
		case 18:
			dong_url = "inst_cd=11";
			break;
		case 19:
			dong_url = "inst_cd=13";
			break;

		}
		// 강서 - while문 돌릴수없음
		try {

			int p = 1, pageNum = 1;
			while (true) {
				Document doc = Jsoup.connect(jsoupUrl + dong_url + "&pn=" + p).timeout(5000).get(); // 웹!!

				// url 확인
				System.out.println(jsoupUrl + dong_url + "&pn=" + p);

				if (p == 1) {
					Elements page = doc.select(".bbsPaging a");
					pageNum = page.size();
					System.out.println(pageNum);
				}
				Elements first = doc.select(".program tbody tr a");
				Elements ing = doc.select("tr td img");
				for (int i = 1; i <= first.size(); i++) {
					String img = ing.get(i * 5 - 2).attr("src");

					if (img.equals("/site/ccenter/images/common/apply_1_btn.gif")) {
						HashMap<String, String> data = new HashMap<String, String>();
						data.put("lecture", first.get(i - 1).text());
						data.put("link", click_url + first.get(i - 1).attr("href"));

						dataList.add(data);

					}

				}
				if (p > pageNum)
					break;
				else
					p++;

			}

		} catch (

		IOException e)

		{

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return dataList;
	}
}
