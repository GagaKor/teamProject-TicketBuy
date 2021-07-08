package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vo.Member;

public class InsertViewAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		String url = "/usr/perform/insertView";
		if (!(member != null && member.getMemberCode()==0)) {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");
			url = "/usr/perform/redirect";
		}
		
		return new ActionForward(false, url);
	}

}
