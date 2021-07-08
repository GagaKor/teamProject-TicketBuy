package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vo.Member;

public class LogoutAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		Member member = (Member) session.getAttribute("member");
		if (member == null) {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");
		}else {
			session.invalidate();
			request.setAttribute("alertMsg", member.getUserid()+"님 로그아웃 되었습니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");
		}
		
		return new ActionForward(false, "/usr/redirect");
	}

}
