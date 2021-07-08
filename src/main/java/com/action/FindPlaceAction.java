package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PlaceDao;
import com.vo.Member;
import com.vo.Place;

public class FindPlaceAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {

		PlaceDao placeDao = PlaceDao.getInstance();

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		String url = "/usr/perform/searchPlace";
		if (!(member != null && member.getMemberCode() == 0)) {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "/usr/main.do");
			url = "/usr/perform/redirect";
		} else {
			String place = request.getParameter("place");
			if (place != null) {
				List<Place> placeList = placeDao.getPlaceList(place);
				request.setAttribute("placeList", placeList);
			}
		}
		return new ActionForward(false, url);
	}

}
