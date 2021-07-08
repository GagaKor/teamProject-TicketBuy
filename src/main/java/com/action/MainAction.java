package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PerformDao;
import com.dao.PlaceDao;
import com.dao.SchDao;
import com.vo.Member;
import com.vo.Perform;

public class MainAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		Member member = (Member) session.getAttribute("member");
		
		session.setAttribute("member", member);
		
		PerformDao performDao = PerformDao.getInstance();
		PlaceDao placeDao = PlaceDao.getInstance();
		SchDao schDao = SchDao.getInstance();
		List<Perform> list = performDao.getNewest();
		
		List<Integer> placeIdList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int placeId = schDao.getPlaceId(list.get(i).getPerformId());
			placeIdList.add(placeDao.getPlaceOne(placeId).getPlaceId());
		}
		request.setAttribute("placeIdList", placeIdList);
		request.setAttribute("list", list);
		
		
		return new ActionForward(false, "/usr/main");
	}

}
