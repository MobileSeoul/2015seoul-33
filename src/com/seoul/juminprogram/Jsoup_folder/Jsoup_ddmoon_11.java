package com.seoul.juminprogram.Jsoup_folder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_ddmoon_11 {

	String jsoupUrl = "http://www.ddm.go.kr/education/satProgramDDm.jsp?page=", dong_url;

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		switch (spinnerNum) {

		case 1:
			dong_url = "답십리제1동";
			break;
		case 2:
			dong_url = "답십리제2동";
			break;
		case 3:
			dong_url = "용신동";
			break;
		case 4:
			dong_url = "이문제1동";
			break;
		case 5:
			dong_url = "이문제2동";
			break;
		case 6:
			dong_url = "장안제1동";
			break;
		case 7:
			dong_url = "장안제2동";
			break;
		case 8:
			dong_url = "전농제1동";
			break;
		case 9:
			dong_url = "전농제2동";
			break;
		case 10:
			dong_url = "제기동";
			break;
		case 11:
			dong_url = "청량리동";
			break;
		case 12:
			dong_url = "회기동";
			break;
		case 13:
			dong_url = "휘경제1동";
			break;
		case 14:
			dong_url = "휘경제2동";
			break;

		}
		// 동대문구 - jsp.post방식 클릭페이지없음 - 전부 신청가능
		try {
			int n = 1;
			while (true) {
				Document doc = Jsoup.connect(jsoupUrl + n).data("__EVENTTARGET", "ctl00$ContentPlaceHolder1$GridView1")
						.data("dept", dong_url).timeout(5000).post();
				// url 확인
				System.out.println(jsoupUrl + n);

				Elements sub = doc.select(".tac b");
				Elements contents = doc.select(".tac");

				StringTokenizer st;
				for (int i = 0; i < sub.size(); i++) {

					String plc = "", phone = "";
					HashMap<String, String> data = new HashMap<String, String>();

					st = new StringTokenizer(contents.get(i * 7).text(), " ");

					for (int t = 0; t <= st.countTokens(); t++) {
						plc = st.nextToken();
					}
					st = new StringTokenizer(contents.get(i * 7 + 5).text(), " ");

					for (int t = 0; t <= st.countTokens(); t++) {
						phone = st.nextToken();
					}
					data.put("lecture", sub.get(i).text());
					data.put("link", sub.get(i).text() + "^" + plc + "^" + contents.get(i * 7 + 2).text() + "^"
							+ contents.get(i * 7 + 4).text() + "^" + phone);

					dataList.add(data);

					System.out.println(sub.get(i).text() + "^" + plc + "^" + contents.get(i * 7 + 2).text() + "^"
							+ contents.get(i * 7 + 4).text() + "^" + phone);

				}
				if (sub.size() == 10)
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
