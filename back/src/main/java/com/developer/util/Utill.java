package com.developer.util;

import javax.servlet.http.HttpSession;

public class Utill {
	
	
	/**
	 * [공통] 로그인 세션 체크 
	 * @param session 현재 세션
	 * @return 로그인 상태값 반환 
	 */
	public int checkLogin(HttpSession session) {
		String logined = (String)session.getAttribute("logined");
		if(logined != null) {
			return 1;
		} else {
			return 0;
		}
	}

}
