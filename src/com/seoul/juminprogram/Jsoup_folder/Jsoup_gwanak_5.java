package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_gwanak_5 {

	String jsoupUrl = "http://www.gwanak.go.kr/servlets/rnl/gwanak/edu/lecture/service/LectureServlet?cmd=LIST&menu_no=0100096&srh_title=&edu_type=&class_type=&class_who=&apay_yn=&amethod=&write_yn=&",
			dong_url;
	String click_url = "http://www.gwanak.go.kr/servlets/rnl/gwanak/edu/lecture/service/LectureServlet?cmd=VIEW&list_seq=";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {
		case 0:
			dong_url = "srh_post_code=7080";
			break;
		case 1:
			dong_url = "srh_post_code=7270";
			break;
		case 2:
			dong_url = "srh_post_code=7210";
			break;
		case 3:
			dong_url = "srh_post_code=7130";
			break;
		case 4:
			dong_url = "srh_post_code=7230";
			break;
		case 5:
			dong_url = "srh_post_code=7260";
			break;
		case 6:
			dong_url = "srh_post_code=7020";
			break;
		case 7:
			dong_url = "srh_post_code=7200";
			break;
		case 8:
			dong_url = "srh_post_code=7160";
			break;
		case 9:
			dong_url = "srh_post_code=7140";
			break;
		case 10:
			dong_url = "srh_post_code=7060";
			break;
		case 11:
			dong_url = "srh_post_code=7190";
			break;
		case 12:
			dong_url = "srh_post_code=7180";
			break;
		case 13:
			dong_url = "srh_post_code=7150";
			break;
		case 14:
			dong_url = "srh_post_code=7010";
			break;
		case 15:
			dong_url = "srh_post_code=7120";
			break;
		case 16:
			dong_url = "srh_post_code=7220";
			break;
		case 17:
			dong_url = "srh_post_code=7110";
			break;
		case 18:
			dong_url = "srh_post_code=7090";
			break;
		case 19:
			dong_url = "srh_post_code=7040";
			break;
		case 20:
			dong_url = "srh_post_code=7070";
			break;
		}

		try {

			int n = 1;
			while (true) {

				Document doc = Jsoup.connect(jsoupUrl + dong_url + "&pageNum=" + n).timeout(5000).get();
				// url 확인
				System.out.println(jsoupUrl + dong_url + "&pageNum=" + n);
				Elements contents = doc.select(".al_l a");

				if (contents.size() == 0)
					break;
				for (int i = 0; i < contents.size(); i++) {

					HashMap<String, String> data = new HashMap<String, String>();
					data.put("lecture", contents.get(i).text());

					// 문자열자르기
					String str = contents.get(i).attr("href");

					StringTokenizer st = new StringTokenizer(str, "'");
					str = st.nextToken(); // 쓰레기값
					String link2 = st.nextToken();
					str = st.nextToken();

					data.put("link", click_url + link2);

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
