package com.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ActorDao;
import com.dao.PerformDao;
import com.dao.SchDao;
import com.dao.TicketDao;
import com.vo.Actor;
import com.vo.Member;
import com.vo.Perform;
import com.vo.Sch;

public class UpdateViewAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {	
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		String url ="/usr/perform/updateView";
		
		if (member != null && member.getMemberCode() == 0) {
		
		PerformDao performDao = PerformDao.getInstance();
		ActorDao actorDao = ActorDao.getInstance();
		SchDao schDao = SchDao.getInstance();
		TicketDao ticketDao = TicketDao.getInstance();
		
		int performId = Integer.parseInt(request.getParameter("performId"));
		int placeId = Integer.parseInt(request.getParameter("placeId"));
		
		Perform perform = performDao.getPerformOne(performId);
		List<Sch> schList = schDao.getSearchSchList(performId);
		String seasonDate = schDao.getSeasonDate(performId);
		List<Date> schDate = schDao.getschTimeList(performId);
		int ticketPrice =  ticketDao.getTicketPrice(schList.get(0).getSchId());
		List<Actor> actorList = actorDao.getActorList(performId);
		request.setAttribute("actorList", actorList);
		request.setAttribute("perform", perform);
		request.setAttribute("placeId", placeId);
		request.setAttribute("schList", schList);
		request.setAttribute("seasonDate", seasonDate);
		request.setAttribute("schDate", schDate);
		request.setAttribute("ticketPrice", ticketPrice);
		}else {
				request.setAttribute("alertMsg", "잘못된 접근입니다.");
				request.setAttribute("replaceUrl", "/usr/main.do");
				url = "/usr/perform/redirect";
		}
		return new ActionForward(false, url);
	}

}