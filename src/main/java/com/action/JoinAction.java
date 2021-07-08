package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.vo.Member;

public class JoinAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String url = "/usr/redirect";
		
		Member member =(Member)session.getAttribute("member");
		
		if (member == null) {
			String userid = request.getParameter("userid");
			
			MemberDao memberDao = MemberDao.getInstance();
		
			if(userid == null) {
				List<String> memberId = memberDao.getAllId();
				request.setAttribute("memberId", memberId);
				url = "/usr/joinView";
			}else {
				String userpw = request.getParameter("userpw");
				String phone = request.getParameter("phone");
		
				Member bean = new Member(0, userid, userpw, 1, phone);
				memberDao.insertMember(bean);
				request.setAttribute("alertMsg", "회원가입을 축하합니다.");
				request.setAttribute("replaceUrl", "/usr/login.do");
				
			}
		}else {
			request.setAttribute("alertMsg", "이미 로그인되어 있습니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");
		}
		
		return new ActionForward(false, url);
	}

}
