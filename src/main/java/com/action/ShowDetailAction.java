package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ActorDao;
import com.dao.PerformDao;
import com.dao.PlaceDao;
import com.dao.SchDao;
import com.dao.SeatDao;
import com.dao.TicketDao;
import com.vo.Actor;
import com.vo.Detail;
import com.vo.Member;
import com.vo.Perform;
import com.vo.Place;
import com.vo.Sch;
import com.vo.Seat;

public class ShowDetailAction implements Action {
//모든 일정 리스트  -> 디테일 ->삭제 수정 예정
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PerformDao performDao = PerformDao.getInstance();
		ActorDao actorDao = ActorDao.getInstance();
		SchDao schDao = SchDao.getInstance();
		PlaceDao placeDao = PlaceDao.getInstance();
		SeatDao seatDao = SeatDao.getInstance();
		TicketDao ticketDao = TicketDao.getInstance();

		String url = "/usr/perform/detail";

		int performId = Integer.parseInt(request.getParameter("performId"));
		int placeId = Integer.parseInt(request.getParameter("placeId"));
		Perform perform = performDao.getPerformOne(performId);
		String categoryName = performDao.getCategoryName(performId);
		List<Actor> actorList = actorDao.getActorList(performId);
		Place place = placeDao.getPlaceOne(placeId);
		String seasonDate = schDao.getSeasonDate(performId);
		List<Sch> schList = schDao.getSearchSchList(performId);

		List<List<Seat>> seatList = new ArrayList<>();
		List<Integer> remainSeat = new ArrayList<>();
		int schMaxId = schDao.getMaxSchId(performId);
		int price = ticketDao.getTicketPrice(schMaxId);
		// 해당 공연의 가격을 가져오기 위해서 perform, sch 공통인 schId를 TicketPrice에 넣어준다.

		for (int i = 0; i < schList.size(); i++) {
			int schId = schList.get(i).getSchId();
			List<Seat> seat = seatDao.getSeatList(schId);
			seatList.add(seat);
			remainSeat.add(seatDao.getRemainSeat(schId));
		}

		Detail detail = new Detail(perform, categoryName, actorList, place, seasonDate, price, schList, seatList,
				remainSeat);
		request.setAttribute("detail", detail);
		
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		String adm = request.getParameter("adm");
		if (member != null && member.getMemberCode() == 0 && adm.equals("y") ) {
			url = "/usr/perform/adminDetail";
		}

		return new ActionForward(false, url);
	}

}
