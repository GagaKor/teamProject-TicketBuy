package com.action.insert;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.dao.PlaceDao;
import com.dao.SchDao;
import com.dao.SeatDao;
import com.dao.TicketDao;
import com.vo.Member;
import com.vo.Place;
import com.vo.Sch;
import com.vo.Seat;
import com.vo.Ticket;

public class InsertTicket implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		if (member.getMemberCode() == 0) {
			SchDao schDao = SchDao.getInstance();
			SeatDao seatDao = SeatDao.getInstance();
			PlaceDao placeDao = PlaceDao.getInstance();
			TicketDao tDao = TicketDao.getInstance();

			int performId = Integer.parseInt(request.getParameter("performId"));
			int ticketPrice = Integer.parseInt(request.getParameter("ticketPrice"));

			List<Sch> schList = schDao.getSearchSchList(performId);
			Place place = placeDao.getPlaceOne(schList.get(0).getPlaceId());

			for (int i = 0; i < schList.size(); i++) {
				List<Seat> seatList = seatDao.getSeatList(schList.get(i).getSchId());
				for (int j = 0; j < seatList.size(); j++) {
					tDao.insertTicket(new Ticket(0, schList.get(i).getSchId(), place.getPlaceId(),
							seatList.get(j).getSeatId(), ticketPrice));
				}
				request.setAttribute("replaceUrl", "/usr/main.do");
			}
		} else {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");
		}

		return new ActionForward(false, "/usr/perform/redirect");
	}

}
