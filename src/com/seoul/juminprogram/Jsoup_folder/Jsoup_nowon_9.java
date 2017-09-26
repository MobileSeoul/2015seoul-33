package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_nowon_9 {

	String jsoupUrl = "http://lll.nowon.kr/lms/lecture/l_lst.jsp?school_fee=-1&inPeriod=1&", dong_url;
	String click_url = "http://lll.nowon.kr/lms/menu.jsp?org_seq=146&at_cd=l_lecture_viw&mno=0_0&term_seq=1&edu_insti_seq=0&num=&org_type=null&open_course_seq=";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "org_seq=146";
			break;
		case 1:
			dong_url = "org_seq=147";
			break;
		case 2:
			dong_url = "org_seq=155";
			break;
		case 3:
			dong_url = "org_seq=156";
			break;
		case 4:
			dong_url = "org_seq=157";
			break;
		case 5:
			dong_url = "org_seq=158";
			break;
		case 6:
			dong_url = "org_seq=159";
			break;
		case 7:
			dong_url = "org_seq=160";
			break;
		case 8:
			dong_url = "org_seq=161";
			break;
		case 9:
			dong_url = "org_seq=162";
			break;
		case 10:
			dong_url = "org_seq=141";
			break;
		case 11:
			dong_url = "org_seq=142";
			break;
		case 12:
			dong_url = "org_seq=144";
			break;
		case 13:
			dong_url = "org_seq=154";
			break;
		case 14:
			dong_url = "org_seq=150";
			break;
		case 15:
			dong_url = "org_seq=151";
			break;
		case 16:
			dong_url = "org_seq=153";
			break;
		case 17:
			dong_url = "org_seq=148";
			break;
		case 18:
			dong_url = "org_seq=149";
			break;
		}
		// 노원 - 페이지 돌면서 신청중 정보만 가져오기
		try {

			int n = 1;
			while (true) {
				Document doc = Jsoup.connect(jsoupUrl + dong_url + "&page=" + n).timeout(5000).get(); // 웹!!
				// url 확인
				System.out.println(jsoupUrl + dong_url);

				Elements contents = doc.select(".pd_l10 a");
				Elements page = doc.select(".lst img ");

				if (contents.size() == 0)
					break;
				for (int i = 1; i <= contents.size(); i++) {
					if (!page.get(i * 5 - 2).attr("src").equals("/lms_img/btn/btn_app_end.gif")) {
						HashMap<String, String> data = new HashMap<String, String>();

						data.put("lecture", contents.get(i - 1).text());

						// 문자열자르기
						String str = contents.get(i - 1).attr("href");
						StringTokenizer st = new StringTokenizer(str, ",");
						str = st.nextToken();// 쓰레기값
						str = st.nextToken();
						String link = st.nextToken();
						str = st.nextToken();

						StringTokenizer stz = new StringTokenizer(link, "'");
						str = stz.nextToken(); // 쓰레기값
						link = stz.nextToken();

						data.put("link", click_url + link);

						dataList.add(data);
					}

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
