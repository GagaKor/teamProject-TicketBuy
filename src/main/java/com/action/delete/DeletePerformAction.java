package com.action.delete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.dao.PerformDao;
import com.vo.Member;

public class DeletePerformAction implements Action {
//모든 일정 리스트  -> 디테일 ->삭제 수정 예정
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {	
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		if (member != null && member.getMemberCode() == 0) {
		
		PerformDao performDao = PerformDao.getInstance();
		
		int performId = Integer.parseInt(request.getParameter("performId"));
		performDao.deletePerfrom(performId);
		
		request.setAttribute("alertMsg", "해당 공연이 삭제되었습니다.");
		request.setAttribute("replaceUrl", "/usr/main.do");
		}else {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");
			
		}
		
		return new ActionForward(false, "/usr/perform/redirect");
	}

}
