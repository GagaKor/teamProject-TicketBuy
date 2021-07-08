package com.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vo.Actor;
import com.vo.Member;
import com.vo.Perform;
import com.vo.Sch;
import com.vo.Seat;
import com.vo.Ticket;

public class UpdatePerformAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");

		if (member != null && member.getMemberCode() == 0) {

			PerformDao performDao = PerformDao.getInstance();
			ActorDao actorDao = ActorDao.getInstance();
			SchDao schDao = SchDao.getInstance();
			PlaceDao placeDao = PlaceDao.getInstance();
			SeatDao seatDao = SeatDao.getInstance();
			TicketDao ticketDao = TicketDao.getInstance();

			String path = request.getServletContext().getRealPath("/") +"static/img";
			int size = 10 * 1024 * 1024;

			MultipartRequest multi_request = new MultipartRequest(request, path, size, "UTF-8",
					new DefaultFileRenamePolicy());

			int performId = Integer.parseInt(multi_request.getParameter("performId"));
			int placeId = Integer.parseInt(multi_request.getParameter("placeId"));
			int[] schIdArr = Arrays.stream(multi_request.getParameterValues("schId")).mapToInt(Integer::parseInt)
					.toArray();
			int[] actorIdArr = Arrays.stream(multi_request.getParameterValues("actorId")).mapToInt(Integer::parseInt)
					.toArray();
			// String[] convert to Int[]

			// perfrom info
			int categoryCode = Integer.parseInt(multi_request.getParameter("categoryCode"));
			String performName = multi_request.getParameter("performName");
			String performDetail = multi_request.getParameter("performDetail");
			String runTime = multi_request.getParameter("runTime");
			String performImg = multi_request.getFilesystemName("performPic");
			String detailImg = multi_request.getFilesystemName("detailPic");
			if (performImg == null)
				performImg = performDao.getPerformOne(performId).getPerformImg();

			if (detailImg == null)
				detailImg = performDao.getPerformOne(performId).getDetailImg();

			Perform perform = new Perform(performId, categoryCode, performName, performDetail, performImg, detailImg,
					runTime);
			// perfrom update
			performDao.updatePerform(perform);
			// actor info 배열로 받아오기
			String[] name = multi_request.getParameterValues("name");
			String[] part = multi_request.getParameterValues("part");
			int[] actorCode = Arrays.stream(multi_request.getParameterValues("actorCode")).mapToInt(Integer::parseInt)
					.toArray();
			// old actor delete 출연진 총원이 달라지기 때문에 전체 삭제 후 입력
			for (int i = 0; i < actorIdArr.length; i++) {
				actorDao.deleteActor(actorIdArr[i]);
			}
			// new actor insert 0번은 hidden 값으로 저장 X
			for (int i = 1; i < name.length; i++) {
				actorDao.insertActor(new Actor(0, performId, name[i], part[i], actorCode[i]));
			}
			// sch info
			String[] stime_temp = multi_request.getParameterValues("sTime");
			String[] stime = new String[stime_temp.length - 1];
			for (int i = 0; i < stime.length; i++) {
				stime[i] = stime_temp[i + 1];
			}
			String strDate = multi_request.getParameter("startDate");
			String eDate = multi_request.getParameter("endDate");
			ArrayList<Integer> schidList = new ArrayList<>();// schId 저장용 seat insert 할때 각 일정마다 seat 정보 inset

			// Old sch delete
			// sch row 삭제시 해당 seat,Ticket row 자동 삭제됨
			for (int i = 0; i < schIdArr.length; i++) {
				schDao.deleteSch(schIdArr[i]);
			}
			// New sch insert

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
				// 총 공연날짜 계산

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

			// seat insert
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

			// Ticket info
			int ticketPrice = Integer.parseInt(multi_request.getParameter("ticketPrice"));

			// Ticket insert
			for (int i = 0; i < schidList.size(); i++) {
				List<Seat> seatList = seatDao.getSeatList(schidList.get(i));
				for (int j = 0; j < seatList.size(); j++) {
					ticketDao.insertTicket(
							new Ticket(0, schidList.get(i), placeId, seatList.get(j).getSeatId(), ticketPrice));
				}
			}

			request.setAttribute("alertMsg", "[" + performName + "] 정보가 수정되었습니다.");
			request.setAttribute("replaceUrl", "/usr/perform/detail.do?placeId=" + placeId + "&performId=" + performId + "&adm=0");
		} else {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");

		}
		return new ActionForward(false, "/usr/perform/redirect");
	}

}
