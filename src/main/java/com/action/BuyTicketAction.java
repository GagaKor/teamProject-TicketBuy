package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ReservationDao;
import com.dao.SeatDao;
import com.dao.TicketDao;
import com.vo.Member;
import com.vo.Reservation;
import com.vo.Seat;

public class BuyTicketAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/usr/perform/buyTicket";
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		if(member != null) {
		TicketDao ticketDao = TicketDao.getInstance();
		SeatDao seatDao = SeatDao.getInstance();
		ReservationDao reservationDao = ReservationDao.getInstance();
		
		int schId = Integer.parseInt(request.getParameter("schId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		List<Seat> seatList = seatDao.getRemainSeatList(schId);
		List<Integer> reIdList = new ArrayList<Integer>();
		for (int i = 0; i < amount; i++) {
			seatDao.updateIssue(seatList.get(i).getSeatId());
			int ticketId = ticketDao.getTicketId(seatList.get(i).getSeatId());
			reservationDao.insertReservation(new Reservation(0, member.getIdx(), ticketId));
			reIdList.add(reservationDao.getTicketIdToReservationNo(ticketId));
		}
		request.setAttribute("reIdList", reIdList);
		}else {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "usr/main.do");
			url = "/usr/perform/redirect";
		}
		

		return new ActionForward(false, url);
	}

}
