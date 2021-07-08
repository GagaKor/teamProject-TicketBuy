package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PerformDao;
import com.dao.PlaceDao;
import com.dao.ReservationDao;
import com.dao.SchDao;
import com.dao.TicketDao;
import com.vo.Member;
import com.vo.MyTicket;
import com.vo.Perform;
import com.vo.Place;
import com.vo.Reservation;
import com.vo.Sch;
import com.vo.Ticket;

public class myTicketAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String url = "/usr/redirect";
		Member member = (Member) session.getAttribute("member");
		if (member != null) {
			url = "/usr/myTicket";
			ReservationDao reservationDao = ReservationDao.getInstance();
			TicketDao ticketDao = TicketDao.getInstance();
			PerformDao performDao = PerformDao.getInstance();
			SchDao schDao = SchDao.getInstance();
			PlaceDao placeDao = PlaceDao.getInstance();
			List<Reservation> resList = reservationDao.getAllReservationNo(member.getIdx());
			if (resList == null) {
				request.setAttribute("em", "");
			} else {
				List<MyTicket> list = new ArrayList<>();
				for (int i = 0; i < resList.size(); i++) {
					Ticket ticket = ticketDao.getTicketOne(resList.get(i).getTicketId());
					int performId = schDao.getSchOne(ticket.getSchId()).getPerformId();

					int reservationNo = resList.get(i).getReservationNo();
					Perform perform = performDao.getPerformOne(performId);
					String categoryName = performDao.getCategoryName(performId);
					Sch sch = schDao.getSchOne(ticket.getSchId());
					Place place = placeDao.getPlaceOne(ticket.getPlaceId());
					list.add(new MyTicket(reservationNo, perform, categoryName, sch, place));
				}
				request.setAttribute("list", list);
			}
		} else {
			request.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			request.setAttribute("replaceUrl", "/usr/login.do");
		}
		return new ActionForward(false, url);
	}

}
