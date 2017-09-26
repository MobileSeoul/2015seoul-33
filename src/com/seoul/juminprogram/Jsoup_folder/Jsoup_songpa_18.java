package com.seoul.juminprogram.Jsoup_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsoup_songpa_18 {

	String jsoupUrl = "http://lll.songpa.go.kr/user.kdf?a=songpa.eduportal.lecture.LectureServiceApp&c=1004&menu_type=&el_seq=20063&el_edu_gubun=B&dong_code=&r_page=2&sh_el_edu_begin=2014-11-01&sh_el_edu_end=2015-12-31&sh_ea_id=seokchon&sh_name_search_text=&sh_name_search=L&sh_el_stat=&sh_el_type=01",
			dong_url;
	String click_url = "http://lll.songpa.go.kr/lms.jsp?act=course_lst&mno=2_202";

	public List<HashMap<String, String>> parse(int spinnerNum) {

		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		return dataList;
	}
}
