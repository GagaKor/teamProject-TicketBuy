package com.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PerformDao;
import com.dao.PlaceDao;
import com.dao.SchDao;
import com.dao.SeatDao;
import com.dao.TicketDao;
import com.vo.Member;
import com.vo.Perform;
import com.vo.Place;
import com.vo.Sch;

public class BuyTicketCheckAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/usr/perform/buyCheck";

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");

		if (member != null) {
			PerformDao performDao = PerformDao.getInstance();
			SchDao schDao = SchDao.getInstance();
			PlaceDao placeDao = PlaceDao.getInstance();
			TicketDao ticketDao = TicketDao.getInstance();
			SeatDao seatDao = SeatDao.getInstance();

			int schId = Integer.parseInt(request.getParameter("schId"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			if (!(seatDao.getRemainSeat(schId) == 0)) {
				Sch sch = schDao.getSchOne(schId);
				Perform perform = performDao.getPerformOne(sch.getPerformId());
				String categoryName = performDao.getCategoryName(perform.getPerformId());
				Place place = placeDao.getPlaceOne(sch.getPlaceId());
				int price = ticketDao.getTicketPrice(schId);

				request.setAttribute("amount", amount);
				request.setAttribute("sch", sch);
				request.setAttribute("categoryName", categoryName);
				request.setAttribute("place", place);
				request.setAttribute("price", price);
				request.setAttribute("perform", perform);
			} else {
				request.setAttribute("alertMsg", "매진된 일정 입니다.");
				request.setAttribute("historyBack", "historyBack");
				url = "/usr/perform/redirect";
			}
		} else {
			request.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			request.setAttribute("replaceUrl", "/usr/login.do");
			url = "/usr/perform/redirect";
		}

		return new ActionForward(false, url);
	}

}
