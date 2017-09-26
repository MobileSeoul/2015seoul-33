package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_jungnang_25 {

	String jsoupUrl = "http://community.jungnang.go.kr/commnew/app/commnewApp/list.do?searchCnd=1&division=commnew&programId=commnewApp&menuNo=1600008&learnOrg=",
			dong_url;
	String click_url = "http://community.jungnang.go.kr/";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "13";
			break;
		case 1:
			dong_url = "14";
			break;
		case 2:
			dong_url = "01";
			break;
		case 3:
			dong_url = "02";
			break;
		case 4:
			dong_url = "03";
			break;
		case 5:
			dong_url = "04";
			break;
		case 6:
			dong_url = "05";
			break;
		case 7:
			dong_url = "06";
			break;
		case 8:
			dong_url = "11";
			break;
		case 9:
			dong_url = "12";
			break;
		case 10:
			dong_url = "07";
			break;
		case 11:
			dong_url = "08";
			break;
		case 12:
			dong_url = "15";
			break;
		case 13:
			dong_url = "16";
			break;
		case 14:
			dong_url = "09";
			break;
		case 15:
			dong_url = "10";
			break;
		}
		// 중랑구 - 신청가능순으로 정렬되있음.
		try {

			int n = 1;
			while (n > 0) {
				Document doc = Jsoup.connect(jsoupUrl + dong_url + "&pageIndex=" + n).timeout(5000).get();
				// url 확인
				System.out.println(jsoupUrl + dong_url + "&pageIndex=" + n);

				Elements contents = doc.select(".boardlist td a");
				Elements ing = doc.select(".boardlist td img");
				if (contents.size() == 0)
					break;
				for (int i = 0; i < ing.size(); i++) {

					HashMap<String, String> data = new HashMap<String, String>();

					if (!ing.get(i).attr("alt").equals("마감")) {
						System.out.println(ing.get(i).attr("alt"));
						data.put("lecture", contents.get(i).text());
						data.put("link", click_url + contents.get(i).attr("href"));

						dataList.add(data);
					} else {
						n = -100;
					}

				}
				if (ing.size() < 10)
					break;
				n++;
			}

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return dataList;
	}
}
