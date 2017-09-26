package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_yeongdpo_20 {

	String jsoupUrl = "http://www.ydp.go.kr/jumin/edu/program.do?mCode=D030010000&", dong_url;
	String click_url = "http://www.ydp.go.kr";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "dongNm=%B4%E7%BB%EA1%B5%BF";
			break;
		case 1:
			dong_url = "dongNm=%B4%E7%BB%EA2%B5%BF";
			break;
		case 2:
			dong_url = "dongNm=%B4%EB%B8%B21%B5%BF";
			break;
		case 3:
			dong_url = "dongNm=%B4%EB%B8%B22%B5%BF";
			break;
		case 4:
			dong_url = "dongNm=%B4%EB%B8%B23%B5%BF";
			break;
		case 5:
			dong_url = "dongNm=%B5%B5%B8%B2%B5%BF";
			break;
		case 6:
			dong_url = "dongNm=%B9%AE%B7%A1%B5%BF";
			break;
		case 7:
			dong_url = "dongNm=%BD%C5%B1%E61%B5%BF";
			break;
		case 8:
			dong_url = "dongNm=%BD%C5%B1%E63%B5%BF";
			break;
		case 9:
			dong_url = "dongNm=%BD%C5%B1%E64%B5%BF";
			break;
		case 10:
			dong_url = "dongNm=%BD%C5%B1%E65%B5%BF";
			break;
		case 11:
			dong_url = "dongNm=%BD%C5%B1%E66%B5%BF";
			break;
		case 12:
			dong_url = "dongNm=%BD%C5%B1%E67%B5%BF";
			break;
		case 13:
			dong_url = "dongNm=%BE%E7%C6%F21%B5%BF";
			break;
		case 14:
			dong_url = "dongNm=%BE%E7%C6%F22%B5%BF";
			break;
		case 15:
			dong_url = "dongNm=%BF%A9%C0%C7%B5%BF";
			break;
		case 16:
			dong_url = "dongNm=%BF%B5%B5%EE%C6%F7%B5%BF";
			break;
		case 17:
			dong_url = "dongNm=%BF%B5%B5%EE%C6%F7%BA%BB%B5%BF";
			break;

		}
		// 영등포구 - 접수마감 제외한 정보 읽어오기
		try {
			Document doc = Jsoup.connect(jsoupUrl + dong_url).timeout(5000).get(); // 웹!!
			// url 확인
			System.out.println(jsoupUrl + dong_url);

			Elements num = doc.select(".ta12l a");
			Elements contents = doc.select(".ta12c");

			for (int i = 1; i <= num.size(); i++) {
				HashMap<String, String> data = new HashMap<String, String>();

				String str = contents.get(i * 7 - 1).text();

				if (str.length() > 0) {
					data.put("lecture", num.get(i - 1).text());
					data.put("link", click_url + num.get(i - 1).attr("href"));

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
