package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_geumcheon_8 {
	String jsoupUrl;
	String click_url = "http://gasan.geumcheon.go.kr/";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			jsoupUrl = "http://gasan.geumcheon.go.kr/program/reservation/setL.jsp?menuID=025003004&subMode=lecture&reserveGroupID=5";
			break;
		case 1:
			jsoupUrl = "http://doksan1.geumcheon.go.kr/program/reservation/setL.jsp?menuID=026003004&subMode=lecture&reserveGroupID=19";
			break;
		case 2:
			jsoupUrl = "http://doksan2.geumcheon.go.kr/program/reservation/setL.jsp?menuID=027003004&subMode=lecture&reserveGroupID=20";
			break;
		case 3:
			jsoupUrl = "http://doksan3.geumcheon.go.kr/program/reservation/setL.jsp?menuID=028003004&subMode=lecture&reserveGroupID=21";
			break;
		case 4:
			jsoupUrl = "http://doksan4.geumcheon.go.kr/program/reservation/setL.jsp?menuID=029003004&subMode=lecture&reserveGroupID=22";
			break;
		case 5:
			jsoupUrl = "http://siheung1.geumcheon.go.kr/program/reservation/setL.jsp?menuID=030003004&subMode=lecture&reserveGroupID=23";
			break;
		case 6:
			jsoupUrl = "http://siheung2.geumcheon.go.kr/program/reservation/setL.jsp?menuID=031003004&subMode=lecture&reserveGroupID=24";
			break;
		case 7:
			jsoupUrl = "http://siheung3.geumcheon.go.kr/program/reservation/setL.jsp?menuID=032003004&subMode=lecture&reserveGroupID=25";
			break;
		case 8:
			jsoupUrl = "http://siheung4.geumcheon.go.kr/program/reservation/setL.jsp?menuID=033003004&subMode=lecture&reserveGroupID=26";
			break;
		case 9:
			jsoupUrl = "http://siheung5.geumcheon.go.kr/program/reservation/setL.jsp?menuID=034003004&subMode=lecture&reserveGroupID=27";
			break;
		}
		// 금천 - 마감 없음 - 페이지로 전부 표시
		try {
			int n = 1;
			while (true) {
				Document doc = Jsoup.connect(jsoupUrl + "&pageSize=10&currentPage=" + n).timeout(5000).get(); // 웹!!
				// url 확인
				System.out.println(jsoupUrl + "&pageSize=10&currentPage=" + n);

				Elements e = doc.select("div.tbl-ctd");
				Elements contents = e.select("strong");
				Elements link = e.select("a");

				if (contents.size() == 0)
					break;
				for (int i = 0; i < contents.size(); i++) {

					HashMap<String, String> data = new HashMap<String, String>();
					data.put("lecture", contents.get(i).text());
					data.put("link", click_url + link.get(i).attr("href"));

					dataList.add(data);

					System.out.println(i + "--------" + link.get(i).attr("href"));
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
