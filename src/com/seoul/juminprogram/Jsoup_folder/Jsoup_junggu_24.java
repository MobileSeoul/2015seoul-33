package com.seoul.juminprogram.Jsoup_folder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class Jsoup_junggu_24 {

	String jsoupUrl = "http://community.junggu.seoul.kr/jumin/program/program_list.jsp?page_no=2&top=2&sub=1&",
			dong_url;
	String click_url = "http://community.junggu.seoul.kr";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "dong_on=15";
			break;
		case 1:
			dong_url = "dong_on=7";
			break;
		case 2:
			dong_url = "dong_on=11";
			break;
		case 3:
			dong_url = "dong_on=2";
			break;
		case 4:
			dong_url = "dong_on=1";
			break;
		case 5:
			dong_url = "dong_on=10";
			break;
		case 6:
			dong_url = "dong_on=6";
			break;
		case 7:
			dong_url = "dong_on=8";
			break;
		case 8:
			dong_url = "dong_on=5";
			break;
		case 9:
			dong_url = "dong_on=4";
			break;
		case 10:
			dong_url = "dong_on=13";
			break;
		case 11:
			dong_url = "dong_on=9";
			break;
		case 12:
			dong_url = "dong_on=3";
			break;
		case 13:
			dong_url = "dong_on=12";
			break;
		case 14:
			dong_url = "dong_on=14";
			break;

		}
		// 중구 - 모두1페이지 - 신청가능 정보만 있음
					try {
						int n = 1;
						while (true) {
							Document doc = Jsoup.connect(jsoupUrl + dong_url + "&curPage=" + n).timeout(5000).get(); // 웹!!
							// url 확인
							System.out.println(jsoupUrl + dong_url + "&curPage=" + n);
							Elements contents = doc.select("tbody td.left a");

							if (contents.size() == 0)
								break;
							for (int i = 0; i < contents.size(); i++){
								HashMap<String, String> data = new HashMap<String, String>();
								data.put("lecture", contents.get(i).text());
								data.put("link", click_url+contents.get(i).attr("href"));

								dataList.add(data);

							}
								
							n++;

						}

		
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return dataList;
	}
}
