package com.action.insert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.dao.PlaceDao;
import com.vo.Member;
import com.vo.Place;

public class InsertPlaceAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		boolean isRedirect = false;

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");

		String url = "/usr/perform/redirect";
		if (member.getMemberCode() == 0) {
			PlaceDao placeDao = PlaceDao.getInstance();
			url = "/usr/perform/insertPlace";
			String insert = request.getParameter("insert");
			if (insert == null) {
				String placeName = request.getParameter("placeName");
				String placeaddr = request.getParameter("placeaddr");
				String phonNum = request.getParameter("phonNum");
				int totalSeatNum = Integer.parseInt(request.getParameter("totalSeatNum"));
				int placeRow = Integer.parseInt(request.getParameter("placeRow"));
				int placeCol = Integer.parseInt(request.getParameter("placeCol"));
				placeDao.insertPlace(new Place(0, placeName, placeaddr, phonNum, totalSeatNum, placeRow, placeCol));
				url = "./find.do?search=n";
				isRedirect = true;
			}
		} else {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "./main.do");
		}
		return new ActionForward(isRedirect, url);
	}

}
