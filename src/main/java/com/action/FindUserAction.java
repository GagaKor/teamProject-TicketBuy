package com.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.vo.Member;

public class FindUserAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String url = "/usr/redirect";

		Member member = (Member) session.getAttribute("member");

		if (member == null) {
			String phone = request.getParameter("phone");
			if (phone == null) {
				url = "/usr/findUser";
			} else {
				MemberDao memberDao = MemberDao.getInstance();
				Member userInfo = memberDao.findUser(phone);
				if (userInfo == null) {
					request.setAttribute("alertMsg", "회원정보가 존재하지 않습니다.");
					request.setAttribute("replaceUrl", "/usr/findUser.do");
					request.setAttribute("f", "");
				} else {
					url = "/usr/findUser";
					request.setAttribute("memberId", userInfo.getUserid());
					request.setAttribute("memberPw", userInfo.getUserpw());
					request.setAttribute("f", "rs");
					
				}
			}
		} else {
			request.setAttribute("alertMsg", "이미 로그인되어 있습니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");
		}

		return new ActionForward(false, url);
	}

}
