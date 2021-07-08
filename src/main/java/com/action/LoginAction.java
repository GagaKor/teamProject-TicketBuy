package com.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.vo.Member;

public class LoginAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String url = "/usr/redirect";
		
		Member member =(Member)session.getAttribute("member");
		
		if (member == null) {
			String userid = request.getParameter("userid");
			String userpw = request.getParameter("userpw");
		
			if(userid == null) {
				url =  "/usr/loginView";
			}else {
				//로그인 
				MemberDao memberDao = MemberDao.getInstance();
				member = memberDao.login(userid, userpw);
				if(member == null) {
					request.setAttribute("alertMsg", "아이디 또는 비밀번호를 확인해 주세요.");
					request.setAttribute("replaceUrl", "/usr/login.do");	
				}else {
				session.setAttribute("member", member);
				
				request.setAttribute("alertMsg", member.getUserid()+"님 환영합니다.");
				request.setAttribute("replaceUrl", "/usr/main.do");
				}
			}
		}else {
			request.setAttribute("alertMsg", "이미 로그인되어 있습니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");
		}
		
		return new ActionForward(false, url);
	}

}
