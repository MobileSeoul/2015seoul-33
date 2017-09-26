package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_eunpyeong_22 {

	String jsoupUrl = "http://center.ep.go.kr/CmsWeb/viewPage.req?idx=PG0000000631&", dong_url;
	String click_url = "http://center.ep.go.kr";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "flag=605";
			break;
		case 1:
			dong_url = "flag=606";
			break;
		case 2:
			dong_url = "flag=607";
			break;
		case 3:
			dong_url = "flag=601";
			break;
		case 4:
			dong_url = "flag=608";
			break;
		case 5:
			dong_url = "flag=602";
			break;
		case 6:
			dong_url = "flag=603";
			break;
		case 7:
			dong_url = "flag=618";
			break;
		case 8:
			dong_url = "flag=615";
			break;
		case 9:
			dong_url = "flag=616";
			break;
		case 10:
			dong_url = "flag=623";
			break;
		case 11:
			dong_url = "flag=609";
			break;
		case 12:
			dong_url = "flag=610";
			break;
		case 13:
			dong_url = "flag=611";
			break;
		case 14:
			dong_url = "flag=617";
			break;
		case 15:
			dong_url = "flag=622";
			break;

		}
		// 용산구 - 모두1페이지 - 마감 아닌 정보 읽어옴
		try {
			Document doc = Jsoup.connect(jsoupUrl + dong_url).timeout(5000).get(); // 웹!!
			// url 확인
			System.out.println(jsoupUrl + dong_url);

			Elements contents = doc.select("tbody a");
			Elements e = doc.select("tbody b");

			for (int i = 0; i < e.size(); i++) {
				HashMap<String, String> data = new HashMap<String, String>();
				String ing = e.get(i).text();
				String str = contents.get(i * 2 + 1).text();
				String url = contents.get(i * 2 + 1).attr("href");

				if (!ing.equals("접수마감")) {

					data.put("lecture", str);
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
