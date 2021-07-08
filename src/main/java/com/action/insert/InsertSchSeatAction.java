package com.action.insert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.dao.ActorDao;
import com.dao.PerformDao;
import com.dao.PlaceDao;
import com.dao.SchDao;
import com.dao.SeatDao;
import com.vo.Actor;
import com.vo.Member;
import com.vo.Perform;
import com.vo.Place;
import com.vo.Sch;
import com.vo.Seat;

public class InsertSchSeatAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");

		String url = "/usr/perform/redirect";
		if (member.getMemberCode() == 0) {
			url = "/usr/perform/insertTicket";
			SchDao schDao = SchDao.getInstance();
			String[] stime_temp = request.getParameterValues("sTime");
			String[] stime = new String[stime_temp.length - 1];
			for (int i = 0; i < stime.length; i++) {
				stime[i] = stime_temp[i + 1];
			}

			String strDate = request.getParameter("startDate");
			String eDate = request.getParameter("endDate");
			ArrayList<Integer> schidList = new ArrayList<>();// schId 저장용
		
			
			int performId = Integer.parseInt(request.getParameter("performId"));
			int placeId = Integer.parseInt(request.getParameter("placeId"));

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();// 날짜 계산용
			try {

				Date[] schDate = new Date[stime.length];
				for (int i = 0; i < stime.length; i++) {
					schDate[i] = new Date(df.parse(strDate + "\s" + stime[i]).getTime());
				}
				Date startDate = new Date(dt.parse(strDate).getTime());
				Date endDate = new Date(dt.parse(eDate).getTime());
				
				long date = dt.parse(eDate).getTime() - dt.parse(strDate).getTime();
				long calDate = date/(24*60*60*1000);
				calDate = Math.abs(calDate);
				
				for (int i = 0; i < stime.length; i++) {
					cal.setTime(schDate[i]);
					for (int j = 0; j <= calDate; j++) {
						schDao.insertSch(new Sch(0, cal.getTime(), performId, placeId, startDate, endDate));
						schidList.add(schDao.getMaxSchId(performId));
						cal.add(Calendar.DATE, 1);
					}
				}

			} catch (ParseException e) {

				e.printStackTrace();
			}

			// 좌석 정보 등록
			SeatDao seatDao = SeatDao.getInstance();
			PlaceDao placeDao = PlaceDao.getInstance();

			int placeRow = placeDao.getPlaceOne(placeId).getPlaceRow();
			int placeCol = placeDao.getPlaceOne(placeId).getPlaceCol();
			String[] c = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "k", "L" };

			for (int i = 0; i < schidList.size(); i++) {
				int a = 0;
				int b = 1;
				for (int j = 1; j <= placeRow * placeCol; j++) {
					String seatName = c[a] + "-" + b;
					b++;
					if (b > placeRow) {
						a++;
						b = 1;
					}
					seatDao.insertSeat(new Seat(0, seatName, placeId, schidList.get(i), 0));
				}
			}

			PerformDao performDao = PerformDao.getInstance();
			ActorDao actorDao = ActorDao.getInstance();

			Perform perform = performDao.getPerformOne(performId);
			List<Sch> schList = schDao.getSearchSchList(performId);
			Place place = placeDao.getPlaceOne(schList.get(0).getPlaceId());
			List<Actor> actorList = actorDao.getActorList(performId);

			request.setAttribute("perform", perform);
			request.setAttribute("schList", schList);
			request.setAttribute("place", place);
			request.setAttribute("actorList", actorList);
			request.setAttribute("startDate", schList.get(0).getStartDate());
			request.setAttribute("endDate", schList.get(0).getEndDate());

		} else {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "./main.do");
		}
		return new ActionForward(false, url);
	}

}
