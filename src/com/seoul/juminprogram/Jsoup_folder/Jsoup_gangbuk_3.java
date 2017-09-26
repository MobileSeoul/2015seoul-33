package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_gangbuk_3 {

	String jsoupUrl = "http://center.gangbuk.go.kr/index.aspx?", dong_url;
	String click_url = "http://center.gangbuk.go.kr";
	int today;

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		Calendar cal = Calendar.getInstance();
		today = cal.get(Calendar.DATE);

		switch (spinnerNum) {
		case 0:
			dong_url = "cate=D003";
			break;
		case 1:
			dong_url = "cate=D010";
			break;
		case 2:
			dong_url = "cate=D011";
			break;
		case 3:
			dong_url = "cate=D012";
			break;
		case 4:
			dong_url = "cate=D007";
			break;
		case 5:
			dong_url = "cate=D002";
			break;
		case 6:
			dong_url = "cate=D001";
			break;
		case 7:
			dong_url = "cate=D004";
			break;
		case 8:
			dong_url = "cate=D009";
			break;
		case 9:
			dong_url = "cate=D005";
			break;
		case 10:
			dong_url = "cate=D008";
			break;
		case 11:
			dong_url = "cate=D013";
			break;
		case 12:
			dong_url = "cate=D014";
			break;
		case 13:
			dong_url = "cate=D015";
			break;
		case 14:
			dong_url = "cate=D016";
			break;
		case 15:
			dong_url = "cate=D017";
			break;
		case 16:
			dong_url = "cate=D018";
			break;
		}
		// 강북구 - 26일~말일에만 신청가능
		try {

			if (today > 25 && today < 32) {
				for (int page = 0; page < 10; page++) {

					Document doc = Jsoup.connect(jsoupUrl + dong_url + "&nPage=" + page).timeout(5000).get(); // 웹!!
					// url 확인
					System.out.println(jsoupUrl + dong_url + "&nPage=" + page);

					Elements contents = doc.select(".edu_ta a");
					Elements ing = doc.select(".how_join");

					for (int i = 0; i < contents.size(); i++) {
						if (!ing.get(i).text().equals("접수마감")) {
							HashMap<String, String> data = new HashMap<String, String>();
							data.put("lecture", contents.get(i).text());
							data.put("link", click_url + contents.get(i).attr("href"));

							dataList.add(data);
					}

					}

				}
			} else {
				HashMap<String, String> data = new HashMap<String, String>();
				data.put("lecture", "강좌 접수는 분기별(3개월단위)로 합니다.\n" + "-12월 26일 ~ 12월 말일 접수 --->>>수강은 1, 2, 3월 31일까지\n"
						+ "- 3월 26일 ~ 3월 말일 접수 --->>>수강은 4, 5, 6월 30일까지\n"
						+ "- 6월 26일 ~ 6월 말일 접수 --->>>수강은 7, 8, 9월 30일까지\n"
						+ "- 9월 26일 ~ 9월 말일 접수 --->>>수강은 10, 11, 12월 31일까지\n" + "※ 단, 분기중 접수는 매월 26일 ~ 말일 입니다\n");

				dataList.add(data);
			}

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return dataList;
	}
}
